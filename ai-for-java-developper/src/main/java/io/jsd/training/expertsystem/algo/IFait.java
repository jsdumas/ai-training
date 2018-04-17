package io.jsd.training.expertsystem.algo;

// Interface pour tous les faits, devant être implémentée
public interface IFait {
    String getNom();
    Object getValeur();
    int getNiveau();
    String getQuestion();
    
    void setNiveau(int l); // Permet de modifier le niveau d'un fait
}
