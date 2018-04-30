package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class Plateau {
	final public static int TAILLELIGNE = 10;

	//tableau d'entier : 1 si présence de bateau, 0 sinon , -1 si touché , -2 si coulé
	private int [][] plateau;
	
	/**
	 * 
	 * @param plateau le tableau d'initialisation
	 */
	public Plateau(int[][] plateau){
		this.plateau=plateau;
	}
	/**
	 * Construit un plateau en fonction d'une liste de bateau passé en paramètre
	 * @param listBateau 
	 */
	public Plateau(ArrayList<Bateau>listBateau){
		
		plateau=new int[TAILLELIGNE][TAILLELIGNE];
		//longueur du bateau i
		int longueur;
		//orientation du bateau i
		boolean orientation;
		//position du bateau i
		Position posCourant;
		for(int i=0;i<listBateau.size();i++){
			longueur=listBateau.get(i).getLongueur();
			orientation=listBateau.get(i).getOrientation();
			posCourant=listBateau.get(i).getPos();
			if(orientation){
				
				for(int y=posCourant.getY();y<posCourant.getY()+longueur;y++){
					if(this.plateau[posCourant.getX()][y]==0){
						this.plateau[posCourant.getX()][y]=1;
					}else{
						System.out.println("Erreur creation d'un bateau sur un bateau déjà existant");
					}
				}
			}else{
				
				for(int x=posCourant.getX();x<posCourant.getX()+longueur;x++){
					if(this.plateau[x][posCourant.getY()]==0){
						this.plateau[x][posCourant.getY()]=1;
					}else{
						System.out.println("Erreur : creation d'un bateau sur un bateau déjà existant");
					}
				}
			}
		}
	}
	/**
	 * Constructeur qui g�n�re un plateau al�atoire
	 */
	/*public Plateau() {
		Random r = new Random();
		for (int i = 0; i < plateau.length; i++)
			for (int j = 0; j < plateau[0].length; j++)
				this.plateau[i][j] = r.nextInt(2);
	}*/
	
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
	public void coulerBateau(Bateau b, ArrayList<Position> listTouche){
		int longueur=b.getLongueur();
		boolean orientation=b.getOrientation();
		Position posCourant=b.getPos();
		if(orientation){
			for(int y=posCourant.getY();y<posCourant.getY()+longueur;y++){		
				this.plateau[posCourant.getX()][y]=-2;
				listTouche.add(new Position(posCourant.getX(),y));
			}
		}else{
			for(int x=posCourant.getX();x<posCourant.getX()+longueur;x++){		
				this.plateau[x][posCourant.getY()]=-2;
				listTouche.add(new Position(x,posCourant.getY()));
			}
		}
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int j = 0; j < TAILLELIGNE; j++) {
			for (int i = 0; i < TAILLELIGNE; i++) {
				sb.append(plateau[i][j] + " | ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
