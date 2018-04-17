package io.jsd.training.geneticalgorithm.algo;

import java.util.ArrayList;

// Système générique gérant le processus évolutionnaire
public class ProcessusEvolutionnaire {
    // Attributs
    protected ArrayList<Individu> population;
    protected int nbGeneration = 0;
    protected IHM ihm = null;
    protected double meilleureFitness;
    protected String probleme;
    
    // Constructeur
    public ProcessusEvolutionnaire(IHM _ihm, String _probleme) {
        ihm = _ihm;
        probleme = _probleme;
        FabriqueIndividus.getInstance().Init(probleme);
        population = new ArrayList();
        for (int i = 0; i < Parametres.nbIndividus; i++) {
            population.add(FabriqueIndividus.getInstance().CreerIndividu(probleme));
        }
    }
    
    // Survie : remplacement par la nouvelle population
    private void Survie(ArrayList<Individu> nelleGeneration) {
        // Remplacement
        population = nelleGeneration;
    }
    
    // Sélection : tournoi
    private Individu Selection() {
        // Choix des individus
        int index1 = Parametres.random.nextInt(Parametres.nbIndividus);
        int index2 = Parametres.random.nextInt(Parametres.nbIndividus);
        if (population.get(index1).fitness <= population.get(index2).fitness) {
            return population.get(index1);
        }
        else {
            return population.get(index2);
        }
    }
    
    // Boucle principale
    public void Run() {
        meilleureFitness = Parametres.minFitness + 1;
        while(nbGeneration < Parametres.nbMaxGenerations && meilleureFitness > Parametres.minFitness) {
            // Evaluation de tous les individus
            Individu meilleurInd = population.get(0);
            for(Individu ind : population) {
                ind.Evaluer();
                if (ind.fitness < meilleurInd.fitness) {
                    meilleurInd = ind;
                }
            }
            
            // Meilleur individu
            ihm.AfficherMeilleurIndividu(meilleurInd, nbGeneration);
            meilleureFitness = meilleurInd.fitness;
            
            // Sélection et reproduction avec élitisme
            ArrayList<Individu> nellePopulation = new ArrayList();
            nellePopulation.add(meilleurInd);
            for (int i = 0; i < Parametres.nbIndividus - 1; i++) {
                // Avec ou sans crossover ?
                if (Parametres.random.nextDouble() < Parametres.tauxCrossover) {
                    // Avec crossover, donc deux parents
                    Individu parent1 = Selection();
                    Individu parent2 = Selection();
                    // Reproduction
                    nellePopulation.add(FabriqueIndividus.getInstance().CreerIndividu(probleme, parent1, parent2));
                }
                else {
                    // Sans crossover, un seul parent
                    Individu parent = Selection();
                    // Reproduction
                    nellePopulation.add(FabriqueIndividus.getInstance().CreerIndividu(probleme, parent));
                }
            }
            
            // Survie
            Survie(nellePopulation);
            
            nbGeneration++;
        }
    }
}
