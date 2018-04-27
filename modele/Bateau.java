package modele;

public abstract class Bateau {
	int vie;
	int largeur;
	boolean vertical;
	Position pos;
	
	abstract void appliquerBonus();
	abstract boolean diminuerVie();
	
	public Position getPos(){
		return this.pos;
	}
	public int getPosX(){
		return pos.getX();
	}
	public int getPosY(){
		return pos.getY();
	}
	public int getLargeur(){
		return largeur;
	}
	public boolean getOrientation(){
		return vertical;
	}
	
}
