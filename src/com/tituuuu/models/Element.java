package com.tituuuu.models;

import java.util.ArrayList;
import java.util.List;

public class Element {
    public ArrayList<Node> nodes = new ArrayList<>(4);
    public static int elements = 0;
    public double[][] HLocal;
    public double[][] CLocal;
    public double[][] HBlocal;
    public Element(ArrayList<Node> nodes){
        this.nodes = nodes;
        elements++;
        createHLocal();
    }

    private void createHLocal(){
        double[] x = new double[nodes.size()];
        double[] y = new double[nodes.size()];

        for (int j = 0; j < nodes.size(); j++) {
            x[j] = nodes.get(j).x;
            y[j] = nodes.get(j).y;
        }

        UniversalStructElement4 u = new UniversalStructElement4(x, y, GlobalData.npc);
        HLocal = u.evaluateH();
        CLocal = u.evaluateC();
        HBlocal = u.calculateHB();
    }
}
