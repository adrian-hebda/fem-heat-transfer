package com.tituuuu.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GlobalData {
    public static double h; // height of whole element
    public static double w; // width of whole element
    public static int nH; // number of nodes in height
    public static int nW; // number of node in width
    public static int nE; // number of all elements
    public static int nN; // number of all nodes
    public static int npc; // points of integration
    public static double K; // Conductivity
    public static double specificHeat;
    public static double ro;
    public static double alpha;
    public static double initialTemperature;
    public static double ambientTemperature;
    public static double simulationTime; // in seconds
    public static double simulationTimeStep; // in seconds


    public GlobalData() {
        File dataFile;
        Scanner dataScanner;

        try {
            dataFile = new File("src/data/data.txt");
            dataScanner = new Scanner(dataFile);
            if (dataScanner.hasNextLine()) {
                h = dataScanner.nextDouble();
                dataScanner.nextLine();
                w = dataScanner.nextDouble();
                dataScanner.nextLine();
                nH = dataScanner.nextInt();
                dataScanner.nextLine();
                nW = dataScanner.nextInt();
                dataScanner.nextLine();
                npc = dataScanner.nextInt();
                dataScanner.nextLine();
                K = dataScanner.nextDouble();
                dataScanner.nextLine();
                initialTemperature = dataScanner.nextDouble();
                dataScanner.nextLine();
                specificHeat = dataScanner.nextDouble();
                dataScanner.nextLine();
                ro = dataScanner.nextDouble();
                dataScanner.nextLine();
                alpha = dataScanner.nextDouble();
                dataScanner.nextLine();
                ambientTemperature = dataScanner.nextDouble();
                dataScanner.nextLine();
                simulationTime = dataScanner.nextDouble();
                dataScanner.nextLine();
                simulationTimeStep = dataScanner.nextDouble();
            }
            dataScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        nE = (nH-1)*(nW-1);
        nN = nH*nW;
    }

}
