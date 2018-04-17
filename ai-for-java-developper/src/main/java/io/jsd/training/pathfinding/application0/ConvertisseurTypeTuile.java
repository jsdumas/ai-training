package io.jsd.training.pathfinding.application0;

// Classe utilitaire gérant les types de terrain
class ConvertisseurTypeTuile {
    public static TypeTuile CharToType(char c) {
        switch (c) {
            case ' ' :
                return TypeTuile.Herbe;
            case '*' :
                return TypeTuile.Arbre;
            case '=' :
                return TypeTuile.Pont;
            case 'X' : 
                return TypeTuile.Eau;
            case '.' :
                return TypeTuile.Chemin;
        }
        return null;
    }
}
