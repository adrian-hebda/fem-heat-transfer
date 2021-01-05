package com.tituuuu;

import com.tituuuu.models.GlobalData;
import com.tituuuu.models.Grid2d;

public class Solver {
    public static double[][] calculateHFinal(Grid2d grid) {
        double[][] HFinal = new double[GlobalData.nN][GlobalData.nN];
        for (int i = 0; i < grid.H.length; i++) {
            for (int j = 0; j < grid.H[i].length; j++) {
                HFinal[i][j] = grid.H[i][j] + grid.C[i][j] / GlobalData.simulationTimeStep;
            }
        }
        return HFinal;
    }

    public static double[] calculatePFinal(Grid2d grid) {
        double[] PFinal = new double[GlobalData.nN];
        for (int i = 0; i < grid.C.length; i++) {
            double row = 0;
            for (int j = 0; j < grid.C[i].length; j++) {
                row += (grid.C[i][j] / GlobalData.simulationTimeStep) * grid.allNodes.get(j).t0;
            }
            PFinal[i] = row - grid.P[i];
        }
        return PFinal;
    }
}
