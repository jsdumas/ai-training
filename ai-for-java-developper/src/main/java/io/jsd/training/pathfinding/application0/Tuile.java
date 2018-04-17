package io.jsd.training.pathfinding.application0;

import io.jsd.training.pathfinding.algo.Noeud;

// Classe représentant chaque case de notre carte
public class Tuile extends Noeud {
    protected TypeTuile type;
    protected int ligne;
    protected int colonne;
    
    // Constructeur
    public Tuile(TypeTuile _type, int _ligne, int _colonne) {
        type = _type;
        ligne = _ligne;
        colonne = _colonne;
    }
    
    // Indique si la case est accessible ou non
    boolean Accessible() {
        return (type.equals(TypeTuile.Chemin) || type.equals(TypeTuile.Herbe) || type.equals(TypeTuile.Pont));
    }
    
    // Renvoie le coà»t de la case
    double Cout() {
        switch (type) {
            case Chemin :
                return 1;
            case Pont :
            case Herbe :
                return 2;
            default :
                return Double.POSITIVE_INFINITY;
        }
    }
    
    @Override
    public String toString() {
        return "[" + ligne + ";" + colonne + ";" + type.toString() + "]";
    }
}
