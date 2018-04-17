package io.jsd.training.metaheuristic.algo;

// Algorithme générique
public abstract class Algorithme {
    protected IProbleme probleme;
    protected IHM ihm;
    
    public void Resoudre(IProbleme _pb, IHM _ihm) {
        probleme = _pb;
        ihm = _ihm;
    }
    
    protected abstract void EnvoyerResultat();
}
