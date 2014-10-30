package edu.iastate.utils;

public class MathUtils {

    private MathUtils() {}

    /**
     * Takes the logarithm of n in base base
     * 
     * @param n What you want to find the log of
     * @param base The base you want to find it in
     * @return log base b of n
     */
    public static double log(int n, int base) {
        return Math.log(n) / Math.log(base);
    }
}
