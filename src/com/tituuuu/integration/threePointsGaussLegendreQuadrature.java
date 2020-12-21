package com.tituuuu.integration;

import java.util.ArrayList;

public class threePointsGaussLegendreQuadrature {

    public static double calculate() {
        final double p1 = -Math.sqrt(3.0 / 5.0);
        final double p2 = 0.0;
        final double p3 = Math.sqrt(3.0 / 5.0);
        final double w1 = 5.0 / 9.0;
        final double w2 = 8.0 / 9.0;
        double result = 0.0;

        double[][] pointsOnSurface = {
                {p1, p1, w1, w1},
                {p2, p1, w2, w1},
                {p3, p1, w1, w1},

                {p1, p2, w1, w2},
                {p2, p2, w2, w2},
                {p3, p2, w1, w2},

                {p1, p3, w1, w1},
                {p2, p3, w2, w1},
                {p3, p3, w1, w1}
        };

        for (double[] point : pointsOnSurface) {
            double x = point[0];
            double y = point[1];
            double weight1 = point[2];
            double weight2 = point[3];
            result += f(x, y) * weight1 * weight2;
        }

        return result;
    }

    private static double f(double x, double y) {
        return (-5 * (x * x) * y) + (2 * x * (y * y)) + 10;
    }
}