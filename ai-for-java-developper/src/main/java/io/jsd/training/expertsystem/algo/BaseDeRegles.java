package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Classe g�rant la base de r�gles
class BaseDeRegles {
	
    // Liste des r�gles
    protected ArrayList<Regle> regles;
    
    // Constructeur
    public BaseDeRegles() { 
        this.regles = new ArrayList(); 
    } 
    
    public ArrayList<Regle> getRegles() {
       return regles;
    }
    
    public void setRegles(ArrayList<Regle> regles) {
        // On copie les r�gles et on les ajoute
        for (Regle regle : regles) {
            Regle copie = new Regle(regle.getNom(), regle.getPremisses(), regle.getConclusion());
            regles.add(copie);
        }
    }
   
    // Efface les r�gles
    public void ClearBase()  { 
        regles.clear(); 
    } 
  
    // Ajouter une r�gle � la base
    public void AjouterRegle(Regle regle)   { 
        regles.add(regle); 
    } 
  
    // Enl�ve une r�gle
    public void Effacer(Regle regle) 
    { 
        regles.remove(regle); 
    }
}
