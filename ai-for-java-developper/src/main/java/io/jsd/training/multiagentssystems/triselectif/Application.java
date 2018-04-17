package io.jsd.training.multiagentssystems.triselectif;

import javax.swing.JFrame;

// Classe contenant le main et cr�ant la fen�tre + lan�ant la simulation
public class Application {
    public static void main(String[] args) {
        // Cr�ation de la fen�tre
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Tri s�lectif");
        fenetre.setSize(600, 400);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        // Cr�ation du contenu
        TriJPanel panel = new TriJPanel();
        fenetre.setContentPane(panel);
        // Affichage
        fenetre.setVisible(true);
        panel.Lancer();
    }
}
