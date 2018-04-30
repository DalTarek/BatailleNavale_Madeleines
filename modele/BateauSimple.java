package modele;

public class BateauSimple extends Bateau {

	/**
	 * Constructeur du bateau Simple
	 * @param vie la vie du bateau
	 * @param nbcases la longueur du bateau
	 * @param position la position la plus en haut à gauche du bateau
	 * @param vertical orientation du bateau
	 */
    public BateauSimple(int vie, int nbcases, Position position, boolean vertical) {
        super(vie, nbcases, position, vertical);
    }
    /**
     * Fonction pour appliquer un bonus
     */
    public void appliquerBonus() {
        // Pas de bonus pour un bateau simple, ne fait rien
    }
}

