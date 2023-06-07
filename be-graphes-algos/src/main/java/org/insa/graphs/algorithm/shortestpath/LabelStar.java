package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class LabelStar extends Label implements Comparable<Label>{

    private float estimatedCost; // Estimated cost to the destination

    public LabelStar(Node current_node, float estimatedCost) {
        super(current_node);
        this.estimatedCost = estimatedCost;
    }

    // Getter for the estimated cost
    public float getEstimatedCost() {
        return this.estimatedCost;
    }

    // Override the getTotalCost method to return the sum of cost and estimated cost
    @Override
    public float getTotalCost() {
        return super.getTotalCost() + this.getEstimatedCost();
    }


    
}
