package io.jsd.training.metaheuristic.application;

import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;
import java.util.StringJoiner;

// Une solution potentielle = un chargement possible du sac ра dos
public class SolutionSacADos implements ISolution {
    public ArrayList<Boite> contenu;
    
    public SolutionSacADos() {
        contenu = new ArrayList();
    }
    
    public SolutionSacADos(SolutionSacADos original) {
        contenu = new ArrayList();
        contenu.addAll(original.contenu);
    }
    
    public double getPoids() {
        double poids = 0.0;
        for (Boite b : contenu) {
            poids += b.poids;
        }
        return poids;
    }
    
    @Override
    public double getValeur() {
        double valeur = 0.0;
        for (Boite b : contenu) {
            valeur += b.valeur;
        }
        return valeur;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" - ");
        sj.add("Valeur : " + getValeur());
        sj.add("Poids : " + getPoids());
        for(Boite b : contenu) {
            sj.add(b.toString());
        }
        return sj.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SolutionSacADos)) {
            return false;
        }
        SolutionSacADos sol = (SolutionSacADos) o;
        if (sol.contenu.size() != this.contenu.size() || sol.getPoids() != this.getPoids() || sol.getValeur() != this.getValeur()) {
            return false;
        }
        for(Boite b : contenu) {
            if (!sol.contenu.contains(b)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
