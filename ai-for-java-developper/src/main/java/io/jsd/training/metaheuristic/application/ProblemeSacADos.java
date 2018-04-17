package io.jsd.training.metaheuristic.application;

import io.jsd.training.metaheuristic.algo.IProbleme;
import io.jsd.training.metaheuristic.algo.ISolution;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// Le problème du sac à dos à résoudre (maximiser la valeur sans dépasser le poids)
public class ProblemeSacADos implements IProbleme {
    protected ArrayList<Boite> boitesDispo = null;
    public double poidsMax;
    public static Random generateur = null;
    public final static int NB_VOISINS = 30;
    
    // Problème du livre (12 boîtes)
    public ProblemeSacADos() {
        // Liste des boites
        boitesDispo = new ArrayList();
        boitesDispo.add(new Boite("A", 4, 15)); 
        boitesDispo.add(new Boite("B", 7, 15)); 
        boitesDispo.add(new Boite("C", 10, 20)); 
        boitesDispo.add(new Boite("D", 3, 10)); 
        boitesDispo.add(new Boite("E", 6, 11)); 
        boitesDispo.add(new Boite("F", 12, 16)); 
        boitesDispo.add(new Boite("G", 11, 12)); 
        boitesDispo.add(new Boite("H", 16, 22)); 
        boitesDispo.add(new Boite("I", 5, 12)); 
        boitesDispo.add(new Boite("J", 14, 21)); 
        boitesDispo.add(new Boite("K", 4, 10)); 
        boitesDispo.add(new Boite("L", 3, 7)); 

        poidsMax = 20;
        if (generateur == null) {
            generateur = new Random();
        }
    }
    
    // Constructeur de problèmes aléatoires
    public ProblemeSacADos(int nbBoites, double _poidsMax, double valeurMax) {
        boitesDispo = new ArrayList();
        poidsMax = _poidsMax;
        if (generateur == null) {
            generateur = new Random();
        }
        for (int i = 0; i < nbBoites; i++) {
            boitesDispo.add(new Boite(Integer.toString(i), generateur.nextDouble() * poidsMax, generateur.nextDouble() * valeurMax));
        }
    }
    
    public ArrayList<Boite> Boites() {
        ArrayList<Boite> copie = new ArrayList();
        copie.addAll(boitesDispo);
        return copie;
    }
    
    @Override
    public ISolution SolutionAleatoire() {
        SolutionSacADos solution = new SolutionSacADos();
        ArrayList<Boite> boitesPossibles = Boites();
        double espaceDispo = poidsMax;
        // On enlève les trop lourdes
        EliminerTropLourdes(boitesPossibles, espaceDispo);
        while(espaceDispo > 0 && !boitesPossibles.isEmpty()) {
            // Choix aléatoire d'une boite
            int index = generateur.nextInt(boitesPossibles.size());
            // Mise à jour de la solution
            Boite b = boitesPossibles.get(index);
            solution.contenu.add(b);
            boitesPossibles.remove(index);
            espaceDispo -= b.poids;
            // Mise à jour de la liste
            EliminerTropLourdes(boitesPossibles, espaceDispo);
        }
        return solution;
    }
    
    public void EliminerTropLourdes(ArrayList<Boite> boitesPossibles, double espaceDispo) {
        Iterator<Boite> iterateur = boitesPossibles.iterator();
        while (iterateur.hasNext()) {
            Boite b = iterateur.next();
            if (b.poids > espaceDispo) {
                iterateur.remove();
            }
        }
    }
    
    @Override
    public ISolution MeilleureSolution(ArrayList<ISolution> solutions) {
        if (!solutions.isEmpty()) {
            ISolution meilleure = solutions.get(0);
            for (ISolution sol : solutions) {
                if (sol.getValeur() > meilleure.getValeur()) {
                    meilleure = sol;
                }
            }
            return meilleure;
        }
        else {
            return null;
        }
    }
    
    @Override
    public ArrayList<ISolution> Voisinage(ISolution solutionCourante) {
        ArrayList<ISolution> voisinage = new ArrayList();
        for (int i = 0; i < NB_VOISINS; i++) {
            // Création d'un clone
            SolutionSacADos solution = new SolutionSacADos((SolutionSacADos) solutionCourante);
            // On enlève un élément
            int index = generateur.nextInt(solution.contenu.size());
            solution.contenu.remove(index);
            // Calcul du poids et des boites disponibles
            ArrayList<Boite> boitesPossibles = Boites();
            double espaceDispo = poidsMax - solution.getPoids();
            boitesPossibles.removeAll(solution.contenu);
            EliminerTropLourdes(boitesPossibles, espaceDispo);
            // On ajoute des boites
            while(espaceDispo > 0 && !boitesPossibles.isEmpty()) {
                // Choix aléatoire d'une boite
                index = generateur.nextInt(boitesPossibles.size());
                // Mise à jour de la solution
                Boite b = boitesPossibles.get(index);
                solution.contenu.add(b);
                boitesPossibles.remove(index);
                espaceDispo -= b.poids;
                // Mise à jour de la liste
                EliminerTropLourdes(boitesPossibles, espaceDispo);
            }
            voisinage.add(solution);
        }
        return voisinage;
    }
}
