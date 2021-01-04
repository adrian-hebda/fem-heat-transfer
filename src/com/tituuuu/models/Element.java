package com.tituuuu.models;

import java.util.ArrayList;

public class Element {
    public ArrayList<Node> nodes;
    public static int elements = 0;
    public double[][] HLocal;
    public double[][] CLocal;
    public double[][] HBLocal;
    public double[] PLocal;

    public Element(ArrayList<Node> nodes) {
        this.nodes = nodes;
        elements++;
        calculateProperties();
    }

    private void calculateProperties() {
        double[] x = new double[nodes.size()];
        double[] y = new double[nodes.size()];
        boolean[] isBoundary = new boolean[nodes.size()];

        for (int j = 0; j < nodes.size(); j++) {
            x[j] = nodes.get(j).x;
            y[j] = nodes.get(j).y;
            isBoundary[j] = nodes.get(j).boundaryCondition;
        }

        UniversalStructElement4 u = new UniversalStructElement4(x, y, GlobalData.npc, isBoundary);
        HLocal = u.evaluateH();
        CLocal = u.evaluateC();
        HBLocal = u.calculateHB();
        PLocal = u.calculateP();
    }
}
