package io.jsd.training.metaheuristic.application.AlgorithmesSacADos;

import io.jsd.training.metaheuristic.algo.ISolution;
import io.jsd.training.metaheuristic.algo.algorithmes.DescenteGradient;

// Descente de gradient pour le problème du sac à  dos
public class DescenteGradientSacADos extends DescenteGradient {
    protected int nbIterationsSansAmelioration = 0;
    protected final static int NB_MAX_ITERATIONS_SANS_AMELIORATION = 50;
    
    @Override
    protected boolean CritereArret() {
        return nbIterationsSansAmelioration >= NB_MAX_ITERATIONS_SANS_AMELIORATION;
    }

    @Override
    protected void Incrementer() {
        nbIterationsSansAmelioration++;
    }
    
    @Override
    protected void MiseAJour(ISolution solution) {
        if (solution.getValeur() > solutionCourante.getValeur()) {
            solutionCourante = solution;
            nbIterationsSansAmelioration = 0;
        }
    }

    @Override
    protected void EnvoyerResultat() {
        ihm.AfficherMessage(solutionCourante.toString());
    }

}
