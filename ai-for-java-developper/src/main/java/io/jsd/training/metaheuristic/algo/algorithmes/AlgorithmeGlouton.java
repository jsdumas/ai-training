package io.jsd.training.metaheuristic.algo.algorithmes;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;

// Résolution par algorithme glouton : construction progressive de la solution
public abstract class AlgorithmeGlouton extends Algorithme {
    @Override
    public final void Resoudre(IProbleme pb, IHM ihm) {
        super.Resoudre(pb, ihm);
        ConstruireSolution();
        EnvoyerResultat();
    }
    
    protected abstract void ConstruireSolution();
}
