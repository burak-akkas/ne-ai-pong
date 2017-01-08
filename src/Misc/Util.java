package Misc;

/**
 * Created by burak
 */
public class Util {

    public static double rand() {
        return Math.random() * 2 - 1;
    }

    public static double sigmoid(double t) {
        return  (1/( 1 + Math.pow(Math.E,(-1*t))));
    }
}
