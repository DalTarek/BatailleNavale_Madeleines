package modele;

public class BateauSimple extends Bateau {


    public BateauSimple(int vie, int nbcases, Position position, boolean vertical) {
        super(vie, nbcases, position, vertical);
    }

    public void appliquerBonus() {
        // Pas de bonus pour un bateau simple, ne fait rien
    }
}

