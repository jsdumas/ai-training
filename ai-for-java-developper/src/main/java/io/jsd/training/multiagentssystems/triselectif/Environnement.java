package io.jsd.training.multiagentssystems.triselectif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

// Environnement, contenant les déchets et les agents
public class Environnement extends Observable {
    // Gestion du singleton
    private static Environnement instance;
    
    public static Environnement getInstance() {
        if (instance == null) {
            instance = new Environnement();
        }
        return instance;
    }
    
    // Attributs
    protected Random generateur;
    protected double largeur;
    protected double hauteur;
    protected ArrayList<Dechet> dechets;
    protected ArrayList<AgentTri> agents;
    protected int nbIterations = 0;
    
    // Méthodes
    private Environnement() {
        dechets = new ArrayList();
        agents = new ArrayList();
        generateur = new Random();
    }
    
    public void Initialiser(int _nbDechets, int _nbAgents, double _largeur, double _hauteur, int _nbTypesDechets) {
        largeur = _largeur;
        hauteur = _hauteur;
        dechets.clear();
        for (int i = 0; i < _nbDechets; i++) {
            Dechet dechet = new Dechet(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur, generateur.nextInt(_nbTypesDechets));
            dechets.add(dechet);
        }
        agents.clear();
        for (int i = 0; i < _nbAgents; i++) {
            AgentTri agent = new AgentTri(generateur.nextDouble() * largeur, generateur.nextDouble() * hauteur);
            agents.add(agent);
        }
    }
   
    public double getLargeur() {
        return largeur;
    }
    public double getHauteur() {
        return hauteur;
    }
    
    public void PoserDechet(Dechet d) {
        d.AugmenterTaille();        
    }
    
    public Dechet PrendreDechet(Dechet d) {
        if (d.taille == 1) {
            dechets.remove(d);
            return d;
        }
        else {
            d.DiminuerTaille();
            Dechet charge = new Dechet(d);
            return charge;
        }
    }
    
    public void MiseAJour() {
        for (AgentTri agent : agents) {
            agent.MiseAJourDirection(dechets);
            agent.MiseAJourPosition();
        }
        
        nbIterations++;
        if (nbIterations % 500 == 0) {
            Collections.reverse(dechets);
        }
        
        setChanged();
        notifyObservers();
    }
}
