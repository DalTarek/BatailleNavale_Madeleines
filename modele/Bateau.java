package modele;

public abstract class Bateau {
	
	protected boolean vertical;
    protected int vie; // points de vie du bateau
    protected int longueur; // nombre de cases du bateau
    protected Position position; // Position de la case la plus en haut à gauche du bateau

    public Bateau(int vie, int nbcases, Position position, boolean vertical) {
        this.vie = vie;
        this.longueur = nbcases;
        this.position = position;
        this.vertical = vertical;
    }
    
    /**
     * Appliquer le bonus du bateau
     */
    public abstract void appliquerBonus();

    /**
     * Diminuer la vie du bateau
     * retourne true si il est détuit
     * false sinon
     */
    public boolean diminuerVie() {
        vie--;

        if (vie == 0) {
            return true;
        }
        return false;
    }

    public void setPosition(Position p) {
        position = p;
    }
    /**************************GETTER*******************************/
    public Position getPos(){
		return this.position;
	}
	public int getPosX(){
		return position.getX();
	}
	public int getPosY(){
		return position.getY();
	}
	public int getLongueur(){
		return longueur;
	}
	public boolean getOrientation(){
		return vertical;
	}

}
