package io.jsd.training.geneticalgorithm.algo;

import java.util.Random;

// Ensemble des paramètres du système
public class Parametres {
    // Paramètres sur la population et les individus
    public static int nbIndividus = 20;
    public static int nbGenes = 10;
    
    // Critères d'arrêt
    public static int nbMaxGenerations = 50;
    public static double minFitness = 0.0;
    
    // Taux des opérateurs
    public static double tauxMutation = 0.1;
    public static double tauxAjoutGene = 0.2;
    public static double tauxSupprGene = 0.1;
    public static double tauxCrossover = 0.6;
    
    // Générateur aléatoire
    public static Random random = new Random();
}
