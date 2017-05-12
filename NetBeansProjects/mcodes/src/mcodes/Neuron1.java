/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author placements2017
 */
public class Neuron1 {
    
    static int counter=0;
    final int id;
    Connection1 biasconnection;
    final double bias=-1;
    ArrayList<Connection1> inconnections;
    double output;
    Map<Integer, Connection1> connectionLookup;
    
    Neuron1(){
        id = counter++;
        inconnections = new ArrayList<>();
        connectionLookup = new HashMap<Integer, Connection1>();
    }
    
    void addInconnections(ArrayList<Neuron1> inNeurons){
        
        for(Neuron1 n : inNeurons){
            
            Connection1 con = new Connection1(n,this);
            inconnections.add(con);
            connectionLookup.put(n.id, con);
        }
    }

    void calculateOutput() {
        double s = 0;
        for (Connection1 con : inconnections) {
            Neuron1 leftNeuron = con.getFromNeuron();
            double weight = con.getWeight();
            double a = leftNeuron.getOutput(); //output from previous layer

            s = s + (weight * a);
        }
        s = s + (biasconnection.getWeight() * bias);

        output = g(s);
    }
    
    double g(double x){
        return sigmoid(x);
    }
    
    double sigmoid(double x){
         return 1.0 / (1.0 + (Math.exp(-x)));
    }
    
    public void addBiasConnection(Neuron1 n) {
        Connection1 con = new Connection1(n, this);
        biasconnection = con;
        inconnections.add(con);
    }
    
    public ArrayList<Connection1> getAllInConnections() {
        return inconnections;
    }
    
    public double getBias() {
        return bias;
    }
    
    public double getOutput() {
        return output;
    }

    public void setOutput(double out) {
        output = out;
    }
    
    public Connection1 getConnection(int neuronIndex) {
        return connectionLookup.get(neuronIndex);
    }

}
