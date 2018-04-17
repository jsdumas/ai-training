package io.jsd.training.pathfinding.application0;

import java.time.Duration;
import java.time.LocalDateTime;
import io.jsd.training.pathfinding.algo.AStar;
import io.jsd.training.pathfinding.algo.Algorithme;
import io.jsd.training.pathfinding.algo.BellmanFord;
import io.jsd.training.pathfinding.algo.Dijkstra;
import io.jsd.training.pathfinding.algo.Graphe;
import io.jsd.training.pathfinding.algo.IHM;
import io.jsd.training.pathfinding.algo.RechercheEnLargeur;
import io.jsd.training.pathfinding.algo.RechercheEnProfondeur;

// Utilisation des algorithmes sur des exemples de carte
public class Application implements IHM {

    // Programme main
    public static void main(String[] args) {
        System.out.println("Recherche de chemins");
        Application app = new Application();
        app.Lancer();
    }

    // Lancement sur les deux probl�mes
    private void Lancer() {
        // Cas 1�re carte
        String carteStr = "..  XX   .\n" 
                      + "*.  *X  *.\n" 
                      + " .  XX ...\n" 
                      + " .* X *.* \n" 
                      + " ...=...  \n" 
                      + " .* X     \n" 
                      + " .  XXX*  \n" 
                      + " .  * =   \n" 
                      + " .... XX  \n" 
                      + "   *.  X* "; 
        Carte carte1 = new Carte(carteStr, 0, 0, 9, 9);
        LancerAlgorithmes(carte1);
        
        // Cas 2�me carte
        carteStr = "...*     X .*    *  \n" 
                 + " *..*   *X .........\n"  
                 + "   .     =   *.*  *.\n" 
                 + "  *.   * XXXX .    .\n" 
                 + "XXX=XX   X *XX=XXX*.\n" 
                 + "  *.*X   =  X*.  X  \n" 
                 + "   . X * X  X . *X* \n" 
                 + "*  .*XX=XX *X . XXXX\n" 
                 + " ....  .... X . X   \n" 
                 + " . *....* . X*. = * "; 
        Carte carte2 = new Carte(carteStr, 0, 0, 9, 19);
        LancerAlgorithmes(carte2);
    }
    
    // Lancement de tous les algorithmes � la suite
    private void LancerAlgorithmes(Graphe graphe) {
        LancerAlgorithme("Profondeur", graphe);
        LancerAlgorithme("Largeur", graphe);
        LancerAlgorithme("Bellman-Ford", graphe);
        LancerAlgorithme("Dijkstra", graphe);
        LancerAlgorithme("A*", graphe);
    }
    
    // Lancement d'un algorithme d'apr�s son nom et affichage du temps
    private void LancerAlgorithme(String nom, Graphe graphe) {
        // Initialisation
        LocalDateTime debut;
        LocalDateTime fin;
        Duration duree;
        Algorithme algo = null;
        
        // Cr�ation de l'algorithme
        switch(nom) {
            case "Profondeur" :
                algo = new RechercheEnProfondeur(graphe, this);
                break;
            case "Largeur" : 
                algo = new RechercheEnLargeur(graphe, this);
                break;
            case "Bellman-Ford" :
                algo = new BellmanFord(graphe, this);
                break;
            case "Dijkstra" :
                algo = new Dijkstra(graphe, this);
                break;
            case "A*" :
                algo = new AStar(graphe, this);
                break;
        }
        
        // R�solution
        System.out.println("Algorithme : " + nom);
        debut = LocalDateTime.now();
        algo.Resoudre();
        fin = LocalDateTime.now();
        duree = Duration.between(debut, fin);
        System.out.println("Dur�e (ms) : " + duree.toMillis() + "\n");
    }
    
    // M�thode venant de l'interface, pour l'affichage du r�sultat
    @Override
    public void AfficherResultat(String chemin, double distance) {
        System.out.println("Chemin (taille : " + distance + ") : " + chemin);
    }
    
}
