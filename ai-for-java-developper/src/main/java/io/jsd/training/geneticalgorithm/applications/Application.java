package io.jsd.training.geneticalgorithm.applications;

import io.jsd.training.geneticalgorithm.algo.IHM;
import io.jsd.training.geneticalgorithm.algo.Individu;
import io.jsd.training.geneticalgorithm.algo.Parametres;
import io.jsd.training.geneticalgorithm.algo.ProcessusEvolutionnaire;

// Classe lan�ant les diff�rentes applications
public class Application implements IHM {
    public static void main(String[] args) {
        Application app = new Application();
        app.Run();
    }

    public void Run() {
        // R�solution du voyageur de commerce
        // Param�tres
        /*Parametres.tauxCrossover = 0.0;
        Parametres.tauxMutation = 0.3;
        Parametres.tauxAjoutGene = 0.0;
        Parametres.tauxSupprGene = 0.0;
        Parametres.minFitness = 2579;
        // Lancement
        ProcessusEvolutionnaire syst = new ProcessusEvolutionnaire(this, "PVC");
        syst.Run();*/
        
        // R�solution du labyrinthe
        // Param�tres
        Parametres.tauxCrossover = 0.6;
        Parametres.tauxMutation = 0.1;
        Parametres.tauxAjoutGene = 0.8;
        Parametres.tauxSupprGene = 0.1;
        Parametres.minFitness = 0;
        Parametres.nbMaxGenerations = 300;
        // Lancement
        ProcessusEvolutionnaire syst = new ProcessusEvolutionnaire(this, "Lab");
        syst.Run();
    }
    
    @Override
    public void AfficherMeilleurIndividu(Individu ind, int generation) {
        System.out.println(generation + " -> " + ind.toString());
    }
}
