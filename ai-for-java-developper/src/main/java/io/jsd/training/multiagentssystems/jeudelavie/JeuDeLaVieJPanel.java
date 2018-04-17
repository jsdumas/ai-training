package io.jsd.training.multiagentssystems.jeudelavie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

// Panel principal gérant le jeu de la vie (sa création + son lancement + mises à jour)
public class JeuDeLaVieJPanel extends JPanel implements Observer, MouseListener {
    Timer timer;
    boolean enCours = false;
    Grille grille;
    TimerTask tache;
    
    public JeuDeLaVieJPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
    }
    
    public void Lancer() {
        grille = new Grille(this.getWidth() / 3, getHeight() / 3, 0.1);
        grille.addObserver(this);
        timer = new Timer();
        tache = new TimerTask() {
            @Override
            public void run() {
                grille.MiseAJour(true);
            }
        };
        timer.scheduleAtFixedRate(tache, 0, 500);
        enCours = true;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    public void DessinerCellule(Graphics g, int i, int j) {
        g.fillRect(3*i-1, 3*j-1, 3, 3);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < grille.largeur; i++) {
            for (int j = 0; j < grille.hauteur; j++) {
                if (grille.contenu[i][j]) {
                    DessinerCellule(g, i, j);
                }
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Clic gauche : ajout de cellules vivantes
            grille.ChangerEtat(e.getX() / 3, e.getY() / 3);
            grille.MiseAJour(false);
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            // Clic droit : pause du timer
            if (enCours) {
                timer.cancel();
                timer = null;
            }
            else {
                timer = new Timer();
                tache = new TimerTask() {
                    @Override
                    public void run() {
                        grille.MiseAJour(true);
                    }
                };
                timer.scheduleAtFixedRate(tache, 0, 500);
            }
            enCours = !enCours;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
