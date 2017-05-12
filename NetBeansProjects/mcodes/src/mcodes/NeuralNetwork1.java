/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author placements2017
 */
public class NeuralNetwork1 {

    final double epsilon = 0.00000000001;
    final Random rand;
    final ArrayList<Neuron1> inputLayer = new ArrayList<>();
    final ArrayList<Neuron1> hiddenLayer = new ArrayList<>();
    final ArrayList<Neuron1> outputLayer = new ArrayList<>();
    final double inputs[][];
    final double expectedOutputs[][];
    double resultOutputs[][];
    final int[] layers;
    final Neuron1 bias;
    final int randomWeightMultiplier = 1;
    double output[];
    final double learningRate = 0.9f;
    final double momentum = 0.7f;
    final DecimalFormat df;
    final boolean isTrained = false;
    final HashMap<String, Double> weightUpdate;

    NeuralNetwork1(int input, int hidden, int output) {

        weightUpdate = new HashMap<String, Double>();
        df = new DecimalFormat("#.0#");
        rand = new Random();
        bias = new Neuron1();
        inputs = new double[360][1];
        expectedOutputs = new double[360][1];
        resultOutputs = new double[360][1];

        for (double i = 0.0; i < 360.0; i++) {
            inputs[(int) i] = new double[]{i};
        }

        for (double i = 0; i < 360; i++) {
            expectedOutputs[(int) i] = new double[]{Math.sin(Math.toRadians(i))};
            System.out.println(i + " " + Math.toRadians(i) + " " + Math.sin(Math.toRadians(i)));
        }

        for (double i = 0; i < 360; i++) {
            resultOutputs[(int) i] = new double[]{-1};
        }

        layers = new int[]{input, hidden, output};

        for (int i = 0; i < layers.length; i++) {
            if (i == 0) {
                for (int j = 0; j < layers[i]; j++) {
                    Neuron1 neuron = new Neuron1();
                    inputLayer.add(neuron);
                }
            } else if (i == 1) {
                for (int j = 0; j < layers[i]; j++) {
                    Neuron1 neuron = new Neuron1();
                    neuron.addInconnections(inputLayer);
                    neuron.addBiasConnection(bias);
                    hiddenLayer.add(neuron);
                }
            } else if (i == 2) {
                for (int j = 0; j < layers[i]; j++) {
                    Neuron1 neuron = new Neuron1();
                    neuron.addInconnections(hiddenLayer);
                    neuron.addBiasConnection(bias);
                    outputLayer.add(neuron);
                }
            } else {
                System.out.println("!Error NeuralNetwork init");
            }
        }

        for (Neuron1 neuron : hiddenLayer) {
            ArrayList<Connection1> connections = neuron.getAllInConnections();
            for (Connection1 conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }

        for (Neuron1 neuron : outputLayer) {
            ArrayList<Connection1> connections = neuron.getAllInConnections();
            for (Connection1 conn : connections) {
                double newWeight = getRandom();
                conn.setWeight(newWeight);
            }
        }
        
        Neuron.counter = 0;
        Connection.counter = 0;
        
         if (isTrained) {
            trainedWeights();
            updateAllWeights();
        }

    }

    double getRandom() {
        return randomWeightMultiplier * (rand.nextDouble() * 2 - 1);
    }

    public void setInput(double inputs[]) {
        for (int i = 0; i < inputLayer.size(); i++) {
            inputLayer.get(i).setOutput(inputs[i]);
        }
    }
    
    public double[] getOutput() {
        double[] outputs = new double[outputLayer.size()];
        for (int i = 0; i < outputLayer.size(); i++) {
            outputs[i] = outputLayer.get(i).getOutput();
        }
        return outputs;
    }
    
    public void activate() {
        for (Neuron1 n : hiddenLayer) {
            n.calculateOutput();
        }
        for (Neuron1 n : outputLayer) {
            n.calculateOutput();
        }
    }

    
    public void applyBackpropagation(double expectedOutput[]) {

        // error check, normalize value ]0;1[
        for (int i = 0; i < expectedOutput.length; i++) {
            double d = expectedOutput[i];
            if (d < 0 || d > 1) {
                if (d < 0) {
                    expectedOutput[i] = 0 + epsilon;
                } else {
                    expectedOutput[i] = 1 - epsilon;
                }
            }
        }

        int i = 0;
        for (Neuron1 n : outputLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                double ak = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double desiredOutput = expectedOutput[i];

                double partialDerivative = -ak * (1 - ak) * ai
                        * (desiredOutput - ak);
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
            i++;
        }

        // update weights for the hidden layer
        for (Neuron1 n : hiddenLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                double aj = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Neuron1 out_neu : outputLayer) {
                    double wjk = out_neu.getConnection(n.id).getWeight();
                    double desiredOutput = (double) expectedOutput[j];
                    double ak = out_neu.getOutput();
                    j++;
                    sumKoutputs = sumKoutputs
                            + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                }

                double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
        }
    }
    
    
    
    
    public static void main(String[] args) {
        NeuralNetwork1 nn = new NeuralNetwork1(1, 4, 1);
        int maxRuns = 50000;
        double minErrorCondition = 0.001;
        nn.run(maxRuns, minErrorCondition);
    }

