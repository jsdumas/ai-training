package io.jsd.training.multiagentssystems.bancpoissons;

import javax.swing.JFrame;

// Lancement de la fenàªtre et de l'application
public class Application {
    public static void main(String[] args) {
        // Création de la fenàªtre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Banc de poissons");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        // Création du contenu
        OceanJPanel panel = new OceanJPanel();
        fenetre.setContentPane(panel);
        // Affichage
        fenetre.setVisible(true);
        panel.Lancer();
    }
}
