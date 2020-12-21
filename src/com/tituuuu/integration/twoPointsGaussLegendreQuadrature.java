package com.tituuuu.integration;

public class twoPointsGaussLegendreQuadrature {
    public static double calculate() {
        final double p1 = 1 / Math.sqrt(3);

        double[][] pointsOnSurface = {
                {-p1, -p1},
                {p1, -p1},

                {-p1, p1},
                {p1, p1}
        };

        double result = 0.0;
        for (double[] point : pointsOnSurface) {
            result += f(point[0], point[1]);
        }

        return result;
    }

    private static double f(double x, double y) {
        return (-5 * (x * x) * y) + (2 * x * (y * y)) + 10;
    }
}
