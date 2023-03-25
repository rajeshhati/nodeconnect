package com.myTask.myTask.Entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private List<Node> neighbors;
    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void connect(Node other) {
        neighbors.add(other);
    }
}
