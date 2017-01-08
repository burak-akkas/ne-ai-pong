package NE;

/**
 * Created by burak
 */
public class Evolution {

    static int generationCount = 0;
    public Generation generation;

    public Evolution() {
        if(generationCount == 0) {
            generation = new Generation();
        }

        generationCount++;
    }

    public Generation newGeneration() {
        if(generationCount != 0) {
            generation = generation.nextGeneration(); // this.options => Misc.Config class
        }

        generationCount++;

        return generation;
    }
}
