package io.jsd.training.neuralnetworks.algo;

// Classe gérant le réseau et l'apprentissage
public class Systeme {
    protected CollectionPoints donnees;
    protected ReseauNeurones reseau;
    protected IHM ihm;
    
    protected double tauxApprentissage = 0.3;
    protected double erreurMax = 0.005;
    protected int nbIterationsMax = 10001;
    
    public Systeme(int _nbEntrees, int _nbCaches, int _nbSorties, String[] _donnees, double _ratioApprentissage, IHM _ihm) {
        donnees = new CollectionPoints(_donnees, _nbSorties, _ratioApprentissage);
        reseau = new ReseauNeurones(_nbEntrees, _nbCaches, _nbSorties);
        ihm = _ihm;
    }
    
    public void setTauxApprentissage(double valeur) {
        tauxApprentissage = valeur;
    }
    
    public void setErreurMax(double valeur) {
        erreurMax = valeur;
    }
    
    public void setNbIterationsMax(int valeur) {
        nbIterationsMax = valeur;
    }
    
    public void Lancer() {
        // Initialisation
        int nbIterations = 0;
        double erreurTotale = Double.POSITIVE_INFINITY;
        double ancienneErreur = Double.POSITIVE_INFINITY;
        double erreurGeneralisationTotale = Double.POSITIVE_INFINITY;
        double ancienneErreurGeneralisation = Double.POSITIVE_INFINITY;
        int nbSurapprentissage = 0;
        
        while (nbIterations < nbIterationsMax && erreurTotale > erreurMax && nbSurapprentissage < 3) {
            // Passage à  l'itération suivante
            ancienneErreur = erreurTotale;
            erreurTotale = 0;
            ancienneErreurGeneralisation = erreurGeneralisationTotale;
            erreurGeneralisationTotale = 0;
            
            // Evaluation et apprentissage
            for (PointND point : donnees.getPtsApprentissage()) {
                double[] sorties = reseau.Evaluer(point);
                for (int nb = 0; nb < sorties.length; nb++) {
                    double erreur = point.sorties[nb] - sorties[nb];
                    erreurTotale += (erreur * erreur);
                }
                reseau.AjusterPoids(point, tauxApprentissage);
            }
            
            // Généralisation
            for (PointND point : donnees.getPtsGeneralisation()) {
                double[] sorties = reseau.Evaluer(point);
                for (int nb = 0; nb < sorties.length; nb++) {
                    double erreur = point.sorties[nb] - sorties[nb];
                    erreurGeneralisationTotale += (erreur * erreur);
                }
            }
            if (erreurGeneralisationTotale > ancienneErreurGeneralisation) {
                nbSurapprentissage++;
            }
            else {
                nbSurapprentissage = 0;
            }
            
            // Changement du taux ?
            if (erreurTotale > ancienneErreur) {
                tauxApprentissage = tauxApprentissage / 2.0;
            }
            
            // Affichage et incrément
            ihm.AfficherMessage("Iteration nÂ°" + nbIterations + " - Erreur totale : " + erreurTotale + " - Generalisation : " + erreurGeneralisationTotale + " - Taux : " + tauxApprentissage + " - Moyenne : " + Math.sqrt(erreurTotale / donnees.ptsApprentissage.length));
            nbIterations++;
        }
    }
}
