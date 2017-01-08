package NE;

import java.util.ArrayList;

/**
 * Created by burak
 */
public class Layer {

    ArrayList<Neuron> neurons;

    public Layer(int inputSize, int neuronsCount) {
        this.neurons = new ArrayList<>();

        for(int i = 0; i < neuronsCount; i++) {
            this.neurons.add(new Neuron(inputSize));
        }
    }

    public ArrayList<Double> getOutput() {
        ArrayList<Double> output = new ArrayList<>();

        for(int i = 0; i < neurons.size(); i++) {
            output.add(this.neurons.get(i).output);
        }

        return output;
    }
}
