package io.jsd.training.metaheuristic.application.AlgorithmesSacADos;

import io.jsd.training.metaheuristic.algo.ISolution;
import io.jsd.training.metaheuristic.algo.algorithmes.RecuitSimule;
import io.jsd.training.metaheuristic.application.ProblemeSacADos;

// Recuit simulé pour le problème du sac à dos
public class RecuitSimuleSacADos extends RecuitSimule {
    protected int nbIterationsSansAmelioration = 0;
    protected int nbIterations = 0;
    
    private final static int NB_MAX_ITERATIONS = 100;
    private final static int NB_MAX_ITERATIONS_SANS_AMELIORATIONS = 30;
    
    @Override
    protected void MiseAJourTemperature() {
        temperature *= 0.95;
    }

    @Override
    protected void InitialiserTemperature() {
        temperature = 5;
    }

    @Override
    protected boolean CritereArret() {
        return nbIterations > NB_MAX_ITERATIONS || nbIterationsSansAmelioration > NB_MAX_ITERATIONS_SANS_AMELIORATIONS;
    }

    @Override
    protected void MiseAJour(ISolution solution) {
        double proba = 0.0;
        if (solution.getValeur() < solutionCourante.getValeur()) {
            // Solution moins bonne, on calcule la proba de l'accepter
            proba = Math.exp(-1 * (solutionCourante.getValeur() - solution.getValeur()) / solutionCourante.getValeur() / temperature);
        }
        if (solution.getValeur() > solutionCourante.getValeur() || ProblemeSacADos.generateur.nextDouble() < proba) {
            // On accepte le changement
            solutionCourante = solution;
            if (solution.getValeur() > meilleureSolution.getValeur()) {
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
}
