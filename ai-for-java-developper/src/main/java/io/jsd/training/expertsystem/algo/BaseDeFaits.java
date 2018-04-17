package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Classe gérant la base de faits
class BaseDeFaits {
    // Liste des faits
    protected ArrayList<IFait> faits; 
    public ArrayList<IFait> getFaits ()
    { 
        return faits; 
    } 
  
    // Constructeur
    public BaseDeFaits() 
    { 
        faits = new ArrayList<IFait>(); 
    } 

    // Vider la base
    public void Vider() {
        faits.clear();
    }
    
    // Ajouter un fait
    public void AjouterFait(IFait fait) {
        faits.add(fait);
    }
    
    // Chercher un fait à partir de son nom, null s'il est absent
    public IFait Chercher(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait;
            }
        }
        return null;
    }
    
    // Cherche la valeur d'un fait, null si le fait n'existe pas
    public Object RecupererValeurFait(String nom) {
        for(IFait fait : faits) {
            if (fait.getNom().equals(nom)) {
                return fait.getValeur();
            }
        }
        return null;
    }
}
