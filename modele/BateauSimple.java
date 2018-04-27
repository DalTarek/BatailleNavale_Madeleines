package modele;

public class BateauSimple extends Bateau {

    public BateauSimple(int vie, int nbcases, Position position) {
        super(vie, nbcases, position);
    }

    public void appliquerBonus() {
        // Pas de bonus pour un bateau simple, ne fait rien
    }
}