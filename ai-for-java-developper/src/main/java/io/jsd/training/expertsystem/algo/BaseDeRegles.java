package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

class BaseDeRegles {
	
    protected ArrayList<Regle> regles;
    
    public BaseDeRegles() { 
        this.regles = new ArrayList<Regle>(); 
    } 
    
    public ArrayList<Regle> getRegles() {
       return this.regles;
    }
    
    public void setRegles(ArrayList<Regle> regles) {
        // On copie les règles et on les ajoute
        for (Regle regle : regles) {
            Regle copie = new Regle(regle.getNom(), regle.getPremisses(), regle.getConclusion());
            this.regles.add(copie);
        }
    }
   
    public void ClearBase()  { 
        this.regles.clear(); 
    } 
  
    public void AjouterRegle(Regle regle)   { 
    	this.regles.add(regle); 
    } 
  
    public void Effacer(Regle regle)  { 
        regles.remove(regle); 
    }
}
