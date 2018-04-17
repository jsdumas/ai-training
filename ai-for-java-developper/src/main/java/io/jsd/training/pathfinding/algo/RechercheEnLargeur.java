package io.jsd.training.pathfinding.algo;

import java.util.ArrayList;
import java.util.LinkedList;

// Algorithme de recherche en largeur
public class RechercheEnLargeur extends Algorithme {

    // Constructeur
    public RechercheEnLargeur(Graphe _graphe, IHM _ihm) {
        super(_graphe,_ihm);
    }
    
    // Méthode de résolution
    @Override
    protected void Run() {
        // Création de la liste des noeuds non visités et de la pile
        ArrayList<Noeud> noeudsNonVisites = graphe.ListeNoeuds();
        LinkedList<Noeud> noeudsAVisiter = new LinkedList();
        noeudsAVisiter.add(graphe.NoeudDepart());
        noeudsNonVisites.remove(graphe.NoeudDepart());
        
        // Initialisation de la sortie
        Noeud noeudSortie = graphe.NoeudSortie();
        boolean sortieTrouvee = false;
        
        // Boucle principale
        while(!sortieTrouvee && noeudsAVisiter.size() != 0) {
            Noeud noeudCourant = noeudsAVisiter.removeFirst();
            if (noeudCourant.equals(noeudSortie)) {
                // On a fini l'algorithme
                sortieTrouvee = true;
            }
            else {
                // On ajoute les voisins non encore visités
                for (Noeud n : graphe.ListeNoeudsSortants(noeudCourant)) {
                    if (noeudsNonVisites.contains(n)) {
                        noeudsNonVisites.remove(n);
                        n.precurseur = noeudCourant;
                        n.distanceDuDepart = noeudCourant.distanceDuDepart + graphe.Cout(noeudCourant, n);
                        noeudsAVisiter.add(n);
                    }
                }
            }
        }
    }
}
