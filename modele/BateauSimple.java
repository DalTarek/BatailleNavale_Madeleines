package modele;

public class BateauSimple extends Bateau {

	
	public BateauSimple(int vie, int largeur, Position pos, boolean vertical){
		this.vie=vie;
		this.largeur=largeur;
		this.pos=pos;
		this.vertical=vertical;
	}
	@Override
	void appliquerBonus() {
		// TODO
		
	}
	/**
	 * Méthode qui retire un point de vie au bateau et renvoi un boolean à true si le bateau est détruit
	 */
	@Override
	public boolean diminuerVie() {
		//TODO
		System.out.println("le bateau perd un pv");
		return false;
	
		
	}

}
