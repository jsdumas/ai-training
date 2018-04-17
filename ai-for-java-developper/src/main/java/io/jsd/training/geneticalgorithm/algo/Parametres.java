package io.jsd.training.geneticalgorithm.algo;

import java.util.Random;

// Ensemble des param�tres du syst�me
public class Parametres {
    // Param�tres sur la population et les individus
    public static int nbIndividus = 20;
    public static int nbGenes = 10;
    
    // Crit�res d'arr�t
    public static int nbMaxGenerations = 50;
    public static double minFitness = 0.0;
    
    // Taux des op�rateurs
    public static double tauxMutation = 0.1;
    public static double tauxAjoutGene = 0.2;
    public static double tauxSupprGene = 0.1;
    public static double tauxCrossover = 0.6;
    
    // G�n�rateur al�atoire
    public static Random random = new Random();
}
