package io.jsd.training.multiagentssystems.jeudelavie;

import java.util.Observable;
import java.util.Random;

// Grille représentant l'environnement du jeu de la vie + les cellules
public class Grille extends Observable {
    protected int largeur;
    protected int hauteur;
    protected boolean[][] contenu;
    
    public Grille(int _largeur, int _hauteur, double _densite) {
        largeur = _largeur;
        hauteur = _hauteur;
        Random generateur = new Random();
        
        contenu = new boolean[largeur][hauteur];
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                if (generateur.nextDouble() < _densite) {
                    contenu[i][j] = true;
                }
            }
        }
    }
    
    public void ChangerEtat(int ligne, int colonne) {
        contenu[ligne][colonne] = !contenu[ligne][colonne];
    }
    
    public int NbVoisinsVivants(int colonne, int ligne) {
        int i_min = Math.max(0, colonne-1);
        int i_max = Math.min(largeur-1, colonne+1);
        int j_min = Math.max(0, ligne-1);
        int j_max = Math.min(hauteur-1, ligne+1);
        int nb = 0;
        for (int i = i_min; i <= i_max; i++) {
            for (int j = j_min; j <= j_max; j++) {
                if (contenu[i][j] && !(i==colonne && j==ligne)) {
                    nb++;
                }
            }
        }
        return nb;
    }
    
    public void MiseAJour(boolean avecApplication) {
        if (avecApplication) {
            boolean[][] nouvelleGrille = new boolean[largeur][hauteur];
            for (int i = 0; i < largeur; i++) {
                for (int j = 0; j < hauteur; j++) {
                    int nb = NbVoisinsVivants(i, j);
                    if (nb == 3 || (nb == 2 && contenu[i][j])) {
                        nouvelleGrille[i][j] = true;
                    }
                }
            }
            contenu = nouvelleGrille;
        }
        setChanged();
        notifyObservers();
    }
}
