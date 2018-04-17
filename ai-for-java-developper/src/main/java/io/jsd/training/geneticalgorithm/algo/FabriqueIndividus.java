package io.jsd.training.geneticalgorithm.algo;

import io.jsd.training.geneticalgorithm.algo.PVC.PVC;
import io.jsd.training.geneticalgorithm.algo.PVC.PVCIndividu;
import io.jsd.training.geneticalgorithm.algo.labyrinthe.LabIndividu;
import io.jsd.training.geneticalgorithm.algo.labyrinthe.Labyrinthe;

// Fabrique d'individus adaptés aux problème (classe singleton)
class FabriqueIndividus {
    private static FabriqueIndividus instance;
    
    private FabriqueIndividus() {}
    
    public static FabriqueIndividus getInstance() {
        if (instance == null) {
            instance = new FabriqueIndividus();
        }
        return instance;
    }
    
    void Init(String type) {
        switch (type) {
            case "PVC" :
                PVC.Init();
                break;
            case "Lab" :
                Labyrinthe.Init(Labyrinthe.Lab2);
                break;
        }
    }
    
    public Individu CreerIndividu(String type) {
        Individu ind = null;
        switch (type) {
            case "PVC" :
                ind = new PVCIndividu();
                break;
            case "Lab" :
                ind = new LabIndividu();
                break;
        }
        return ind;
    }
    
    public Individu CreerIndividu(String type, Individu parent) {
        Individu ind = null;
        switch (type) {
            case "PVC" :
                ind = new PVCIndividu((PVCIndividu)parent);
                break;
            case "Lab" :
                ind = new LabIndividu((LabIndividu)parent);
                break;
        }
        return ind;
    }
    
    public Individu CreerIndividu(String type, Individu parent1, Individu parent2) {
        Individu ind = null;
        switch (type) {
            case "PVC" :
                ind = new PVCIndividu((PVCIndividu)parent1, (PVCIndividu)parent2);
                break;
            case "Lab" :
                ind = new LabIndividu((LabIndividu)parent1, (LabIndividu)parent2);
                break;
        }
        return ind;
    }
}
