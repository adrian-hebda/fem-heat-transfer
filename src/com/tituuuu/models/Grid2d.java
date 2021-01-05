package com.tituuuu.models;

import java.util.ArrayList;

public class Grid2d {
    public ArrayList<Element> allElements = new ArrayList<>();
    public ArrayList<Node> allNodes = new ArrayList<>();
    public double[][] H;
    public double[][] C;
    public double[] P;

    public Grid2d() {
        calculateNodesCoordinates();
        signNodesToElements();
        calculateHGlobal();
        calculateCGlobal();
        calculateP();
    }

    public void printMatrix(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.printf("%.2f  ", mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printVector(double[] vec) {
        for (int i = 0; i < vec.length; i++) {
            System.out.println(vec[i]);
        }
    }

    private void calculateHGlobal() {
        H = new double[GlobalData.nN][GlobalData.nN];
        for (int i = 0; i < allElements.size(); i++) {
            for (int j = 0; j < allElements.get(i).nodes.size(); j++) {
                for (int k = 0; k < allElements.get(i).nodes.size(); k++) {
                    H[allElements.get(i).nodes.get(j).id - 1][allElements.get(i).nodes.get(k).id - 1] += allElements.get(i).HLocal[j][k];
                    H[allElements.get(i).nodes.get(j).id - 1][allElements.get(i).nodes.get(k).id - 1] += allElements.get(i).HBLocal[j][k];
                }
            }
        }
        printMatrix(H);
    }
    private void calculateCGlobal(){
        C = new double[GlobalData.nN][GlobalData.nN];
        for (int i = 0; i < allElements.size(); i++) {
            for (int j = 0; j < allElements.get(i).nodes.size(); j++) {
                for (int k = 0; k < allElements.get(i).nodes.size(); k++) {
                    C[allElements.get(i).nodes.get(j).id - 1][allElements.get(i).nodes.get(k).id - 1] += allElements.get(i).CLocal[j][k];
                }
            }
        }
        printMatrix(C);
    }

    private void signNodesToElements(){
        for (int i = 0; i < GlobalData.nW - 1; i++) {
            for (int j = 0; j < GlobalData.nH - 1; j++) {
                ArrayList<Node> nodes = new ArrayList<>();
                nodes.add(allNodes.get((i * GlobalData.nH) + j));
                nodes.add(allNodes.get(((i + 1) * GlobalData.nH) + j));
                nodes.add(allNodes.get(((i + 1) * GlobalData.nH) + j + 1));
                nodes.add(allNodes.get((i * GlobalData.nH) + j + 1));
                allElements.add(new Element(nodes));
            }
        }
    }

    private void calculateNodesCoordinates() {
        int x = 0, y = 0; // origin of the coordinate system
        for (int i = 0; i < GlobalData.nW; i++) {
            for (int j = 0; j < GlobalData.nH; j++) {
                double nodeX = x + (i * (GlobalData.w / (GlobalData.nW - 1)));
                double nodeY = y + (j * (GlobalData.h / (GlobalData.nH - 1)));
                boolean ifBoundary = false;
                if (nodeX == x || nodeY == y || nodeX == (x + GlobalData.w) || nodeY == (y + GlobalData.h)) {
                    ifBoundary = true;
                }
                allNodes.add(new Node(nodeX, nodeY, ifBoundary, GlobalData.initialTemperature));
            }
        }
    }

    private void calculateP() {
        P = new double[GlobalData.nN];
        for (int i = 0; i < allElements.size(); i++) {
            for (int j = 0; j < allElements.get(i).nodes.size(); j++) {
                P[allElements.get(i).nodes.get(j).id - 1] += allElements.get(i).PLocal[j];
            }
        }
    }

    public double getMaxTemperature() {
        return allNodes.stream().mapToDouble(node -> node.t0).max().getAsDouble();
    }

    public double getMinTemperature() {
        return allNodes.stream().mapToDouble(node -> node.t0).min().getAsDouble();
    }

    public void signNewTemperatures(double[] newNodesTemperatures) {
        for (int i = 0; i < newNodesTemperatures.length; i++) {
            allNodes.get(i).t0 = newNodesTemperatures[i];
        }
    }

    public void printTemperatures() {
        System.out.println();
        int it = 1;
        for (Node node : allNodes) {
            System.out.printf("%.2f  ", node.t0);
            if (it % GlobalData.nH == 0)
                System.out.println();
            it++;
        }
        System.out.println();
    }
}
