package io.jsd.training.pathfinding.algo;

// Classe représentant les noeuds dans un graphe
public abstract class Noeud {
    public Noeud precurseur = null;
    public double distanceDuDepart = Double.POSITIVE_INFINITY;
    public double distanceEstimee;   
}
