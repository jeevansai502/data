/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

/**
 *
 * @author placements2017
 */
public class Connection1 {
    
    static int counter=0;
    final int id;
    double weight;
    final Neuron1 leftNeuron;
    final Neuron1 rightNeuron;
    double prevDeltaWeight = 0;
    double deltaWeight = 0;
    
    Connection1(Neuron1 from,Neuron1 to){
           id = counter++;
           leftNeuron = from;
           rightNeuron = to;
    }
    
    double getWeight(){
       return weight;
    }
    
    void setWeight(double w){
        weight = w;
    }
    
    Neuron1 getFromNeuron(){
        return leftNeuron;
    }
    
    Neuron1 getToNeuron(){
        return rightNeuron;
    }
    
    void setDeltaWeight(double w) {
        prevDeltaWeight = deltaWeight;
        deltaWeight = w;
    }
    
    public double getPrevDeltaWeight() {
        return prevDeltaWeight;
    }

}
