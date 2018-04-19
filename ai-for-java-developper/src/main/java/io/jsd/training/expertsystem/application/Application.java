package io.jsd.training.expertsystem.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import io.jsd.training.expertsystem.algo.IFait;
import io.jsd.training.expertsystem.algo.IHM;
import io.jsd.training.expertsystem.algo.MoteurInferences;
import io.jsd.training.expertsystem.algo.Regle;

public class Application implements IHM {

	public static void main(String[] args) {
		Application app = new Application();
		app.run();
	}

	// Fonctionnement du programme, avec l'exemple des polygones
	private void run() {
		// Cr�ation du moteur
		System.out.println("** Cr�ation du moteur **");
		MoteurInferences moteurInferences = new MoteurInferences(this);

		// Ajout des r�gles
		System.out.println("** Ajout des r�gles **");
		
		moteurInferences.ajouterRegle("R1 : IF (Ordre=3(Quel est l'ordre?)) THEN Triangle");
		moteurInferences.ajouterRegle("R2 : IF (Triangle AND Angle Droit(La figure a-t-elle au moins un angle droit ?)) THEN Triangle Rectangle");
		moteurInferences.ajouterRegle("R3 : IF (Triangle AND Cotes Egaux=2(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Triangle Isoc�le");
		moteurInferences.ajouterRegle("R4 : IF (Triangle Rectangle AND Triangle Isoc�le) THEN Triangle Rectangle Isoc�le");
		moteurInferences.ajouterRegle("R5 : IF (Triangle AND Cotes Egaux=3(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Triangle Equilateral");
		moteurInferences.ajouterRegle("R6 : IF (Ordre=4(Quel est l'ordre ?)) THEN Quadrilat�re");
		moteurInferences.ajouterRegle("R7 : IF (Quadrilat�re AND Cotes Paralleles=2(Combien y a-t-il de c�t�s parall�les entre eux - 0, 2 ou 4)) THEN Trapeze");
		moteurInferences.ajouterRegle("R8 : IF (Quadrilat�re AND Cotes Paralleles=4(Combien y a-t-il de c�t�s parall�les entre eux - 0, 2 ou 4)) THEN Parall�logramme");
		moteurInferences.ajouterRegle("R9 : IF (Parall�logramme AND Angle Droit(La figure a-t-elle au moins un angle droit ?)) THEN Rectangle");
		moteurInferences.ajouterRegle("R10 : IF (Parall�logramme AND Cotes Egaux=4(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Losange");
		moteurInferences.ajouterRegle("R11 : IF (Rectangle AND Losange THEN Carr�");

		// R�solution
		while (true) {
			System.out.println("\n** R�solution **");
			moteurInferences.resoudre();
		}
	}

	// Demande une valeur enti�re � l'utilisateur, sans v�rifications (0 en cas de
	// probl�me)
	public int demanderValeurEntiere(String question) {
		System.out.println(question);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return Integer.decode(br.readLine());
		} catch (Exception e) {
			return 0;
		}
	}

	// Demande une valeur bool�enne, avec oui (vrai) ou non.
	// Les erreurs sont ignor�es (renvoie faux)
	public boolean demanderValeurBooleenne(String question) {
		try {
			System.out.println(question + " (oui, non)");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String res = br.readLine();
			if (res.equals("oui")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}

	// Affiche la liste des faits de niveau >0 et par ordre d�croissant de niveau
	public void afficherFaits(ArrayList<IFait> faits) {
		String res = "Solution(s) trouv�e(s) : \n";
		Collections.sort(faits, (IFait fait1, IFait fait2) -> {
			if (fait1.getNiveau() == fait2.getNiveau()) {
				return 0;
			} else if (fait1.getNiveau() > fait2.getNiveau()) {
				return -1;
			} else {
				return 1;
			}
		});
		for (IFait f : faits) {
			if (f.getNiveau() != 0) {
				res += f.toString() + "\n";
			}
		}
		System.out.println(res);
	}

	// Affiche les r�gles contenues dans la base
	public void afficherRegles(ArrayList<Regle> regles) {
		String res = "";
		for (Regle r : regles) {
			res += r.toString() + "\n";
		}
		System.out.println(res);
	}

}
