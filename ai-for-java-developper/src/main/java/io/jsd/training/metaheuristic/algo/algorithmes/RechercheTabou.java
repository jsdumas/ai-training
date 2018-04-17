package io.jsd.training.metaheuristic.algo.algorithmes;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;

// Recherche tabou : on se déplace sur le meilleur voisin non présent dans la liste taboue
public abstract class RechercheTabou extends Algorithme {
    protected ISolution solutionCourante;
    protected ISolution meilleureSolution;
    
    @Override
    public final void Resoudre(IProbleme pb, IHM ihm) {
        super.Resoudre(pb, ihm);
        
        solutionCourante = probleme.SolutionAleatoire();
        meilleureSolution = solutionCourante;
        AjouterListeTaboue(solutionCourante);
        
        while (!CritereArret()) {
            ArrayList<ISolution> voisinage = probleme.Voisinage(solutionCourante);
            if (voisinage != null) {
                voisinage = EnleverSolutionsTaboues(voisinage);
                ISolution meilleurVoisin = probleme.MeilleureSolution(voisinage);
                if (meilleurVoisin != null) {
                    MiseAJour(meilleurVoisin);
                }
            }
            Incrementer();
        }
        EnvoyerResultat();
    }
    
    protected abstract void AjouterListeTaboue(ISolution solution);
    protected abstract ArrayList<ISolution> EnleverSolutionsTaboues(ArrayList<ISolution> voisinage);
    protected abstract boolean CritereArret();
    protected abstract void MiseAJour(ISolution solution);
    protected abstract void Incrementer();
}
