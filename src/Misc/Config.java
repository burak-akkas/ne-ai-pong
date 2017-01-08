package Misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by burak
 */
public class Config {

    /* Game */
    public static int width = 400;
    public static int height = 600;

    public static int paddleWidth = 100;
    public static int paddleHeight = 10;

    public static int minSpeed = 30;
    public static int maxSpeed = 40;

    public static int epochHits = 50;

    /* Algorithm */
    public static int network[] = {3, 1};
    public static int inputSize = 3;
    public static int population = 10000;
    public static int childCount = 1;

    public static double mutationRate = 0.1;
    public static double mutationRange = 0.3;
    public static double elitism = 0.2;
    public static double luck = 0.1;
    public static double chaos = 0.2;

    public static void loadConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("test.properties");


            // load a properties file
            prop.load(input);

            epochHits = Integer.valueOf(prop.getProperty("epochHits"));
            population = Integer.valueOf(prop.getProperty("population"));
            childCount = Integer.valueOf(prop.getProperty("childCount"));
            mutationRate = Double.valueOf(prop.getProperty("mutationRate"));
            elitism = Double.valueOf(prop.getProperty("elitism"));
            luck = Double.valueOf(prop.getProperty("luck"));
            chaos = Double.valueOf(prop.getProperty("chaos"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
