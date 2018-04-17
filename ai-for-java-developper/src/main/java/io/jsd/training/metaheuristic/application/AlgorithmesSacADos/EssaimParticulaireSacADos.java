package io.jsd.training.metaheuristic.application.AlgorithmesSacADos;

import io.jsd.training.metaheuristic.algo.ISolution;
import io.jsd.training.metaheuristic.algo.algorithmes.EssaimParticulaire;
import io.jsd.training.metaheuristic.application.Boite;
import io.jsd.training.metaheuristic.application.ProblemeSacADos;
import io.jsd.training.metaheuristic.application.SolutionSacADos;
import java.util.ArrayList;

// Essaim particulaire pour le problème du sac à dos
public class EssaimParticulaireSacADos extends EssaimParticulaire {
    protected int nbIterations = 0;
    private final static int NB_MAX_ITERATIONS = 200;
    
    @Override
    protected void MiseAJourSolutions() {
        for (ISolution sol : solutions) {
            SolutionSacADos solution = (SolutionSacADos) sol;
            if (!solution.equals(meilleureSolution)) {
                // Ajout d'un élément de la meilleure jusqu'à présent
                int index = ProblemeSacADos.generateur.nextInt(((SolutionSacADos)meilleureSolution).contenu.size());
                Boite b = ((SolutionSacADos)meilleureSolution).contenu.get(index);
                if (!solution.contenu.contains(b)) {
                    solution.contenu.add(b);
                }
                // Ajout d'un élément de la meilleure actuelle
                index = ProblemeSacADos.generateur.nextInt(((SolutionSacADos)meilleureActuelle).contenu.size());
                b = ((SolutionSacADos)meilleureActuelle).contenu.get(index);
                if (!solution.contenu.contains(b)) {
                    solution.contenu.add(b);
                }
                // Diminution du poids si besoin
                while (solution.getPoids() > ((ProblemeSacADos)probleme).poidsMax) {
                    index = ProblemeSacADos.generateur.nextInt(solution.contenu.size());
                    solution.contenu.remove(index);
                }
                // Enfin, on complète
                double espaceDispo = ((ProblemeSacADos)probleme).poidsMax - solution.getPoids();
                ArrayList<Boite> boitesPossibles = ((ProblemeSacADos)probleme).Boites();
                boitesPossibles.removeAll(solution.contenu);
                ((ProblemeSacADos)probleme).EliminerTropLourdes(boitesPossibles, espaceDispo);
                while (!boitesPossibles.isEmpty()) {
                    index = ProblemeSacADos.generateur.nextInt(boitesPossibles.size());
                    b = boitesPossibles.get(index);
                    solution.contenu.add(b);
                    boitesPossibles.remove(b);
                    espaceDispo = ((ProblemeSacADos)probleme).poidsMax - solution.getPoids();
                    ((ProblemeSacADos)probleme).EliminerTropLourdes(boitesPossibles, espaceDispo);
                }
            }
        }
    }
    
    @Override
    protected void MiseAJourVariables() {
        meilleureActuelle = solutions.get(0);
        for (ISolution sol : solutions) {
            if (sol.getValeur() > meilleureActuelle.getValeur()) {
                meilleureActuelle = sol;
            }
        }
        if (meilleureActuelle.getValeur() > meilleureSolution.getValeur()) {
            meilleureSolution = meilleureActuelle;
        }
    }

    @Override
    protected boolean CritereArret() {
        return nbIterations > NB_MAX_ITERATIONS;
    }

    @Override
    protected void Incrementer() {
        nbIterations++;
    }

    @Override
    protected void EnvoyerResultat() {
        ihm.AfficherMessage(meilleureSolution.toString());
    }
}
