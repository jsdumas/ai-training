package io.jsd.training.metaheuristic.application.AlgorithmesSacADos;

import io.jsd.training.metaheuristic.algo.ISolution;
import io.jsd.training.metaheuristic.algo.algorithmes.RechercheTabou;
import io.jsd.training.metaheuristic.application.SolutionSacADos;
import java.util.ArrayList;

// Recherche tabou pour le problème du sac à dos
public class RechercheTabouSacADos extends RechercheTabou {
    protected int nbIterationsSansAmelioration = 0;
    protected int nbIterations = 0;
    protected ArrayList<SolutionSacADos> listeTaboue = new ArrayList();
    
    private final static int NB_MAX_ITERATIONS = 100;
    private final static int NB_MAX_ITERATIONS_SANS_AMELIORATIONS = 30;
    private final static int NB_MAX_POSITIONS_TABOUES = 50;
    
    @Override
    protected boolean CritereArret() {
        return (nbIterations > NB_MAX_ITERATIONS || nbIterationsSansAmelioration > NB_MAX_ITERATIONS_SANS_AMELIORATIONS);
    }
    
    @Override
    protected void MiseAJour(ISolution solution) {
        if (!listeTaboue.contains(solution)) {
            solutionCourante = solution;
            AjouterListeTaboue(solution);
            if (meilleureSolution.getValeur() < solution.getValeur()) {
                meilleureSolution = solution;
                nbIterationsSansAmelioration = 0;
            }
        }
    }
    
    @Override
    protected void Incrementer() {
        nbIterationsSansAmelioration++;
        nbIterations++;
    }
    
    @Override
    protected void EnvoyerResultat() {
        ihm.AfficherMessage(meilleureSolution.toString());
    }
    
    @Override
    protected void AjouterListeTaboue(ISolution solution) {
        while (listeTaboue.size() >= NB_MAX_POSITIONS_TABOUES) {
            listeTaboue.remove(0);
        }
        listeTaboue.add((SolutionSacADos) solution);
    }

    @Override
    protected ArrayList<ISolution> EnleverSolutionsTaboues(ArrayList<ISolution> voisinage) {
        voisinage.removeAll(listeTaboue);
        return voisinage;
    }
}
