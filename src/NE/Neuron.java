package NE;

import Misc.Util;
import java.util.ArrayList;

/**
 * Created by burak
 */
public class Neuron {

    double output = 0;
    ArrayList<Double> weights;

    public Neuron(int weightsCount) {
        this.weights = new ArrayList<>();

        for(int i = 0; i < weightsCount; i++) {
            this.weights.add(Util.rand());
        }
    }
}
