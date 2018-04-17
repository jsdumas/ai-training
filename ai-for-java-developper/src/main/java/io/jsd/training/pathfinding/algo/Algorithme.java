package io.jsd.training.pathfinding.algo;

// Classe générique représentant un algorithme de recherche de chemins
// Tous les algorithmes en héritent
public abstract class Algorithme {
    protected Graphe graphe;
    protected IHM ihm;
    
    public Algorithme(Graphe _graphe, IHM _ihm) {
        graphe = _graphe;
        ihm = _ihm;
    }
    
    public final void Resoudre() {
        // Nettoyage du graphe
        graphe.Effacer();
        
        // Lancement de l'algorithme
        Run();
        
        // Affichage du résultat
        ihm.AfficherResultat(graphe.ReconstruireChemin(), graphe.NoeudSortie().distanceDuDepart);
    }
    
    protected abstract void Run();
}
