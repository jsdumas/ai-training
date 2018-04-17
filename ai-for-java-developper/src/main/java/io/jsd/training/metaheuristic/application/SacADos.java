package io.jsd.training.metaheuristic.application;

import io.jsd.training.metaheuristic.algo.Algorithme;
import io.jsd.training.metaheuristic.algo.IHM;
import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.application.AlgorithmesSacADos.DescenteGradientSacADos;
import io.jsd.training.metaheuristic.application.AlgorithmesSacADos.AlgorithmeGloutonSacADos;
import io.jsd.training.metaheuristic.application.AlgorithmesSacADos.EssaimParticulaireSacADos;
import io.jsd.training.metaheuristic.application.AlgorithmesSacADos.RechercheTabouSacADos;
import io.jsd.training.metaheuristic.application.AlgorithmesSacADos.RecuitSimuleSacADos;

// Classe principale
public class SacADos implements IHM {
    public static void main(String[] args) {
        SacADos app = new SacADos();
        app.Lancer();
    }

    @Override
    public void AfficherMessage(String msg) {
        System.out.println(msg);
    }

    private void Lancer() {
        System.out.println("Metaheuristiques d'optimisation");
        ProblemeSacADos pb = new ProblemeSacADos();
        LancerAlgorithmes(pb);
        System.out.println("*****************************************\n");
        pb = new ProblemeSacADos(100, 30, 20);
        LancerAlgorithmes(pb);
    }
    
    private void LancerAlgorithmes(IProbleme pb) {
        Algorithme algo;
        
        System.out.println("Algorithme glouton");
        algo = new AlgorithmeGloutonSacADos();
        algo.Resoudre(pb, this);
        System.out.println();
        
        System.out.println("Descente de gradient");
        algo = new DescenteGradientSacADos();
        algo.Resoudre(pb, this);
        System.out.println();
        
        System.out.println("Recherche tabou");
        algo = new RechercheTabouSacADos();
        algo.Resoudre(pb, this);
        System.out.println();
        
        System.out.println("Recuit simulé");
        algo = new RecuitSimuleSacADos();
        algo.Resoudre(pb, this);
        System.out.println();
        
        System.out.println("Essaim particulaire");
        algo = new EssaimParticulaireSacADos();
        algo.Resoudre(pb, this);
        System.out.println();
    }
}
