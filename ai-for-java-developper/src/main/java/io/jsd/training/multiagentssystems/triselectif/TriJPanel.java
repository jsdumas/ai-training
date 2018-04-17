package io.jsd.training.multiagentssystems.triselectif;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

// Panel contenant la simulation de tri sélectif
public class TriJPanel extends JPanel implements Observer, MouseListener {
    Timer timer;
    boolean enCours = false;
    TimerTask tache;
    Environnement env;
    
    public TriJPanel() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
    }
    
    public void Lancer() {
        env = Environnement.getInstance();
        env.Initialiser(50, 30, getWidth(), getHeight(), 3);
        env.addObserver(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (enCours) {
            // On arrête le timer
            timer.cancel();
            timer = null;
            enCours = false;
        }
        else {
            // On lance le timer
            timer = new Timer();
            tache = new TimerTask() {
                @Override
                public void run() {
                    env.MiseAJour();
                }
            };
            timer.scheduleAtFixedRate(tache, 0, 10);
            enCours = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }    

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
        int agentsCharges = 0;
        for (AgentTri a : env.agents) {
            if (a.estCharge()) {
                agentsCharges++;
            }
        }
        System.out.println(env.dechets.size() + " - " + agentsCharges);
    }
    
    public void DessinerAgent(AgentTri agent, Graphics g) {
        if (agent.estCharge()) {
            g.setColor(Color.GRAY);
        }
        else {
            g.setColor(Color.BLACK);
        }
        g.fillRect((int) agent.posX - 1, (int) agent.posY - 1, 3, 3);
    }
    
    public void DessinerDechet(Dechet d, Graphics g) {
        // Choix de la couleur
        Color couleur;
        switch(d.type) {
            case 1 :    
                couleur = Color.RED;
                break;
            case 2 :
                couleur = Color.GREEN;
                break;
            default : 
                couleur = Color.BLUE;
        }
        g.setColor(couleur);
        // Base : carré
        g.fillRect((int) d.posX - 1, (int) d.posY - 1, 3, 3);
        // Zone d'influence (ronde)
        couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 50);
        g.setColor(couleur);
        int zone = d.ZoneInfluence();
        g.fillOval((int) d.posX - zone, (int) d.posY - zone, zone * 2, zone * 2);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (AgentTri agent : env.agents) {
            DessinerAgent(agent, g);
        }
        for (Dechet dechet : env.dechets) {
            DessinerDechet(dechet, g);
        }
    }
}
