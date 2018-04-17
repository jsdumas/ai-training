package io.jsd.training.neuralnetworks.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import io.jsd.training.neuralnetworks.algo.IHM;
import io.jsd.training.neuralnetworks.algo.Systeme;

// Classe du programme principal
public class Application implements IHM {
    public static void main(String[] args) {
        Application app = new Application();
        app.Lancer();
    }
    
    protected void Lancer() {
        // Problème du XOR
        /*String[] contenu = lireFichier("xor.txt", true);
        Systeme systeme = new Systeme(2, 2, 1, contenu, 1.0, this);
        systeme.Lancer();*/
        
        // Problème Abalone
        String[] contenu = lireFichier("abalone_norm.txt", false);
        Systeme systeme = new Systeme(10, 4, 1, contenu, 0.8, this);
        systeme.setTauxApprentissage(0.6);
        systeme.setNbIterationsMax(50000);
        systeme.Lancer();
    }

    protected String[] lireFichier(String nomFichier, boolean enleverEntete) {
        try {
            ArrayList<String> lignes = new ArrayList();
            BufferedReader buffer = new BufferedReader(new FileReader(nomFichier));
            String ligne;
            while ((ligne = buffer.readLine()) != null) {
                lignes.add(ligne);
            }
            buffer.close();
            if (enleverEntete) {
                lignes.remove(0);
            }
            String[] contenu = lignes.toArray(new String[lignes.size()]);
            return contenu;
        }
        catch (IOException e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
    @Override
    public void AfficherMessage(String msg) {
        System.out.println(msg);
    }
}
