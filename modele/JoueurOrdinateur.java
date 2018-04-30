package modele;

import java.util.ArrayList;
import java.util.Iterator;

import modele.strategie.Strategie;


public class JoueurOrdinateur {
	ArrayList<Position> listeCaseTouche;
	ArrayList<Position> listeCaseRate;

	// Stocke le nombre de tirs réussis et ratés effectué par le joueur
	// Ce n'est pas la même valeur que la taille des deux listes du dessus
	// Puisque ces listes stockent aussi toutes les cases d'un bateau coulé,
	int cptTirsRates = 0;
	int cptTirsReussis = 0;

	ArrayList<Bateau> listeBateau;
	Plateau plateau;
	
	private Strategie strategie;
	
	public JoueurOrdinateur(Plateau p, ArrayList<Bateau>listbat, Strategie strat){
		plateau=p;
		listeBateau=listbat;
		listeCaseTouche=new ArrayList<Position>();
		listeCaseRate=new ArrayList<Position>();
		this.strategie = strat;
	}
	
	public void subirTir(Position p){
		//On verifie si la postion du tir subit est valide (si il y a un 1 sur le plateau)
		if(plateau.verifPresenceBateau(p)){
			//on recherche l'indice du bateau touché dans la liste
			int indiceB=this.rechercheBateau(p);
			//indiceB ne peux pas être =-1
			assert(indiceB!=-1);
			if(listeBateau.get(indiceB).diminuerVie()){
				//on met a jour la liste des cases touche dans la méthode CoulerBateau parcequ'il y a plusieurs cases touche
				plateau.coulerBateau(listeBateau.get(indiceB),listeCaseTouche);
				
			}else{
				plateau.toucher(p);
				listeCaseTouche.add(p);
			}
			
			cptTirsReussis++;
			
		}else{
			listeCaseRate.add(p);
			cptTirsRates++;
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
			largeurBateau=listeBateau.get(i).getLongueur();
			//orientation du bateau : VRAI si vertical, FAUX sinon
			boolean orientation=listeBateau.get(i).getOrientation();
			if(orientation){// si le bateau est vertical
				for(int l=0;l<largeurBateau;l++){//On parcourt la largeur du bateau jusqu'a a atteindre la position p passé en paramètre
					Position tempy=new Position(pos);	
					tempy.setY(tempy.getY()+l);
					if(p.compareTo(tempy)==0){//Si la position passé en paramètre et la position fictive sont égale, on à trouvé quel bateau est à la postion p
						trouve=true;
					}
				}
			}else{
				for(int l=0;l<largeurBateau;l++){//Si le bateau est horizontal
					Position tempx=new Position(pos);
					tempx.setX(tempx.getX()+l);
					if(p.compareTo(tempx)==0){
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
	
	
	/**
	 * 
	 * @return booolean true si le joueur ordinateur a perdu
	 */
	public boolean aPerdu(){
		// On part du principe que le joueur a perdu
		boolean aPerdu = true;

		// Si on trouve un bateau encore en vie, alors il n'a en fait pas perdu
		for (int i = 0; i < listeBateau.size(); i++) {
			if (listeBateau.get(i).getVie() > 0) {
				aPerdu = false;
				break;
			}
		}

		return aPerdu;
	}
	
	/**
	 * Méthode qui vérifie si une position appartient a la liste des case déjà touché
	 * @param p 
	 * @return boolean true si la position appartient a la liste des cases touché, faux sinon
	 */
	public boolean caseDejaTouchee(Position p){
		// la case a déjà été sélectionnée si elle est dans les cases ratées ou les cases réussies
		return (listeCaseRate.contains(p) || listeCaseTouche.contains(p));
	}
	
	/**
	 * 
	 * @return la position du prochain tir de l'ordinateur sur le plateau
	 */
	public Position recupPosTir(ArrayList<Position> casesTouchees, ArrayList<Position> casesRatees) {
		return strategie.getProchainTir(casesTouchees, casesRatees);
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public void setPlateau(Plateau p) {
		this.plateau = p;
	}
	
	public ArrayList<Position> getListeCaseTouche() {
		return listeCaseTouche;
	}

	public ArrayList<Position> getListeCaseRate() {
		return listeCaseRate;
	}

	public int getNombreTirsReussis() {
		return cptTirsReussis;
	}

	public int getNombreTirsRates() {
		return cptTirsRates;
	}
	
	public Strategie getStrategie() {
		return strategie;
	}
	
	public ArrayList<Bateau> getListeBateau() {
		return listeBateau;
	}
}
