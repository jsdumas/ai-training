package io.jsd.training.neuralnetworks.algo;

// Point utilisé dans le réseau de neurones, avec plusieurs entrées et sorties
public class PointND {
    public final double[] entrees;
    public final double[] sorties;
    
    public PointND(String str, int _nbSorties) {
        String[] contenu = str.split("\t");
        entrees = new double[contenu.length - _nbSorties];
        for (int i = 0; i < entrees.length; i++) {
            entrees[i] = Double.parseDouble(contenu[i]);
        }
        sorties = new double[_nbSorties];
        for (int i = 0; i < _nbSorties; i++) {
            sorties[i] = Double.parseDouble(contenu[entrees.length + i]);
        }
    }
}
