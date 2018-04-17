package io.jsd.training.multiagentssystems.triselectif;

import javax.swing.JFrame;

// Classe contenant le main et créant la fenàªtre + lanà§ant la simulation
public class Application {
    public static void main(String[] args) {
        // Création de la fenàªtre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Tri sélectif");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        // Création du contenu
        TriJPanel panel = new TriJPanel();
        fenetre.setContentPane(panel);
        // Affichage
        fenetre.setVisible(true);
        panel.Lancer();
    }
}
