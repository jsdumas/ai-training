package io.jsd.training.pathfinding.algo;

import java.util.ArrayList;

// Interface définissant les graphes
public interface Graphe {
    // Informations sur le graphe
    Noeud NoeudDepart();
    Noeud NoeudSortie();
    
    // Récupération des noeuds et des arcs
    ArrayList<Noeud> ListeNoeuds();
    ArrayList<Noeud> ListeNoeudsSortants(Noeud source);
    ArrayList<Arc> ListeArcs();
    ArrayList<Arc> ListeArcsSortants(Noeud source);
    
    // Méthodes utilitaires
    int NombreNoeuds();
    double Cout(Noeud depart, Noeud arrivee);
    String ReconstruireChemin();
    void CalculerDistancesEstimees();
    void Effacer();
}
