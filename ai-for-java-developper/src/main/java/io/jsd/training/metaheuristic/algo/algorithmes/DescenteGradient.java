package io.jsd.training.metaheuristic.algo.algorithmes;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;

// Descente de gradient : on cherche le meilleur voisin jusqu'à ce qu'il n'y ait plus d'améliorations
public abstract class DescenteGradient extends Algorithme {
    protected ISolution solutionCourante;
    
    @Override
    public final void Resoudre(IProbleme _pb, IHM ihm) {
        super.Resoudre(_pb, ihm);
        
        solutionCourante = probleme.SolutionAleatoire();
        while(!CritereArret()) {
            ArrayList<ISolution> voisinage = probleme.Voisinage(solutionCourante);
            if (voisinage != null) {
                ISolution meilleureSolution = probleme.MeilleureSolution(voisinage);
                MiseAJour(meilleureSolution);
            }
            Incrementer();
        }
        EnvoyerResultat();
    }
    
    protected abstract boolean CritereArret();
    protected abstract void MiseAJour(ISolution solution);
    protected abstract void Incrementer();
}
