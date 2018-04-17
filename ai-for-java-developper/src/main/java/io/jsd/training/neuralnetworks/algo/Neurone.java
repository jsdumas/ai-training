package io.jsd.training.neuralnetworks.algo;

import java.util.Random;

// Neurone formel de notre réseau
public class Neurone {
    protected double[] poids;
    protected double sortie;
    
    public double getSortie() {
        return sortie;
    }
    
    public double getPoids(int index) {
        return poids[index];
    }
    
    public void setPoids(int index, double valeur) {
        poids[index] = valeur;
    }
    
    public Neurone(int _nbEntrees) {
        sortie = Double.NaN;
        
        Random generateur = new Random();
        poids = new double[_nbEntrees + 1];
        for (int i = 0; i < _nbEntrees + 1; i++) {
            poids[i] = generateur.nextDouble() * 2.0 - 1.0;
        }
    }
    
    protected double Evaluer(double[] entrees) {
        if (Double.isNaN(sortie)) {
            double x = 0.0;
            int nbEntrees = entrees.length;
            for (int i = 0; i < nbEntrees; i++) {
                x += entrees[i] * poids[i];
            }
            x += poids[nbEntrees];
            sortie = 1.0 / (1.0 + Math.exp(-1.0 * x));
        }
        
        return sortie;
    }
    
    protected double Evaluer(PointND point) {
        return Evaluer(point.entrees);
    }
    
    protected void Effacer() {
        sortie = Double.NaN;
    }
}
