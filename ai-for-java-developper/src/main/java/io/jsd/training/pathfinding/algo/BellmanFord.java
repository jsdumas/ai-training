package io.jsd.training.pathfinding.algo;

import java.util.ArrayList;

// Algorithme de Bellman-Ford
public class BellmanFord extends Algorithme {
    // Constructeur
    public BellmanFord(Graphe _graphe, IHM _ihm) {
        super(_graphe, _ihm);
    }

    // M�thode de r�solution
    @Override
    protected void Run() {
        // Initialisation
        boolean distanceChangee = true;
        int i = 0;
        ArrayList<Arc> listeArcs = graphe.ListeArcs();
        
        // Boucle principale
        int nbBoucleMax = graphe.NombreNoeuds() - 1;
        while (i < nbBoucleMax && distanceChangee) {
            distanceChangee = false;
            for (Arc arc : listeArcs) {
                if (arc.source.distanceDuDepart + arc.cout < arc.cible.distanceDuDepart) {
                    // On a trouv� un chemin plus court
                    arc.cible.distanceDuDepart = arc.source.distanceDuDepart + arc.cout;
                    arc.cible.precurseur = arc.source;
                    distanceChangee = true;
                }
            }
            i++;
        }
        
        // Test si boule n�gative
        for (Arc arc : listeArcs) {
            if (arc.source.distanceDuDepart + arc.cout < arc.cible.distanceDuDepart) {
                // On ne pourra pas trouver un chemin le plus court
                System.err.println("Boucle n�gative - pas de chemin le plus court");
            }
        }
    }
}
