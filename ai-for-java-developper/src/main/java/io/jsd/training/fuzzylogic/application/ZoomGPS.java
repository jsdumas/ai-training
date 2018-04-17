package io.jsd.training.fuzzylogic.application;

import io.jsd.training.fuzzylogic.algo.ControleurFlou;
import io.jsd.training.fuzzylogic.algo.EnsembleFlouTrapeze;
import io.jsd.training.fuzzylogic.algo.EnsembleFlouTrapezeDroite;
import io.jsd.training.fuzzylogic.algo.EnsembleFlouTrapezeGauche;
import io.jsd.training.fuzzylogic.algo.ValeurLinguistique;
import io.jsd.training.fuzzylogic.algo.VariableLinguistique;

// Classe principale, et sur l'exemple du zoom d'un GPS
public class ZoomGPS {
    public static void main(String[] arg) {
        System.out.println("Logique floue : cas du zoom d'un GPS");
        
        // Création du système
        ControleurFlou controleur = new ControleurFlou("Gestion du zoom d'un GPS");
        
        System.out.println("Ajout des variables d'entrée");
        // Variable linguistique d'entrée : distance (en m, de 0 à  500 000)
        VariableLinguistique distance = new VariableLinguistique("Distance", 0, 500000); 
        distance.AjouterValeurLinguistique(new ValeurLinguistique("Faible", new EnsembleFlouTrapezeGauche(0, 500000, 30, 50))); 
        distance.AjouterValeurLinguistique(new ValeurLinguistique("Moyenne", new EnsembleFlouTrapeze(0, 500000, 40, 50, 100, 150))); 
        distance.AjouterValeurLinguistique(new ValeurLinguistique("Grande", new EnsembleFlouTrapezeDroite(0, 500000, 100, 150))); 
        controleur.AjouterVariableEntree(distance);

        // Variable linguistique d'entrée : vitesse (en km/h, de 0 à  200)
        VariableLinguistique vitesse = new VariableLinguistique("Vitesse", 0, 200);
        vitesse.AjouterValeurLinguistique(new ValeurLinguistique("Lente", new EnsembleFlouTrapezeGauche(0, 200, 20, 30)));
        vitesse.AjouterValeurLinguistique(new ValeurLinguistique("PeuRapide", new EnsembleFlouTrapeze(0, 200, 20, 30, 70, 80)));
        vitesse.AjouterValeurLinguistique(new ValeurLinguistique("Rapide", new EnsembleFlouTrapeze(0, 200, 70, 80, 90, 110)));
        vitesse.AjouterValeurLinguistique(new ValeurLinguistique("TresRapide", new EnsembleFlouTrapezeDroite(0, 200, 90, 110)));
        controleur.AjouterVariableEntree(vitesse);
        
        System.out.println("Ajout de la variable de sortie");
        // Variable linguistiqe de sortie : niveau de zoom (de 1 à  5)
        VariableLinguistique zoom = new VariableLinguistique("Zoom", 0, 5); 
        zoom.AjouterValeurLinguistique(new ValeurLinguistique("Petit", new EnsembleFlouTrapezeGauche(0, 5, 1, 2))); 
        zoom.AjouterValeurLinguistique(new ValeurLinguistique("Normal", new EnsembleFlouTrapeze(0, 5, 1, 2, 3, 4))); 
        zoom.AjouterValeurLinguistique(new ValeurLinguistique("Gros", new EnsembleFlouTrapezeDroite(0, 5, 3, 4))); 
        controleur.AjouterVariableSortie(zoom);

        System.out.println("Ajout des règles");
        // Ajout des différentes règles (9 pour couvrir les 12 cas)
        controleur.AjouterRegle("IF Distance IS Grande THEN Zoom IS Petit"); 
        controleur.AjouterRegle("IF Distance IS Faible AND Vitesse IS Lente THEN Zoom IS Normal"); 
        controleur.AjouterRegle("IF Distance IS Faible AND Vitesse IS PeuRapide THEN Zoom IS Normal"); 
        controleur.AjouterRegle("IF Distance IS Faible AND Vitesse IS Rapide THEN Zoom IS Gros"); 
        controleur.AjouterRegle("IF Distance IS Faible AND Vitesse IS TresRapide THEN Zoom IS Gros"); 
        controleur.AjouterRegle("IF Distance IS Moyenne AND Vitesse IS Lente THEN Zoom IS Petit"); 
        controleur.AjouterRegle("IF Distance IS Moyenne AND Vitesse IS PeuRapide THEN Zoom IS Normal"); 
        controleur.AjouterRegle("IF Distance IS Moyenne AND Vitesse IS Rapide THEN Zoom IS Normal"); 
        controleur.AjouterRegle("IF Distance IS Moyenne AND Vitesse IS TresRapide THEN Zoom IS Gros"); 

        System.out.println("Résolution des cas pratiques");
        // Cas pratique 1 : vitesse de 35 km/h, 
        // et prochain changement de direction à  70 m 
        System.out.println("Cas 1 :"); 
        controleur.AjouterValeurNumerique(vitesse, 35); 
        controleur.AjouterValeurNumerique(distance, 70); 
        System.out.println("Résultat : " + controleur.Resoudre() + "\n"); 

        // Cas pratique 2 : vitesse de 25 km/h, 
        // et prochain changement de direction à  70 m 
        controleur.EffacerValeursNumeriques();
        System.out.println("Cas 2 :"); 
        controleur.AjouterValeurNumerique(vitesse, 25); 
        controleur.AjouterValeurNumerique(distance, 70); 
        System.out.println("Résultat : " + controleur.Resoudre() + "\n"); 

        // Cas pratique 3 : vitesse de 72.5 km/h, 
        // et prochain changement de direction à  40 m 
        controleur.EffacerValeursNumeriques();
        System.out.println("Cas 3 :"); 
        controleur.AjouterValeurNumerique(vitesse, 72.5); 
        controleur.AjouterValeurNumerique(distance, 40); 
        System.out.println("Résultat : " + controleur.Resoudre() + "\n"); 

        // Cas pratique 4 : vitesse de 100 km/h, 
        // et prochain changement de direction à  110 m 
        controleur.EffacerValeursNumeriques();
        System.out.println("Cas 4 :"); 
        controleur.AjouterValeurNumerique(vitesse, 100); 
        controleur.AjouterValeurNumerique(distance, 110); 
        System.out.println("Résultat : " + controleur.Resoudre() + "\n"); 

        // Cas pratique 5 : vitesse de 45 km/h, 
        // et changement à  160 m 
        controleur.EffacerValeursNumeriques();
        System.out.println("Cas 5 :"); 
        controleur.AjouterValeurNumerique(vitesse, 45); 
        controleur.AjouterValeurNumerique(distance, 160); 
        System.out.println("Résultat : " + controleur.Resoudre() + "\n"); 
    }   
}
