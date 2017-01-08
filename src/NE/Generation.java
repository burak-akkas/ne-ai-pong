package NE;

import Misc.Config;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by burak
 */
public class Generation {

   public ArrayList<Genome> genomes;

    public Generation() {
        genomes = new ArrayList<>();

        for(int i = 0; i < Config.population; i++) {
            this.genomes.add(new Genome(null));
        }
    }

    public Generation nextGeneration() {

        Collections.sort(genomes, (a, b) -> {
            if(b.score < a.score) {
                return -1;
            }
            if(b.score > a.score) {
                return 1;
            }
            return 0;
        });

        ArrayList<Genome> newGeneration = new ArrayList<>();

        int eliteCount = (int)Math.round(Config.population * Config.elitism);
        for(int i = 0; i < eliteCount; i++) {
            newGeneration.add(this.genomes.get(i));
        }

        int luckCount = (int)Math.round(Config.population * Config.luck);
        for(int i = 0; i < luckCount; i++) {
            int luckyIndex = (int)Math.floor(Math.random() * (this.genomes.size() - eliteCount)) + eliteCount;
            newGeneration.add(this.genomes.get(luckyIndex));
        }

        int randomCount = (int)Math.round(Config.population * Config.chaos);
        for(int i = 0; i < randomCount; i++) {
            newGeneration.add(new Genome(null));
        }

        int breedIndex = 0;
        while(newGeneration.size() < Config.population) {
            int pairIndex = (int)Math.floor(Math.random() * this.genomes.size());

            ArrayList<Genome> offspring = this.genomes.get(breedIndex).breed(this.genomes.get(pairIndex));

            for(int i = 0; i < offspring.size(); i++) {
                newGeneration.add(offspring.get(i));
            }

            breedIndex++;
        }

        this.genomes = newGeneration;

        return this;
    }

    public void updateScores(ArrayList<Double> scores) {
        for(int i = 0; i< scores.size(); i++) {
            this.genomes.get(i).score = scores.get(i);
        }
    }
}
