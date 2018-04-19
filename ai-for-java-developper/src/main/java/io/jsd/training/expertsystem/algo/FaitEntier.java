package io.jsd.training.expertsystem.algo;

// Classe pour les faits entiers (comme le nombre de côtés)
class FaitEntier implements IFait {
	
	private final String nom;  
	private final int valeur;
 // Niveau (0 pour les faits en entrée)
	private int niveau;
	private String question = "";
	
    public FaitEntier(String nom, int valeur, String question, int niveau) {
        this.nom = nom;
        this.valeur = valeur;
        this.niveau = niveau;
        this.question = question;
    }
    
    public String getNom() {
        return nom;
    }
   
    public Object getValeur() {
        return valeur;
    }
    
    public int getNiveau() {
        return niveau;
    }
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return nom + "=" + valeur + " (" + niveau + ")";
    }
}
