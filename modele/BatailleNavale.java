package modele;

import dao.AbstractDAOFactory;

public class BatailleNavale {
	private Position caseSelectionnee;
	private JoueurOrdinateur ordinateur;
	private JoueurHumain humain;
	private int joueurCourant; // 0 pour humain et 1 pour ordinateur
	
	private AbstractDAOFactory factory;
	
	
	public BatailleNavale(AbstractDAOFactory factory) {
		this.factory = factory;
		//TODO créer la liste des bateau du joueur humain
		Plateau plateauHumain = new Plateau();
		humain = new JoueurHumain(plateauHumain, /* liste des bateaux */);
		//TODO créer la liste des bateau du joueur ordinateur
		Plateau plateauOrdinateau = new Plateau();
		ordinateur = new JoueurOrdinateur(plateauOrdinateur, /* liste des bateaux */);
		joueurCourant = 0;
	}
	
	public void creerPartie() {
		
	}
	
	/**
	 * 
	 * @param p
	 */
	private void stockCaseSelectionnee(Position p) {
		caseSelectionnee = p;
	}

	public void tirer() {
		Position p = ordinateur.recupPosTir();
		stockCaseSelectionnee(p);
		humain.subirTir(caseSelectionnee);
		if (!humain.aPerdu())
			changerJoueurCourant();
	}
	
	/**
	 * Alterne entre le joueur humain et le joueur ordinateur
	 * 0 pour le joueur humain
	 * 1 pour le joueur ordinateur
	 */
	public void changerJoueurCourant() {
		if (joueurCourant == 0)
			joueurCourant = 1;
		else
			joueurCourant = 0;
	}
	
	/**
	 * Teste si une case est valide ou pas
	 * @param p position a tester
	 * @return vrai si la case est valide
	 */
	public boolean estValide(Position p) {
		boolean valide = false;
		if (joueurCourant == 0) {
			valide = humain.caseDejaTouchee(p);
		} else {
			valide = ordinateur.caseDejaTouchee(p);
		}
		
		return valide;
	}
	
	/**
	 * Permet de sauvegarder une partie pour la reprendre plus tard
	 */
	public void sauvegarderPartie() {
		factory.getBatailleDAO().sauvegarderPartie(this);
	}
	
	/**
	 * Permet de charger une partie sauvegardee
	 */
	public void chargerPartie() {
		factory.getBatailleDAO().chargerPartie();
	}
}
