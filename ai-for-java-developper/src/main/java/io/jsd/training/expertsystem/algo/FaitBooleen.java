package io.jsd.training.expertsystem.algo;

// Classe pour les faits bool�ens (comme le fait d'�tre ou non un triangle)
class FaitBooleen implements IFait {

    // Nom du fait
    protected final String nom;
    // Valeur bool�enne du fait
    protected final boolean valeur;
    // Niveau (0 pour les faits en entr�e)
    protected int niveau;
    // Question � poser � l'utilisateur si besoin
    protected String question;
    
    
    
 // Constructeur
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

    // M�thode toString (pour l'affichage)
    // de la forme Fait(niveau) ou !Fait(niveau)
    @Override
    public String toString()     { 
        String res = ""; 
        if (!valeur) 
        { 
            res += "!"; 
        } 
        res += nom + " (" + niveau + ")"; 
        return res; 
    } 
}
