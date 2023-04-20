package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class Label {
    private Node sommet_courant; // Sommet courant associé à ce label
    private boolean marque; // Booléen indiquant si le coût min de ce sommet est connu
    private float costSoFar; // Coût réalisé du plus court chemin depuis l'origine vers le sommet
    private Node pere; // Arc précédent sur le chemin correspondant au plus court chemin courant

    // Constructeur
    public Label(Node sommet_courant) {
        this.sommet_courant = sommet_courant;
        this.marque = false;
        this.costSoFar = Float.POSITIVE_INFINITY; // Initialiser le coût à l'infini par défaut
        this.pere = null;
    }

    // Getter et Settet
      public Node getSommet_courant() {
        return sommet_courant;
    }

    public boolean isMarque() {
        return marque;
    }

    public void setMarque(boolean marque) {
        this.marque = marque;
    }
   
    public float getCostSoFar() {
        return costSoFar;
    }

    public void setCostSoFar(float costSoFar) {
        this.costSoFar = costSoFar;
    }
    
    public Node getPere() {
        return pere;
    }

    public void setPere(Node pere) {
        this.pere = pere;
    }

    // Getter pour le coût, qui renvoie le coût réalisé
    public float getCost() {
        return costSoFar;
    }


    //????Vous devez aussi pouvoir associer un label à chaque noeud — sans modifier la classe Node ni la classe Graph. 
}
