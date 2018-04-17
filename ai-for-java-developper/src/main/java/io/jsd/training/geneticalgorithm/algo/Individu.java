package io.jsd.training.geneticalgorithm.algo;

import java.util.ArrayList;
import java.util.StringJoiner;

// Un individu dans une population
public abstract class Individu {
    // Attributs
    protected double fitness;
    protected ArrayList<IGene> genome;
    
    // Méthodes
    public double getFitness() {
        return fitness;
    }
    
    public abstract void Muter();
    public abstract double Evaluer();
    
    @Override
    public String toString() {
        String gen = "(" + fitness + ")";
        StringJoiner sj = new StringJoiner(" - ");
        sj.add(gen);
        for(IGene g : genome) {
            sj.add(g.toString());
        }
        return sj.toString();
    }
}
