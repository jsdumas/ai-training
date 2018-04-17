package io.jsd.training.neuralnetworks.algo;

import java.util.ArrayList;
import java.util.Random;

// Ensemble des points utilis�s dans l'algorithme
public class CollectionPoints {
    protected PointND[] ptsApprentissage;
    protected PointND[] ptsGeneralisation;
    
    PointND[] getPtsApprentissage() {
        return ptsApprentissage;
    }
    
    PointND[] getPtsGeneralisation() {
        return ptsGeneralisation;
    }
    
    public CollectionPoints(String[] _contenu, int _nbSorties, double _ratioApprentissage) {
        // Lecture du fichier total
        int nbLignes = _contenu.length;
        ArrayList<PointND> points = new ArrayList();
        for (int i = 0; i < nbLignes; i++) {
            points.add(new PointND(_contenu[i], _nbSorties));
        }
        
        // Cr�ation des points d'apprentissage
        int nbPtsApprentissage = (int) (nbLignes * _ratioApprentissage);
        ptsApprentissage = new PointND[nbPtsApprentissage];
        Random generateur = new Random();
        for (int i = 0; i < nbPtsApprentissage; i++) {
            int index = generateur.nextInt(points.size());
            ptsApprentissage[i] = points.get(index);
            points.remove(index);
        }
        
        // Cr�ation des points de g�n�ralisation
        ptsGeneralisation = (PointND[]) points.toArray(new PointND[points.size()]);
    }
}
