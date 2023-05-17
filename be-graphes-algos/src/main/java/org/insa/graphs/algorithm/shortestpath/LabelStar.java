package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class LabelStar extends Label {

    private float estimatedCost; // Estimated cost to the destination

    public LabelStar(Node current_node, float estimatedCost) {
        super(current_node);
        this.estimatedCost = estimatedCost;
    }

    // Getter for the estimated cost
    public double getEstimatedCost() {
        return this.estimatedCost;
    }

    // Override the getTotalCost method to return the sum of cost and estimated cost
    @Override
    public float getTotalCost() {
        return this.getTotalCost() + this.estimatedCost;
    }

    // Implement the compareTo method to compare labels based on total cost
    //compare() va retourner -1 si (this.getTotalCost()< other.getTotalCost())
    //             retourner  1 si (this.getTotalCost()> other.getTotalCost())
    //             retourner  0 si (this.getTotalCost()= other.getTotalCost())
    @Override
    public int compareTo(Label other) {
        return Double.compare(this.getTotalCost(), other.getTotalCost());
    }
    
    
}
