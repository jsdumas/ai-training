package io.jsd.training.multiagentssystems.jeudelavie;

import javax.swing.JFrame;

// Fenetre principale de l'application (et lancement)
public class Application {
    public static void main(String[] args) {
        // Cr�ation de la fen�tre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Jeu de la vie");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        // Cr�ation du contenu
        JeuDeLaVieJPanel panel = new JeuDeLaVieJPanel();
        fenetre.setContentPane(panel);
        // Affichage
        fenetre.setVisible(true);
        panel.Lancer();
    }
}
