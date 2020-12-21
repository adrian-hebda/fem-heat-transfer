package com.tituuuu.models;

public class Node {
    public static int nNodes = 0;
    public double x, y, t0;
    boolean boundaryCondition;
    int id;
    public Node(double x, double y, boolean boundaryCondition){
        this.x = x;
        this.y = y;
        this.t0 = t0;
        this.nNodes++;
        this.id = Node.nNodes;
        this.boundaryCondition = boundaryCondition;
    }
}
