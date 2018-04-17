package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;
import java.util.StringJoiner;

// Représente une règle du système expert
public class Regle {
	
    // Liste des prémisses (partie gauche)
    protected ArrayList<IFait> premisses;
 // Conclusion de la règle (partie droite)
    protected IFait conclusion;
    // Nom de la règle
    protected String nom;
    
    
    // Constructeur
    public Regle(String _nom, ArrayList<IFait> _premisses, IFait _conclusion) { 
        this.nom = _nom; 
        this.premisses = _premisses; 
        this.conclusion = _conclusion; 
    } 
    
    public ArrayList<IFait> getPremisses() {
        return this.premisses;
    }
    
    public void setPremisses(ArrayList<IFait> _premisses) {
    	this.premisses = _premisses;
    }
    
    public IFait getConclusion() {
        return this.conclusion;
    }
    
    public void setConclusion(IFait _conclusion) {
        this.conclusion = _conclusion;
    }
    
    public String getNom() {
        return this.nom;
    }
    public void setNom(String _nom) {
    	this.nom = _nom;
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
