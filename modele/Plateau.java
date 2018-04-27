package modele;

import java.util.Random;

public class Plateau {
	final public static int TAILLELIGNE = 10;

	//tableau d'entier : 1 si présence de bateau, 0 sinon , -1 si touché 
	private int [][] plateau;
	
	/**
	 * 
	 * @param plateau le tableau d'initialisation
	 */
	public Plateau(int[][] plateau){
		this.plateau=plateau;
	}
	
	/**
	 * Constructeur qui g�n�re un plateau al�atoire
	 */
	public Plateau() {
		Random r = new Random();
		for (int i = 0; i < plateau.length; i++)
			for (int j = 0; j < plateau[0].length; j++)
				this.plateau[i][j] = r.nextInt(2);
	}
	
	/**
	 * Permet de verifier si un bateau se trouve à une postiion x, y.
	 * @param p la position x,y à verifier
	 * @return boolean : Vrai si un bateau se trouve à la position x,y. Faux sinon.
	 */
	public boolean verifPresenceBateau(Position p){
		boolean b;
		int v=plateau[p.getX()][p.getY()];

		if(v==1){
			b=true;
		}else{
			b=false;
		}
		return b;
	}
	/**
	 * Permet de mettre à jour le plateau quand un bateau est coulé.
	 * @param b
	 */
	public void coulerBateau(Bateau b){
		//la position du bateau est la case la plus en haut a gauche du bateau. 
		//		******************
		//		***BBB****B*******
		//		**********B*******
		//		**********B*******
		//		**********B*******
		//On determine si le bateau est horizontal ou vertical
		//if(plateau)
	}
	/**
	 * Permet de mettre à jour le plateau quand un bateau est touché
	 * 
	 * @param p la position à mettre à jour dans le tableau
	 */
	public void toucher(Position p){
		if(this.verifPresenceBateau(p)==true){	
			plateau[p.getX()][p.getY()]=-1;;
		}
	}

	/**
	 * Permet de récupérer la valeur à la case de coordonnées x,y
	 * @param x : indice de colonne (ou ligne -> TODO à rectifier)
	 * @param y : indice de ligne (ou colonne -> TODO à rectifier)
	 */
	public int getValeur(int x, int y) {
		return plateau[x][y];
	}
}
