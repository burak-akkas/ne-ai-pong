package NE;

import Misc.Util;
import java.util.ArrayList;

/**
 * Created by burak
 */
public class Network {
    ArrayList<Layer> layers;
    Layer inputLayer;

    public Network(int inputSize, int[] layers) {
        this.layers = new ArrayList<>();

        inputLayer = new Layer(inputSize, layers[0]);
        this.layers.add(inputLayer);
    }

    public ArrayList<Double> prepareOutput(ArrayList<Double> input) {
        for(int l = 0; l < this.layers.size(); l++) {

            for(int n = 0; n < this.layers.get(l).neurons.size(); n++) {
                int output = 0;

                for(int i = 0; i < input.size(); i++) {
                    output += input.get(i) * this.layers.get(l).neurons.get(n).weights.get(i);
                }

                this.layers.get(l).neurons.get(n).output = Util.sigmoid(output);
            }

            input = this.layers.get(l).getOutput();
        }

        return this.layers.get(this.layers.size() - 1).getOutput();
    }

    public ArrayList<Double> dump() {
        ArrayList<Double> weights = new ArrayList<>();

        for(int l = 0; l < layers.size(); l++) {
            for(int n = 0; n < layers.get(l).neurons.size(); n++) {
                for(int w = 0; w < layers.get(l).neurons.get(n).weights.size(); w++) {
                    weights.add(layers.get(l).neurons.get(n).weights.get(w));
                }
            }
        }

        return weights;
    }

    public Network load(ArrayList<Double> weights) {
        int index = 0;
        for(int l = 0; l < layers.size(); l++) {
            for(int n = 0; n < layers.get(l).neurons.size(); n++) {
                for(int w = 0; w < layers.get(l).neurons.get(n).weights.size(); w++) {
                    layers.get(l).neurons.get(n).weights.set(w, weights.get(index));
                    index++;
                }
            }
        }

        return this;
    }
}
