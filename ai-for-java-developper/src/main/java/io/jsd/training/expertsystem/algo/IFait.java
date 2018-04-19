package io.jsd.training.expertsystem.algo;

public interface IFait {
	
    String getNom();
    Object getValeur();
    int getNiveau();
    String getQuestion();
    void setNiveau(int niveau); 
}
