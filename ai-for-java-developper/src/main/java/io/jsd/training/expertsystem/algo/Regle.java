package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Regle {
	
    private ArrayList<IFait> premisses;
    private IFait conclusion;
    private String nom;
    
    
    public Regle(String nom, ArrayList<IFait> premisses, IFait conclusion) { 
        this.nom = nom; 
        this.premisses = premisses; 
        this.conclusion = conclusion; 
    } 
    
    public ArrayList<IFait> getPremisses() {
        return this.premisses;
    }
    
    public void setPremisses(ArrayList<IFait> premisses) {
    	this.premisses = premisses;
    }
    
    public IFait getConclusion() {
        return this.conclusion;
    }
    
    public void setConclusion(IFait conclusion) {
        this.conclusion = conclusion;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    
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