    void run(int maxSteps, double minError) {
        
        double error = 1;
        int i;
        
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.length; p++) {
                setInput(inputs[p]);

                activate();

                output = getOutput();
                resultOutputs[p] = output;

                for (int j = 0; j < expectedOutputs[p].length; j++) {
                    double err = Math.pow(output[j] - expectedOutputs[p][j], 2);
                    error += err;
                }

                applyBackpropagation(expectedOutputs[p]);
            }
        }
        
        printResult();
                System.out.println("Sum of squared errors = " + error);
        System.out.println("##### EPOCH " + i + "\n");
        if (i == maxSteps) {
            System.out.println("!Error training try again");
        } else {
            printAllWeights();
            printWeightUpdate();
        }
        
    }
    
    
    public void updateAllWeights() {
        // update weights for the output layer
        for (Neuron1 n : outputLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
        // update weights for the hidden layer
        for (Neuron1 n : hiddenLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
    }
        
    
    void trainedWeights() {
        weightUpdate.clear();

        weightUpdate.put(weightKey(3, 0), 1.03);
        weightUpdate.put(weightKey(3, 1), 1.13);
        weightUpdate.put(weightKey(3, 2), -.97);
        weightUpdate.put(weightKey(4, 3), 7.24);
        weightUpdate.put(weightKey(4, 4), -3.71);
        weightUpdate.put(weightKey(4, 5), -.51);
        weightUpdate.put(weightKey(5, 6), -3.28);
        weightUpdate.put(weightKey(5, 7), 7.29);
        weightUpdate.put(weightKey(5, 8), -.05);
        weightUpdate.put(weightKey(6, 9), 5.86);
        weightUpdate.put(weightKey(6, 10), 6.03);
        weightUpdate.put(weightKey(6, 11), .71);
        weightUpdate.put(weightKey(7, 12), 2.19);
        weightUpdate.put(weightKey(7, 13), -8.82);
        weightUpdate.put(weightKey(7, 14), -8.84);
        weightUpdate.put(weightKey(7, 15), 11.81);
        weightUpdate.put(weightKey(7, 16), .44);
    }
    
    public void printWeightUpdate() {
        System.out.println("printWeightUpdate, put this i trainedWeights() and set isTrained to true");
        // weights for the hidden layer
        for (Neuron1 n : hiddenLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                String w = df.format(con.getWeight());
                System.out.println("weightUpdate.put(weightKey(" + n.id + ", "
                        + con.id + "), " + w + ");");
            }
        }
        // weights for the output layer
        for (Neuron1 n : outputLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                String w = df.format(con.getWeight());
                System.out.println("weightUpdate.put(weightKey(" + n.id + ", "
                        + con.id + "), " + w + ");");
            }
        }
        System.out.println();
    }
        
        
    String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
    
    void printResult() {
        System.out.println("NN example with xor training");
        for (int p = 0; p < inputs.length; p++) {
            System.out.print("INPUTS: ");
            for (int x = 0; x < layers[0]; x++) {
                System.out.print(inputs[p][x] + " ");
            }

            System.out.print("EXPECTED: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(expectedOutputs[p][x] + " ");
            }

            System.out.print("ACTUAL: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(resultOutputs[p][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    
    public void printAllWeights() {
        System.out.println("printAllWeights");
        // weights for the hidden layer
        for (Neuron1 n : hiddenLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                double w = con.getWeight();
                System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        // weights for the output layer
        for (Neuron1 n : outputLayer) {
            ArrayList<Connection1> connections = n.getAllInConnections();
            for (Connection1 con : connections) {
                double w = con.getWeight();
                System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        System.out.println();
    }
}
