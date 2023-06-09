package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    /*redefinition de la methode newLabel pour utiliser LabelStar à la place de Label  */
    protected Label newLabel(Node node, ShortestPathData data) {
		return new LabelStar(node, data);
	}
}
