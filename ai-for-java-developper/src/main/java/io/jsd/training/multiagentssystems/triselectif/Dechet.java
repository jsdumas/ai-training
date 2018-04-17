package io.jsd.training.multiagentssystems.triselectif;

import io.jsd.training.multiagentssystems.bancpoissons.Objet;

// Déchats dans l'environnement
public class Dechet extends Objet {
    protected final static double DECROISSANCE = 0.6;
    
    protected int type;
    protected int taille = 1;
    
    public int getType() {
        return type;
    }
    
    public int getTaille() {
        return taille;
    }
    
    public Dechet(double _posX, double _posY, int _type) {
        type = _type;
        posX = _posX;
        posY = _posY;
    }
    
    public Dechet(Dechet d) {
        posX = d.posX;
        posY = d.posY;
        type = d.type;
    }
    
    public int ZoneInfluence() {
        return 10 + 8 * (taille - 1);
    }
    
    protected void AugmenterTaille() {
        taille++;
    }
    
    protected void DiminuerTaille() {
        taille--;
    }
    
    protected double ProbaDePrendre() {
        return Math.pow(DECROISSANCE, taille-1);
    }
}
