package io.jsd.training.geneticalgorithm.algo.labyrinthe;

// Une case du labyrinthe
public class Case {
    public int i;
    public int j;
    
    public Case(int _i, int _j) {
        i = _i;
        j = _j;
    }
    
    @Override
    public boolean equals(Object o) {
        return (i == ((Case)o).i && j == ((Case)o).j);
    }
}
