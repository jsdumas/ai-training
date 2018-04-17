package io.jsd.training.metaheuristic.algo.algorithmes;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;

// Algorithme par essaim : plusieurs solutions vont se déplacer dans l'espace de recherche
public abstract class EssaimParticulaire extends Algorithme {
    protected ArrayList<ISolution> solutions;
    protected ISolution meilleureSolution;
    protected ISolution meilleureActuelle;

    protected final static int NB_INDIVIDUS = 30;
    
    @Override
    public final void Resoudre(IProbleme pb, IHM ihm) {
        // Initialisation
        super.Resoudre(pb, ihm);
        solutions = new ArrayList();
        for (int i = 0; i < NB_INDIVIDUS; i++) {
            ISolution nouvelleSol = probleme.SolutionAleatoire();
            solutions.add(nouvelleSol);
        }
        meilleureSolution = probleme.MeilleureSolution(solutions);
        meilleureActuelle = meilleureSolution;
        
        // Boucle
        while (!CritereArret()) {
            MiseAJourVariables();
            MiseAJourSolutions();
            Incrementer();
        }
        
        EnvoyerResultat();
    }
    
    protected abstract void MiseAJourVariables();
    protected abstract void MiseAJourSolutions();
    protected abstract boolean CritereArret();
    protected abstract void Incrementer();
}
