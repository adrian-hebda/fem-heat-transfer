package com.tituuuu;

import com.tituuuu.SystemOfEquationSolvers.GaussianElimination;
import com.tituuuu.models.GlobalData;
import com.tituuuu.models.Grid2d;

public class Program {

    public static void main(String[] args) {
        GlobalData globalData = new GlobalData();
        Grid2d grid = new Grid2d();

        for (int i = 0; i < GlobalData.simulationTime / GlobalData.simulationTimeStep; i++) {
            double[][] HFinal = Solver.calculateHFinal(grid);
            double[] PFinal = Solver.calculatePFinal(grid);
            double[] newNodesTemperatures = GaussianElimination.solve(HFinal, PFinal);
            grid.signNewTemperatures(newNodesTemperatures);
            grid.printTemperatures();
            System.out.println("Min: " + grid.getMinTemperature() + " Max: " + grid.getMaxTemperature());

        }
    }
}
