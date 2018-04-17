package io.jsd.training.expertsystem.algo;

// Classe pour les faits entiers (comme le nombre de cÃ´tÃ©s)
class FaitEntier implements IFait {
	
	 // Nom du fait
    protected final String nom;  
    // Valeur entière associée
    protected final int valeur;
 // Niveau (0 pour les faits en entrée)
    protected int niveau;
 // Question Ã  poser Ã  l'utilisateur si besoin
    protected String question = "";
	
	// Constructeur
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

    // Méthode toString (pour l'affichage)
    @Override
    public String toString() {
        return nom + "=" + valeur + " (" + niveau + ")";
    }
}
