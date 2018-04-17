package io.jsd.training.geneticalgorithm.algo.labyrinthe;

import io.jsd.training.geneticalgorithm.algo.IGene;
import io.jsd.training.geneticalgorithm.algo.Parametres;

// Les gènes pour le labyrinthe
public class LabGene implements IGene {
    public Labyrinthe.Direction direction;
    
    public LabGene() {
        direction = Labyrinthe.Direction.values()[Parametres.random.nextInt(4)];
    }
    
    public LabGene(LabGene g) {
        direction = g.direction;
    }
    
    @Override
    public String toString() {
        return direction.name().substring(0, 1);
    }
    
    @Override
    public void Muter() {
        direction = Labyrinthe.Direction.values()[Parametres.random.nextInt(4)];
    }

}
