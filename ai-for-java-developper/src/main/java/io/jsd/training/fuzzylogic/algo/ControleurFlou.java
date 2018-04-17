package io.jsd.training.fuzzylogic.algo;

import java.util.ArrayList;

// Classe g�rant tout le syst�me flou
public class ControleurFlou {
    protected String nom;
    protected ArrayList<VariableLinguistique> entrees;
    protected VariableLinguistique sortie;
    protected ArrayList<RegleFloue> regles;
    protected ArrayList<ValeurNumerique> probleme;
    
    // Constructeur
    public ControleurFlou(String _nom) {
        nom = _nom;
        entrees = new ArrayList();
        regles = new ArrayList();
        probleme = new ArrayList();
    }
    
    // Ajout d'une variable linguistique en entr�e
    public void AjouterVariableEntree(VariableLinguistique vl) {
        entrees.add(vl);
    }
    
    // Ajout d'une variable linguistique en sortie
    // 1 seule possible : remplace l'existante si besoin
    public void AjouterVariableSortie(VariableLinguistique vl) {
        sortie = vl;
    }
    
    // Ajout d'une r�gle
    public void AjouterRegle(RegleFloue regle) {
        regles.add(regle);
    }
    
    // Ajout d'une r�gle (format textuel)
    public void AjouterRegle(String regleStr) {
        RegleFloue regle = new RegleFloue(regleStr, this);
        regles.add(regle);
    }
    
    // Ajout d'une valeur num�rique en entr�e
    public void AjouterValeurNumerique(VariableLinguistique var, double valeur) {
        probleme.add(new ValeurNumerique(var,valeur));
    }
    
    // Remise � z�ro du probl�me (pour passer au cas suivant)
    public void EffacerValeursNumeriques() {
        probleme.clear();
    }
    
    // Retrouver une variable linguistique � partir de son nom
    public VariableLinguistique VariableLinguistiqueParNom(String nom) {
        for (VariableLinguistique var : entrees) {
            if (var.nom.equalsIgnoreCase(nom)) {
                return var;
            }
        }
        if (sortie.nom.equalsIgnoreCase(nom)) {
            return sortie;
        }
        return null;
    }
    
    // R�soud le probl�me pos�
    public double Resoudre() {
        // Initialisation de l'ensemble flou r�sultat
        EnsembleFlou resultat = new EnsembleFlou(sortie.valeurMin, sortie.valeurMax);
        resultat.Ajouter(sortie.valeurMin, 0);
        resultat.Ajouter(sortie.valeurMax, 0);
        
        // Application des r�gles et modification de l'ensemble flou r�sultant
        for(RegleFloue regle : regles) {
            resultat = resultat.Ou(regle.Appliquer(probleme));
        }
        
        // D�fuzzification
        return resultat.Barycentre();
    }
}
