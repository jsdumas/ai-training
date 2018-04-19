package io.jsd.training.expertsystem.algo;

// Classe pour les faits booléens (comme le fait d'àªtre ou non un triangle)
class FaitBooleen implements IFait {

    private final String nom;
    private final boolean valeur;
    // Niveau (0 pour les faits en entrée)
    private int niveau;
    private String question;
    
    public FaitBooleen(String nom, boolean valeur, String question, int niveau) {
        this.nom = nom;
        this.valeur = valeur;
        this.question = question;
        this.niveau = niveau;
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

    public void setNiveau(int n) {
        niveau = n;
    }
    
    public String getQuestion() {
        return question;
    }

    @Override
    public String toString()     { 
        String res = ""; 
        if (!valeur)  { 
            res += "!"; 
        } 
        res += nom + " (" + niveau + ")"; 
        return res; 
    } 
}
