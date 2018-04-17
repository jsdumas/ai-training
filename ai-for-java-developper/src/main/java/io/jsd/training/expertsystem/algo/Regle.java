package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;
import java.util.StringJoiner;

// Représente une règle du système expert
public class Regle {
    // Liste des prémisses (partie gauche)
    protected ArrayList<IFait> premisses;
    public ArrayList<IFait> getPremisses() {
        return premisses;
    }
    public void setPremisses(ArrayList<IFait> _premisses) {
        premisses = _premisses;
    }
    
    // Conclusion de la règle (partie droite)
    protected IFait conclusion;
    public IFait getConclusion() {
        return conclusion;
    }
    public void setConclusion(IFait _conclusion) {
        conclusion = _conclusion;
    }
    
    // Nom de la règle
    protected String nom;
    public String getNom() {
        return nom;
    }
    public void setNom(String _nom) {
        nom = _nom;
    }
    
    // Constructeur
    public Regle(String _nom, ArrayList<IFait> _premisses, IFait _conclusion) { 
        nom = _nom; 
        premisses = _premisses; 
        conclusion = _conclusion; 
    } 

    // Méthode affichant la règle
    @Override
    public String toString() { 
        String chaine = nom + " : IF (";
        
        StringJoiner sj = new StringJoiner(" AND ");
        for(IFait fait : premisses) {
            sj.add(fait.toString());
        }
        
        chaine += sj.toString() + ") THEN " + conclusion.toString();
        return chaine;
   }

}
