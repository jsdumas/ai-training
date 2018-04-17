package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Classe gérant la base de règles
class BaseDeRegles {
	
    // Liste des règles
    protected ArrayList<Regle> regles;
    
    // Constructeur
    public BaseDeRegles() { 
        this.regles = new ArrayList(); 
    } 
    
    public ArrayList<Regle> getRegles() {
       return regles;
    }
    
    public void setRegles(ArrayList<Regle> regles) {
        // On copie les règles et on les ajoute
        for (Regle regle : regles) {
            Regle copie = new Regle(regle.getNom(), regle.getPremisses(), regle.getConclusion());
            regles.add(copie);
        }
    }
   
    // Efface les règles
    public void ClearBase()  { 
        regles.clear(); 
    } 
  
    // Ajouter une règle à  la base
    public void AjouterRegle(Regle regle)   { 
        regles.add(regle); 
    } 
  
    // Enlève une règle
    public void Effacer(Regle regle) 
    { 
        regles.remove(regle); 
    }
}
