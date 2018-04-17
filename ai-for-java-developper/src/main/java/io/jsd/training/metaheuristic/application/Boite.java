package io.jsd.training.metaheuristic.application;

// Une boite dans le problème du sac à dos
public class Boite {
    public double poids;
    public double valeur;
    protected String nom;
    
    public Boite(String _nom, double _poids, double _valeur) {
        nom = _nom;
        poids = _poids;
        valeur = _valeur;
    }
    
    @Override
    public String toString() {
        return nom + " (" + poids + ", " + valeur + ")";
    }
}
