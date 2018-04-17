package io.jsd.training.geneticalgorithm.algo.labyrinthe;

import io.jsd.training.geneticalgorithm.algo.IGene;
import io.jsd.training.geneticalgorithm.algo.Individu;
import io.jsd.training.geneticalgorithm.algo.Parametres;
import java.util.ArrayList;

// Un individu se déplaçant dans le labyinthe
public class LabIndividu extends Individu {

    // Cnstructeur par défaut : individu aléatoire
    public LabIndividu() {
        genome = new ArrayList();
        for (int i = 0; i < Parametres.nbGenes; i++) {
            genome.add(new LabGene());
        }
    }
    
    // Constructeur avec un parent : copie puis mutation
    public LabIndividu(LabIndividu parent) {
        genome = new ArrayList();
        for (IGene g : parent.genome) {
            genome.add(new LabGene((LabGene) g));
        }
        Muter();
    }
    
    // Constructeur avec deux parents : crossover puis mutation
    public LabIndividu(LabIndividu parent1, LabIndividu parent2) {
        genome = new ArrayList();
        // Crossover
        int index = Parametres.random.nextInt(parent1.genome.size());
        for (IGene g : parent1.genome.subList(0, index)) {
            genome.add(new LabGene((LabGene) g));
        }
        if (index < parent2.genome.size()) {
            for (IGene g : parent2.genome.subList(index, parent2.genome.size())) {
                genome.add(new LabGene((LabGene) g));
            }
        }
        // Mutation
        Muter();
    }
    
    // Mutation (suppression, ajout ou modification de gènes)
    @Override
    public void Muter() {
        // Suppression d'un gène ?
        if (Parametres.random.nextDouble() < Parametres.tauxSupprGene) {
            int index = Parametres.random.nextInt(genome.size());
            genome.remove(index);
        }
        
        // Ajout d'un gène à la fin ?
        if (Parametres.random.nextDouble() < Parametres.tauxAjoutGene) {
            genome.add(new LabGene());
        }
        
        // Changement de valeurs ?
        for(IGene g : genome) {
            if (Parametres.random.nextDouble() < Parametres.tauxMutation) {
                g.Muter();
            }
        }
    }

    @Override
    public double Evaluer() {
        fitness = Labyrinthe.Evaluer(genome);
        return fitness;
    }
}
