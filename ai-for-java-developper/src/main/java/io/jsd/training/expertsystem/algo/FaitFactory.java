package io.jsd.training.expertsystem.algo;

import java.util.logging.Level;
import java.util.logging.Logger;

// Classe permettant de créer les faits, quel que soit leur type
class FaitFactory {
    // Créé un nouveau fait en remplissant la valeur donnée par l'utilisateur
    static IFait Fait(IFait f, MoteurInferences m) {
        try {
            IFait nouveauFait;
            Class classe = f.getClass();
            if (classe.equals(Class.forName("systemeexpert.FaitEntier"))) {
                // Création d'un fait entier
                int valeur = m.DemanderValeurEntiere(f.getQuestion());
                nouveauFait = new FaitEntier(f.getNom(), valeur, null, 0);
            }
            else {
                // C'est un fait booléen
                boolean valeurB = m.DemanderValeurBooleenne(f.getQuestion());
                nouveauFait = new FaitBooleen(f.getNom(), valeurB, null, 0);
            }
            return nouveauFait;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FaitFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
  
    // Créé un nouveau fait Ã  partir de sa chaine
    static IFait Fait(String faitStr) {
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
            // C'est un fait booléen nom[(question)] ou !nom[(question)]
            boolean valeur = true;
            if (faitStr.startsWith("!")) {
                // C'est négatif
                valeur = false;
                // On enlÃ¨ve le "!"
                faitStr = faitStr.substring(1).trim();
            }
            // Split, aprÃ¨s avoir enlevé le premier délimiteur si besoin : '('
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
