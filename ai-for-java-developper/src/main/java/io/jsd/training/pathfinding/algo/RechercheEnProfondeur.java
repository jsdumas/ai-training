package io.jsd.training.pathfinding.algo;

import java.util.ArrayList;
import java.util.Stack;

// Algorithme de recherche en profondeur
public class RechercheEnProfondeur extends Algorithme {

    // Constructeur
    public RechercheEnProfondeur(Graphe _graphe, IHM _ihm) {
        super(_graphe,_ihm);
    }
    
    // Méthode de résolution
    @Override
    protected void Run() {
        // Création de la liste des noeuds non visités et de la pile
        ArrayList<Noeud> noeudsNonVisites = graphe.ListeNoeuds();
        Stack<Noeud> noeudsAVisiter = new Stack();
        noeudsAVisiter.push(graphe.NoeudDepart());
        noeudsNonVisites.remove(graphe.NoeudDepart());
        
        // Initialisation de la sortie
        Noeud noeudSortie = graphe.NoeudSortie();
        boolean sortieTrouvee = false;
        
        // Boucle principale
        while(!sortieTrouvee && noeudsAVisiter.size() != 0) {
            Noeud noeudCourant = noeudsAVisiter.pop();
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
                        noeudsAVisiter.push(n);
                    }
                }
            }
        }
    }
}
