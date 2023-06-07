package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label implements Comparable<Label>{

    private float estimatedCost; // Estimated cost to the destination

    public LabelStar(Node current_node, ShortestPathData data) {
        super(current_node);
        this.estimatedCost = (float)Point.distance(current_node.getPoint(),data.getDestination().getPoint());
    };

    // Getter for the estimated cost
    public float getEstimatedCost() {
        return this.estimatedCost;
    }

    // Override the getTotalCost method to return the sum of cost and estimated cost
    @Override
    public float getTotalCost() {
        return super.getTotalCost() + this.estimatedCost;
    }


    
}
