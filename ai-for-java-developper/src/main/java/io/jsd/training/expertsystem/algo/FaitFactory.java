package io.jsd.training.expertsystem.algo;

import java.util.logging.Level;
import java.util.logging.Logger;

class FaitFactory {
	
    // Cr�� un nouveau fait en remplissant la valeur donn�e par l'utilisateur
    static IFait fait(IFait fait, MoteurInferences moteur) {
        try {
            IFait nouveauFait;
            Class classe = fait.getClass();
            if (classe.equals(Class.forName("io.jsd.training.expertsystem.algo.FaitEntier"))) {
                // Cr�ation d'un fait entier
                int valeur = moteur.DemanderValeurEntiere(fait.getQuestion());
                nouveauFait = new FaitEntier(fait.getNom(), valeur, null, 0);
            }
            else {
                // C'est un fait bool�en
                boolean valeurB = moteur.DemanderValeurBooleenne(fait.getQuestion());
                nouveauFait = new FaitBooleen(fait.getNom(), valeurB, null, 0);
            }
            return nouveauFait;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaitFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
  
    // Cr�� un nouveau fait � partir de sa chaine
    static IFait fait(String faitStr) {
        faitStr = faitStr.trim();
        if (faitStr.contains("=")) {
            // Il y a le symbole "=", c'est donc un fait entier
            faitStr = faitStr.replaceFirst("^" + "\\(", "");
            String[] nomValeurQuestion = faitStr.split("[=()]");
            if (nomValeurQuestion.length >= 2) {
                // On a un fait correct Nom=Valeur[(question)]
                String question = null;
                if (nomValeurQuestion.length == 3) {
                    // Le fait contient une question
                    question = nomValeurQuestion[2].trim();
                }
                return new FaitEntier(nomValeurQuestion[0].trim(), Integer.parseInt(nomValeurQuestion[1].trim()), question, 0);
            }
        }
        else {
            // C'est un fait bool�en nom[(question)] ou !nom[(question)]
            boolean valeur = true;
            if (faitStr.startsWith("!")) {
                // C'est n�gatif
                valeur = false;
                // On enl�ve le "!"
                faitStr = faitStr.substring(1).trim();
            }
            // Split, apr�s avoir enlev� le premier d�limiteur si besoin : '('
            faitStr = faitStr.replaceFirst("^" + "\\(", "");
            String[] nomQuestion = faitStr.split("[()]");
            String question = null;
            if (nomQuestion.length == 2) {
                // Y'a une question
                question = nomQuestion[1].trim();
            }
            return new FaitBooleen(nomQuestion[0].trim(), valeur, question, 0);
        }
        // Si on arrive ici, la syntaxe est incorrecte
        return null;
    }
}
