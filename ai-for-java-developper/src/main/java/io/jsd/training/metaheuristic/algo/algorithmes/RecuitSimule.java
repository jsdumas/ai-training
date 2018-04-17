package io.jsd.training.metaheuristic.algo.algorithmes;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;

// Algorithme du recuit : on va faire des bonds de plus en plus petits avec la température qui baisse
public abstract class RecuitSimule extends Algorithme {
    protected ISolution solutionCourante;
    protected ISolution meilleureSolution;
    protected double temperature;
    
    @Override
    public final void Resoudre(IProbleme pb, IHM ihm) {
        super.Resoudre(pb, ihm);
        
        solutionCourante = probleme.SolutionAleatoire();
        meilleureSolution = solutionCourante;
        InitialiserTemperature();
        
        while (!CritereArret()) {
            ArrayList<ISolution> voisinage = probleme.Voisinage(solutionCourante);
            if (voisinage != null) {
                ISolution meilleurVoisin = probleme.MeilleureSolution(voisinage);
                MiseAJour(meilleurVoisin);
            }
            Incrementer();
            MiseAJourTemperature();
        }
        EnvoyerResultat();
    }
    
    protected abstract void MiseAJourTemperature();
    protected abstract void InitialiserTemperature();
    protected abstract boolean CritereArret();
    protected abstract void MiseAJour(ISolution solution);
    protected abstract void Incrementer();
}
