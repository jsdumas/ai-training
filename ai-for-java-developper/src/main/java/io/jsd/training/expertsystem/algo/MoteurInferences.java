package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Moteur d'inf�rences du syst�me expert, � cha�nage avant
public class MoteurInferences {
	
    private BaseDeFaits baseDeFaits;
    private BaseDeRegles baseDeRegles;
    private IHM ihm;
    private int niveauMaxRegle;
    
    // Constructeur
    public MoteurInferences(IHM ihm) {
        this.ihm = ihm;
        this.baseDeFaits = new BaseDeFaits();
        this.baseDeRegles = new BaseDeRegles();
    }
    
    // Demande une valeur enti�re � l'ihm
    int DemanderValeurEntiere(String question) {
        return ihm.demanderValeurEntiere(question);
    }
    
    // Demande une valeur bool�enne � l'ihm
    boolean DemanderValeurBooleenne(String question) {
        return ihm.demanderValeurBooleenne(question);
    }
    
    // Indique si une r�gle pass�e en param�tre est applicable. 
    // Si oui, renvoie son niveau, sinon renvoie -1
    int EstApplicable(Regle _r) {
        int niveauMax = -1;
        // On v�rifie la v�racit� de chaque pr�misse
        for (IFait f : _r.getPremisses()) {
            IFait faitTrouve = baseDeFaits.Chercher(f.getNom());
            if (faitTrouve == null) {
                // Ce fait n'existe pas en base de faits
                if (f.getQuestion() != null) {
                    // On le demande (et on l'ajoute)
                    faitTrouve = FaitFactory.Fait(f, this);
                    baseDeFaits.ajouterFait(faitTrouve);
                }
                else {
                    // La r�gle ne peut pas s'appliquer
                    return -1;
                }
            }
            
            // Le fait existe en base (avant ou cr��), mais avec la bonne valeur ?
            if (!faitTrouve.getValeur().equals(f.getValeur())) {
                // Ca ne correspond pas
                return -1;
            }
            else {
                // Ca correspond, on met � jour le niveau
                niveauMax = Math.max(niveauMax, faitTrouve.getNiveau());
            }
        }
        return niveauMax;
    }
    
    // Renvoie la premi�e r�gle applicable de la base pass�e en param�tre
    // S'il y en a une, remplit aussi l'attribut de la classe (niveauMaxRegle)
    // sinon renvoie null
    Regle TrouverRegle(BaseDeRegles bdrLocale) {
        for(Regle r : bdrLocale.getRegles()) {
            int niveau = EstApplicable(r);
            if (niveau != -1) {
                niveauMaxRegle = niveau;
                return r;
            }
        }
        return null;
    }
    
    // Algorithme principal permettant de r�soudre un cas donn�
    public void resoudre() {
        // On copie toutes les r�gles
        BaseDeRegles bdrLocale = new BaseDeRegles();
        bdrLocale.setRegles(this.baseDeRegles.getRegles());
        
        // On vide la base de faits
        baseDeFaits.vider();
        
        // Tant qu'il existe des r�gles � appliquer
        Regle r = TrouverRegle(bdrLocale);
        while(r!=null) {
            // Appliquer la r�gle
            IFait nouveauFait = r.conclusion;
            nouveauFait.setNiveau(niveauMaxRegle + 1);
            baseDeFaits.ajouterFait(nouveauFait);
            // Enlever la r�gle des possibles
            bdrLocale.Effacer(r);
            // Chercher la prochaine r�gle applicable
            r = TrouverRegle(bdrLocale);
        }
        
        // Affichage des r�sultats
        ihm.afficherFaits(baseDeFaits.getFaits());
    }
    
    // Ajoute une r�gle � la base � partir de sa chaine
    // Sous la forme :
    // Nom : IF premisses THEN conclusion
    public void ajouterRegle(String str) {
        // S�paration nom:r�gle
        String[] nomRegle = str.split(":");
        if (nomRegle.length == 2) {
            String nom = nomRegle[0].trim();
            // S�paration premisses THEN conclusion
            String regle = nomRegle[1].trim();
            regle = regle.replaceFirst("^" + "IF", "");
            String[] premissesConclusion = regle.split("THEN");
            if (premissesConclusion.length == 2) {
                // Lecture des premisses
                ArrayList<IFait> premisses = new ArrayList();
                String[] premissesStr = premissesConclusion[0].split(" AND ");
                for(String chaine : premissesStr) {
                    IFait premisse = FaitFactory.Fait(chaine.trim());
                    premisses.add(premisse);
                }
                
                // Lecture de la conclusion
                String conclusionStr = premissesConclusion[1].trim();
                IFait conclusion = FaitFactory.Fait(conclusionStr);
                
                // Cr�ation de la r�gle et ajout � la base
                baseDeRegles.AjouterRegle(new Regle(nom, premisses, conclusion));
            }
        }
    }
}
