package io.jsd.training.metaheuristic.application.AlgorithmesSacADos;

import io.jsd.training.metaheuristic.algo.algorithmes.AlgorithmeGlouton;
import io.jsd.training.metaheuristic.application.Boite;
import io.jsd.training.metaheuristic.application.ProblemeSacADos;
import io.jsd.training.metaheuristic.application.SolutionSacADos;
import java.util.ArrayList;
import java.util.Collections;

// Algorithme glouton pour le problème du sac à dos
public class AlgorithmeGloutonSacADos extends AlgorithmeGlouton {
    SolutionSacADos solution;
    
    @Override
    protected void ConstruireSolution() {
        solution = new SolutionSacADos();
        ProblemeSacADos pb = (ProblemeSacADos) probleme;
        ArrayList<Boite> boitesPossibles = pb.Boites();
        Collections.sort(boitesPossibles, (Boite b1, Boite b2) -> (int) (((b2.valeur/b2.poids) >= (b1.valeur/b1.poids)) ? 1 : -1));
        double espaceDispo = pb.poidsMax;
        for (Boite b : boitesPossibles) {
            if (b.poids <= espaceDispo) {
                solution.contenu.add(b);
                espaceDispo -= b.poids;
            }
        }
    }

    @Override
    protected void EnvoyerResultat() {
        ihm.AfficherMessage(solution.toString());
    }

}
