package io.jsd.training.metaheuristic.algo;

import java.util.ArrayList;

// Un problème générique
public interface IProbleme {
    // Renvoie le voisinage d'une solution
    ArrayList<ISolution> Voisinage(ISolution solutionCourante);
    
    // Créé une solution aléatoire
    ISolution SolutionAleatoire();
    
    // Renvoie la meilleure solution d'un ensemble
    ISolution MeilleureSolution(ArrayList<ISolution> solutions);
}
