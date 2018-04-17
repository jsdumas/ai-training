package io.jsd.training.metaheuristic.algo;

import java.util.ArrayList;

// Un probl�me g�n�rique
public interface IProbleme {
    // Renvoie le voisinage d'une solution
    ArrayList<ISolution> Voisinage(ISolution solutionCourante);
    
    // Cr�� une solution al�atoire
    ISolution SolutionAleatoire();
    
    // Renvoie la meilleure solution d'un ensemble
    ISolution MeilleureSolution(ArrayList<ISolution> solutions);
}
