package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Classe gérant la base de faits
class BaseDeFaits {
	
    // Liste des faits
    protected final ArrayList<IFait> faits; 
    
    // Constructeur
    public BaseDeFaits() { 
    	this.faits = new ArrayList<IFait>(); 
    } 
    
    public ArrayList<IFait> getFaits() { 
        return faits; 
    } 
  
    // Vider la base
    public void vider() {
        faits.clear();
    }
    
    // Ajouter un fait
    public void ajouterFait(IFait fait) {
        faits.add(fait);
    }
    
    // Chercher un fait à  partir de son nom, null s'il est absent
    public IFait Chercher(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait;
            }
        }
        return null;
    }
    
    // Cherche la valeur d'un fait, null si le fait n'existe pas
    public Object recupererValeurFait(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait.getValeur();
            }
        }
        return null;
    }
}
