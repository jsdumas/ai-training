package io.jsd.training.geneticalgorithm.algo.PVC;

import io.jsd.training.geneticalgorithm.algo.IGene;
import io.jsd.training.geneticalgorithm.algo.Individu;
import io.jsd.training.geneticalgorithm.algo.Parametres;
import java.util.ArrayList;

// Individu du problème du voyageur de commerce
public class PVCIndividu extends Individu {

    // Constructeur par défaut : choix aléatoire du parcours
    public PVCIndividu() {
        genome = new ArrayList();
        ArrayList<Integer> indexDispo = PVC.getVillesIndex();
        while(!indexDispo.isEmpty()) {
            int index = Parametres.random.nextInt(indexDispo.size());
            genome.add(new PVCGene(indexDispo.get(index)));
            indexDispo.remove(index);
        }
    }
    
    // Mutation : on va déplacer un gène
    @Override
    public void Muter() {
        if (Parametres.random.nextDouble() < Parametres.tauxMutation) {
            int index1 = Parametres.random.nextInt(genome.size());
            PVCGene g = (PVCGene)genome.get(index1);
            genome.remove(g);
            int index2 = Parametres.random.nextInt(genome.size());
            genome.add(index2, g);
        }
    }
    
    // Constructeur avec un parent
    public PVCIndividu(PVCIndividu parent) {
        genome = new ArrayList();
        for (IGene g : parent.genome) {
            this.genome.add(new PVCGene((PVCGene)g));
        }
        Muter();
    }

    // Constructeur avec deux parents
    public PVCIndividu(PVCIndividu parent1, PVCIndividu parent2) {
        genome = new ArrayList();
        // Crossover
        int ptCoupure = Parametres.random.nextInt(parent1.genome.size());
        for(int i = 0; i < ptCoupure; i++) {
            genome.add(new PVCGene((PVCGene) parent1.genome.get(i)));
        }
        for (IGene g : parent2.genome) {
            if (!genome.contains((PVCGene)g)) {
                genome.add(new PVCGene((PVCGene)g));
            }
        }
        // Mutation
        Muter();
    }
    
    // Evaluation d'un individu : calcul des distances
    @Override
    public double Evaluer() {
        int kmTotal = 0;
        PVCGene ancienGene = null;
        for (IGene g : genome) {
            if (ancienGene != null) {
                kmTotal += ((PVCGene)g).getDistance(ancienGene);
            }
            ancienGene = (PVCGene)g;
        }
        kmTotal += ancienGene.getDistance((PVCGene)genome.get(0));
        fitness = kmTotal;
        return fitness;
    }

}
