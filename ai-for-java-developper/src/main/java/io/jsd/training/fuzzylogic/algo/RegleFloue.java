package io.jsd.training.fuzzylogic.algo;

import java.util.ArrayList;

// Classe repr�sentant une r�gle floue, avec plusieurs pr�misses et une conclusion
public class RegleFloue {
    protected ArrayList<ExpressionFloue> premisses;
    protected ExpressionFloue conclusion;
    
    // Constructeur
    public RegleFloue(ArrayList<ExpressionFloue> _premisses, ExpressionFloue _conclusion) {
        premisses = _premisses;
        conclusion = _conclusion;
    }

    // Constructeur � partir d'une chaine de caract�res
    public RegleFloue(String regleStr, ControleurFlou controleur) {
        // Passage de la r�gle en majuscule
        regleStr = regleStr.toUpperCase();
        
        // S�paration pr�misses / conclusion (mot-cl� THEN)
        String[] regle = regleStr.split(" THEN ");
        if (regle.length == 2) {
            // On traite les pr�misses en enlevant le IF puis en coupant sur AND
            regle[0] = regle[0].replaceFirst("IF ", "").trim();
            String[] premissesStr = regle[0].split(" AND ");
            premisses = new ArrayList();
            for(String exp : premissesStr) {
                // On coupe sur le mot-cl� "IS", et on cr�� une expression floue
                String[] parties = exp.trim().split(" IS ");
                if (parties.length == 2) {
                    ExpressionFloue expFloue = new ExpressionFloue(controleur.VariableLinguistiqueParNom(parties[0]), parties[1]);
                    premisses.add(expFloue);
                }
            }
            // On traite la conclusion
            String[] concluStr = regle[1].trim().split(" IS ");
            if (concluStr.length == 2) {
                conclusion = new ExpressionFloue(controleur.VariableLinguistiqueParNom(concluStr[0]), concluStr[1]);
            }
        }
    }
    
    // Appliquer la r�gle � un probl�me num�rique donn�
    // Cela produit un ensemble flou
    EnsembleFlou Appliquer(ArrayList<ValeurNumerique> probleme) {
        double degre = 1;
        // On cherche le degr� de chaque pr�misse
        for (ExpressionFloue premisse : premisses) {
            double degreLocal = 0;
            ValeurLinguistique vl = null;
            // On r�cup�re la valeur pour cette premisse dans le probl�me � r�soudre
            for (ValeurNumerique pb : probleme) {
                if (premisse.varL.equals(pb.vl)) {
                    // On a trouv� la bonne donn�e du probl�me, on r�cup�re son appartenance
                    vl = premisse.varL.ValeurLinguistiqueParNom(premisse.nomValeurLinguistique);
                    if (vl != null) {
                        degreLocal = vl.ValeurDAppartenance(pb.valeur);
                        break;
                    }
                }
            }
            if (vl == null) {
                // Il nous manque au moins une donn�e, on ne peut pas r�soudre
                return null;
            }
            // On garde le degr� min
            degre = Math.min(degre, degreLocal);
        }
        return conclusion.varL.ValeurLinguistiqueParNom(conclusion.nomValeurLinguistique).ensembleFlou.MultipliePar(degre);
    }
}
