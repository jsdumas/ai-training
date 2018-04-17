package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Classe gérant la base de règles
class BaseDeRegles {
    // Liste des règles
    protected ArrayList<Regle> regles;
    public ArrayList<Regle> getRegles() {
       return regles;
    }
    public void setRegles(ArrayList<Regle> _regles) {
        // On copie les règles et on les ajoute
        for (Regle r : _regles) {
            Regle copie = new Regle(r.nom, r.premisses, r.conclusion);
            regles.add(copie);
        }
    }
   
    // Constructeur
    public BaseDeRegles() { 
        regles = new ArrayList(); 
    } 

    // Efface les règles
    public void ClearBase() 
    { 
        regles.clear(); 
    } 
  
    // Ajouter une règle à la base
    public void AjouterRegle(Regle r) 
    { 
        regles.add(r); 
    } 
  
    // Enlève une règle
    public void Effacer(Regle r) 
    { 
        regles.remove(r); 
    }
}
