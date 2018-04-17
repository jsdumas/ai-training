package io.jsd.training.pathfinding.algo;

import java.util.ArrayList;

// Interface d�finissant les graphes
public interface Graphe {
    // Informations sur le graphe
    Noeud NoeudDepart();
    Noeud NoeudSortie();
    
    // R�cup�ration des noeuds et des arcs
    ArrayList<Noeud> ListeNoeuds();
    ArrayList<Noeud> ListeNoeudsSortants(Noeud source);
    ArrayList<Arc> ListeArcs();
    ArrayList<Arc> ListeArcsSortants(Noeud source);
    
    // M�thodes utilitaires
    int NombreNoeuds();
    double Cout(Noeud depart, Noeud arrivee);
    String ReconstruireChemin();
    void CalculerDistancesEstimees();
    void Effacer();
}
