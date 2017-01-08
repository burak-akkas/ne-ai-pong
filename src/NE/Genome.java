package NE;

import Misc.Config;
import Misc.Util;
import java.util.ArrayList;

/**
 * Created by burak
 */
public class Genome {

    double score = 0;
    Network network;

    public Genome(Network network) {
        if(network != null) {
            this.network = network;
        } else {
            this.network = new Network(Config.inputSize, Config.network);
        }
    }

    public ArrayList<Genome> breed(Genome pair) {

        ArrayList<Genome> offspring = new ArrayList<>();

        ArrayList<Double> thisWeights;
        ArrayList<Double> pairWeights;

        for(int i = 0; i < Config.childCount; i++) {
            thisWeights = this.network.dump();
            pairWeights = pair.network.dump();

            ArrayList<Double> newWeights = new ArrayList<>();

            for(int j = 0; j < thisWeights.size(); j++) {

                // crossover
                if(Math.random() < 0.5) {
                    newWeights.add(thisWeights.get(j));
                } else {
                    newWeights.add(pairWeights.get(j));
                }

                // mutation
                if(Math.random() < Config.mutationRate) {
                    newWeights.set(j, newWeights.get(j) +  (Util.rand() * Config.mutationRange));
                }
            }

            Network network = new Network(Config.inputSize, Config.network);
            Genome genome = new Genome(network.load(newWeights));
            offspring.add(genome);
        }

        return offspring;
    }

    public ArrayList<Double> action(ArrayList<Double> input) {
        return this.network.prepareOutput(input);
    }
}
