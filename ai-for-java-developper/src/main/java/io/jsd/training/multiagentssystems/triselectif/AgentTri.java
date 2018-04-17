package io.jsd.training.multiagentssystems.triselectif;

import io.jsd.training.multiagentssystems.bancpoissons.Objet;
import java.util.ArrayList;
import java.util.Collections;

// Agent qui va trier les déchets
public class AgentTri extends Objet {
    protected final static double PAS = 3;
    protected final static double PROB_CHGT_DIRECTION = 0.05;
    
    protected Dechet charge;
    protected double vitesseX;
    protected double vitesseY;
    protected boolean occupe = false;
    
    protected void Normaliser() {
        double longueur = Math.sqrt(vitesseX * vitesseX + vitesseY * vitesseY);
        vitesseX /= longueur;
        vitesseY /= longueur;
    }
    
    public AgentTri(double _posX, double _posY) {
        posX = _posX;
        posY = _posY;
        vitesseX = Environnement.getInstance().generateur.nextDouble() - 0.5;
        vitesseY = Environnement.getInstance().generateur.nextDouble() - 0.5;
        Normaliser();
    }
    
    public boolean estCharge() {
        return charge != null;
    }
    
    public void MiseAJourPosition() {
        posX += PAS * vitesseX;
        posY += PAS * vitesseY;
        double largeur = Environnement.getInstance().getLargeur();
        double hauteur = Environnement.getInstance().getHauteur();
        if (posX < 0) {
            posX = 0;
        }
        else if (posX > largeur) {
            posX = largeur;
        }
        if (posY < 0) {
            posY = 0;
        }
        else if (posY > hauteur) {
            posY = hauteur;
        }
    }
    
    protected void MiseAJourDirection(ArrayList<Dechet> dechets) {
        // Où aller ?
        ArrayList<Dechet> dansZone = new ArrayList();
        dansZone.addAll(dechets);
        dansZone.removeIf(d -> (Distance(d) > d.ZoneInfluence()));
        Collections.sort(dansZone, (Dechet d1, Dechet d2) -> (Distance(d1) < Distance(d2) ? -1: 1));
        Dechet but = null;
        if (charge != null) {
            dansZone.removeIf(d -> d.type != charge.type);
        }
        if (!dansZone.isEmpty()) {
            but = dansZone.get(0);
        }
        
        // Avons-nous un but ?
        if (but == null || occupe) {
            // Déplacement aléatoire
            if (Environnement.getInstance().generateur.nextDouble() < PROB_CHGT_DIRECTION) {
                vitesseX = Environnement.getInstance().generateur.nextDouble() - 0.5;
                vitesseY = Environnement.getInstance().generateur.nextDouble() - 0.5;
            }
            if (occupe && but == null) {
                occupe = false;
            }
        }
        else {
            // Aller au but
            vitesseX = but.posX - posX;
            vitesseY = but.posY - posY;
            // But atteint ?
            if (Distance(but) < PAS) {
                if (charge == null) {
                    if (Environnement.getInstance().generateur.nextDouble() < but.ProbaDePrendre()) {
                        charge = Environnement.getInstance().PrendreDechet(but);
                    }
                }
                else {
                    Environnement.getInstance().PoserDechet(but);
                    charge = null;
                }
                occupe = true;
            }
        }
        Normaliser();
    }
}
