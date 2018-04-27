package modele;

import java.util.ArrayList;
import java.util.Iterator;


public class JoueurHumain {
	ArrayList<Position> listeCaseTouche;
	ArrayList<Position> listeCaseRate;
	ArrayList<Bateau> listeBateau;
	Plateau plateau;
	
	public JoueurHumain(Plateau p,ArrayList<Bateau>listbat){
		plateau=p;
		listeBateau=listbat;
		listeCaseTouche=new ArrayList<Position>();
		listeCaseRate=new ArrayList<Position>();
	}
	public void subirTir(Position p){
		//On verifie si la postion du tir subit est valide (si il y a un 1 sur le plateau)
		if(plateau.verifPresenceBateau(p)){
			//on recherche l'indice du bateau touché dans la liste
			int indiceB=this.rechercheBateau(p);
			//indiceB ne peux pas être =-1
			assert(indiceB!=-1);
			if(listeBateau.get(indiceB).diminuerVie()){
				plateau.coulerBateau(listeBateau.get(indiceB));
			}else{
				plateau.toucher(p);
			}
			
			
		}
	}
	/**
	 * Permet de recuperer le bateau correspondant a la position passé en paramètre
	 * @param p la position à partir de laquelle on va chercher le bateau
	 * @return le bateau présent a la position p : -1 si aucun bateau trouvé
	 */
	public int rechercheBateau(Position p){
			//RECHERCHE DU BATEAU TOUCHé
		boolean trouve=false;
		//la position du bateau i
		Position pos;
		//la largeur du bateau i
		int largeurBateau;
		//indice du bateau courant
		int i=-1;
		while (!trouve && i<listeBateau.size()-1){
			i++;
			//la position la plus en haut à gauche du bateau
			pos=listeBateau.get(i).getPos();
			//largeur du bateau
			largeurBateau=listeBateau.get(i).getLargeur();
			//orientation du bateau : VRAI si vertical, FAUX sinon
			boolean orientation=listeBateau.get(i).getOrientation();
			if(orientation){// si le bateau est vertical
				System.out.println("coucou");
				for(int l=0;l<largeurBateau;l++){//On parcourt la largeur du bateau jusqu'a a atteindre la position p passé en paramètre
					Position tempy=new Position(pos);	
					tempy.setY(tempy.getY()+l);
					if(p.same(tempy)){//Si la position passé en paramètre et la position fictive sont égale, on à trouvé quel bateau est à la postion p
						trouve=true;
					}
				}
			}else{
				for(int l=0;l<largeurBateau;l++){//Si le bateau est horizontal
					Position tempx=new Position(pos);
					tempx.setX(tempx.getX()+l);
					if(p.same(tempx)){
						trouve=true;
					}
				}
			}
		}
		if(trouve){
			System.out.println("bateau touché : "+i);
			return i;
		}else{
			return -1;
		}
	}
}