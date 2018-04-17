package io.jsd.training.geneticalgorithm.algo.PVC;

import java.util.ArrayList;
import java.util.Arrays;

// Problème du Voyageur de Commerce
public class PVC {
    // Attributs : villes et distances
    static ArrayList<String> villes;
    static int[][] distances;
    
    // Constructeur avec initialisation des villes
    public static void Init() {
        villes = new ArrayList(Arrays.asList("Paris", "Lyon", "Marseille", "Nantes", "Bordeaux", "Toulouse", "Lille"));
        
        distances = new int[villes.size()][];
        distances[0] = new int[] {0, 462, 772, 379, 546, 678, 215}; // Paris
        distances[1] = new int[] { 462, 0, 326, 598, 842, 506, 664 }; // Lyon 
        distances[2] = new int[] { 772, 326, 0, 909, 555, 407, 1005 }; // Marseille 
        distances[3] = new int[] { 379, 598, 909, 0, 338, 540, 584 }; // Nantes 
        distances[4] = new int[] { 546, 842, 555, 338, 0, 250, 792 }; // Bordeaux 
        distances[5] = new int[] { 678, 506, 407, 540, 250, 0, 926 }; // Toulouse 
        distances[6] = new int[] { 215, 664, 1005, 584, 792, 926, 0 }; // Lille 
    }
    
    // Donne la distance entre deux villes
    protected static int getDistance(int ville1, int ville2) {
        return distances[ville1][ville2];
    }

    // Renvoie une liste d'index
    protected static ArrayList<Integer> getVillesIndex() {
        int nbVilles = villes.size();
        ArrayList<Integer> villesIndex = new ArrayList();
        for (int i = 0; i < nbVilles; i++) {
            villesIndex.add(i);
        }
        return villesIndex;
    }
    
    // Renvoie le nom d'une ville donnée
    protected static String getVille(int villeIndex) {
        return villes.get(villeIndex);
    }
}
