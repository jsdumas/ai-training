package io.jsd.training.expertsystem.algo;

import java.util.ArrayList;

public interface IHM {
    int demanderValeurEntiere(String question); 
    boolean demanderValeurBooleenne(String question); 
    void afficherFaits(ArrayList<IFait> faits); 
    void afficherRegles(ArrayList<Regle> regles); 
}
