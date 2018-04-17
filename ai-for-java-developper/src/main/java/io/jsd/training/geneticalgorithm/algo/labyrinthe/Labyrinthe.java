package io.jsd.training.geneticalgorithm.algo.labyrinthe;

import io.jsd.training.geneticalgorithm.algo.IGene;
import java.util.ArrayList;

// Représente un labyrinthe avec les passages possibles, l'entrée et la sortie
public class Labyrinthe {
    // Informations sur le labyrinthe
    private static ArrayList<Case[]> chemins;
    private static Case entree;
    private static Case sortie;
    
    // Directions
    public enum Direction { Haut, Bas, Gauche, Droite};
    
    // Dessins des labyrinthes
    public static String Lab1 = "*--*--*--*--*\n" + 
                                "E           |\n" + 
                                "*  *  *--*--*\n" + 
                                "|  |  |     |\n" + 
                                "*  *--*  *  *\n" + 
                                "|        |  |\n" + 
                                "*  *--*--*  *\n" + 
                                "|        |  S\n" + 
                                "*--*--*--*--*"; 
    public static String Lab2 = "*--*--*--*--*--*--*\n" + 
                                "E        |  |     |\n" + 
                                "*--*--*  *  *  *--*\n" + 
                                "|     |     |     |\n" + 
                                "*  *  *  *  *  *  *\n" + 
                                "|  |  |  |     |  |\n" + 
                                "*--*  *  *--*--*  *\n" + 
                                "|     |  |     |  |\n" + 
                                "*  *--*--*  *  *  *\n" + 
                                "|  |        |  |  |\n" + 
                                "*  *  *  *--*  *  *\n" + 
                                "|     |     |     S\n" + 
                                "*--*--*--*--*--*--*";

    public static void Init(String s) {
        chemins = new ArrayList();
        
        // On sépare puis traite chaque ligne
        String[] lignes = s.split("\n");
        int nbLignes = 0;
        for (String ligne : lignes) {
            if (nbLignes % 2 != 0) {
                // Nombre impair, donc ligne de couloir
                int index = ligne.indexOf("E");
                if (index != -1) {
                    // On a une entrée dans ce couloir
                    if (index == ligne.length() - 1) {
                        index --;
                    }
                    entree = new Case(nbLignes/2, index /3);
                }
                index = ligne.indexOf("S");
                if (index != -1) {
                    // On a une sortie dans le couloir
                    if (index == ligne.length() - 1) {
                        index--;
                    }
                    sortie = new Case(nbLignes/2, index/3);
                }
                // On parcourt le couloir pour créer les chemins horizontaux
                for (int colonne = 0; colonne < ligne.length() / 3; colonne++) {
                    String caseStr = ligne.substring(colonne*3, colonne*3 + 3);
                    if (!caseStr.contains("|") && !caseStr.contains("E") && !caseStr.contains("S")) {
                        // Passage ouvert, on l'ajoute
                        chemins.add(new Case[]{new Case(nbLignes/2, colonne-1), new Case(nbLignes/2, colonne)});
                    }
                }
            }
            else {
                // Ligne paire : on est sur les murs
                String [] cases = ligne.substring(1).split("\\*");
                int colonne = 0;
                for (String bloc : cases) {
                    if (bloc.equals("  ")) {
                        // Passage ouvert, on l'ajoute
                        chemins.add(new Case[] {new Case(nbLignes/2 - 1, colonne), new Case(nbLignes/2, colonne)});
                    }
                    colonne++;
                }
            }
            nbLignes++;
        }
    }
    
    // Indique si un déplacement entre deux cases est possible
    private static boolean estPossible(Case pos1, Case pos2) {
        for (Case[] chemin : chemins) {
            if ((chemin[0].equals(pos1) && chemin[1].equals(pos2)) || ((chemin[0].equals(pos2) && chemin[1].equals(pos1)))) {
                return true;
            }
        }
        return false;
    }
    
    // Indique si une case est un carrefour
    private static boolean estCarrefour(Case pos) {
        int nbChemins = 0;
        for (Case[] chemin : chemins) {
            if (chemin[0].equals(pos) || chemin[1].equals(pos)) {
                nbChemins++;
            }
        }
        return nbChemins > 2;
    }
    
    // Regarde si le déplacement est possible
    static void Deplacer(Case depart, int deplI, int deplJ) {
        boolean finDeplacement = false;
        while(estPossible(depart, new Case(depart.i + deplI, depart.j + deplJ)) && !finDeplacement) {
            depart.i += deplI;
            depart.j += deplJ;
            finDeplacement = estCarrefour(depart) || depart.equals(sortie);
        }
        finDeplacement = false;
    }
    
    // Déplace un individu dans le labyrinthe pour l'évaluer
    static double Evaluer(ArrayList<IGene> genome) { 
        Case caseEnCours = new Case(entree.i, entree.j);
        boolean finDeplacement = false;
        for(IGene g : genome) {
            switch (((LabGene)g).direction) {
                case Bas :
                    Deplacer(caseEnCours, 1, 0);
                    break;
                case Haut :
                    Deplacer(caseEnCours, -1, 0);
                    break;
                case Droite :
                    Deplacer(caseEnCours, 0, 1);
                    break;
                case Gauche :
                    Deplacer(caseEnCours, 0, -1);
                    break;
            }
            if (caseEnCours.equals(sortie)) {
                break;
            }
        }
        // Calcul de la fitness : distance de Manhattan
        int distance = Math.abs(sortie.i - caseEnCours.i) + Math.abs(sortie.j - caseEnCours.j);
        return distance;
    }
}
