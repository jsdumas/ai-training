package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

// Moteur d'inférences du système expert, à chaînage avant
public class MoteurInferences {
    private BaseDeFaits bdf;
    private BaseDeRegles bdr;
    private IHM ihm;
    
    private int niveauMaxRegle;
    
    // Constructeur
    public MoteurInferences(IHM _ihm) {
        ihm = _ihm;
        bdf = new BaseDeFaits();
        bdr = new BaseDeRegles();
    }
    
    // Demande une valeur entière à l'ihm
    int DemanderValeurEntiere(String question) {
        return ihm.demanderValeurEntiere(question);
    }
    
    // Demande une valeur booléenne à l'ihm
    boolean DemanderValeurBooleenne(String question) {
        return ihm.demanderValeurBooleenne(question);
    }
    
    // Indique si une règle passée en paramètre est applicable. 
    // Si oui, renvoie son niveau, sinon renvoie -1
    int EstApplicable(Regle _r) {
        int niveauMax = -1;
        // On vérifie la véracité de chaque prémisse
        for (IFait f : _r.getPremisses()) {
            IFait faitTrouve = bdf.Chercher(f.getNom());
            if (faitTrouve == null) {
                // Ce fait n'existe pas en base de faits
                if (f.getQuestion() != null) {
                    // On le demande (et on l'ajoute)
                    faitTrouve = FaitFactory.Fait(f, this);
                    bdf.AjouterFait(faitTrouve);
                }
                else {
                    // La règle ne peut pas s'appliquer
                    return -1;
                }
            }
            
            // Le fait existe en base (avant ou créé), mais avec la bonne valeur ?
            if (!faitTrouve.getValeur().equals(f.getValeur())) {
                // Ca ne correspond pas
                return -1;
            }
            else {
                // Ca correspond, on met à jour le niveau
                niveauMax = Math.max(niveauMax, faitTrouve.getNiveau());
            }
        }
        return niveauMax;
    }
    
    // Renvoie la premièe règle applicable de la base passée en paramètre
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
    
    // Algorithme principal permettant de résoudre un cas donné
    public void Resoudre() {
        // On copie toutes les règles
        BaseDeRegles bdrLocale = new BaseDeRegles();
        bdrLocale.setRegles(bdr.getRegles());
        
        // On vide la base de faits
        bdf.Vider();
        
        // Tant qu'il existe des règles à appliquer
        Regle r = TrouverRegle(bdrLocale);
        while(r!=null) {
            // Appliquer la règle
            IFait nouveauFait = r.conclusion;
            nouveauFait.setNiveau(niveauMaxRegle + 1);
            bdf.AjouterFait(nouveauFait);
            // Enlever la règle des possibles
            bdrLocale.Effacer(r);
            // Chercher la prochaine règle applicable
            r = TrouverRegle(bdrLocale);
        }
        
        // Affichage des résultats
        ihm.afficherFaits(bdf.getFaits());
    }
    
    // Ajoute une règle à la base à partir de sa chaine
    // Sous la forme :
    // Nom : IF premisses THEN conclusion
    public void AjouterRegle(String str) {
        // Séparation nom:règle
        String[] nomRegle = str.split(":");
        if (nomRegle.length == 2) {
            String nom = nomRegle[0].trim();
            // Séparation premisses THEN conclusion
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
                
                // Création de la règle et ajout à la base
                bdr.AjouterRegle(new Regle(nom, premisses, conclusion));
            }
        }
    }
}
