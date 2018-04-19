package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

class BaseDeFaits {
	
    private final ArrayList<IFait> faits; 
    
    public BaseDeFaits() { 
    	this.faits = new ArrayList<IFait>(); 
    } 
    
    public ArrayList<IFait> getFaits() { 
        return this.faits; 
    } 
  
    public void vider() {
    	this.faits.clear();
    }
    
    public void ajouterFait(IFait fait) {
    	this.faits.add(fait);
    }
    
    public IFait Chercher(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait;
            }
        }
        return null;
    }
    
    public Object recupererValeurFait(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait.getValeur();
            }
        }
        return null;
    }
}
