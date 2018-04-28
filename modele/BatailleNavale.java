package modele;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import modele.strategie.TirAleatoire;

public class BatailleNavale {
	private Position caseSelectionnee;
	private JoueurOrdinateur ordinateur;
	private JoueurHumain humain;
	private int joueurCourant; // 0 pour humain et 1 pour ordinateur
	private static int JOUEURHUMAIN = 0, JOUEURORDI = 1;
	
	
	private AbstractDAOFactory factory;
	
	
	public BatailleNavale(AbstractDAOFactory factory) {
		this.factory = factory;
		//TODO créer la liste des bateau du joueur humain et le plateau correspondant
		Plateau plateauHumain = new Plateau();
		humain = new JoueurHumain(plateauHumain, /* liste des bateaux */);
		//TODO créer la liste des bateau du joueur ordinateur et le plateau correspondant
		Plateau plateauOrdinateur = new Plateau();
		ordinateur = new JoueurOrdinateur(plateauOrdinateur, /* liste des bateaux */, new TirAleatoire());
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
		if (joueurCourant == JOUEURHUMAIN) {
			ordinateur.subirTir(caseSelectionnee);
			if (!ordinateur.aPerdu())
				changerJoueurCourant();
		} else {
			Position p = ordinateur.recupPosTir();
			stockCaseSelectionnee(p);
			humain.subirTir(caseSelectionnee);
			if (!humain.aPerdu())
				changerJoueurCourant();
		}
	}
	
	/**
	 * Alterne entre le joueur humain et le joueur ordinateur
	 */
	public void changerJoueurCourant() {
		if (joueurCourant == JOUEURHUMAIN)
			joueurCourant = JOUEURORDI;
		else
			joueurCourant = JOUEURHUMAIN;
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
		}	
		return valide;
	}
	
	/**
	 * Permet de sauvegarder une partie pour la reprendre plus tard
	 */
	public void sauvegarderPartie(String nomFichier) {
		factory.getBatailleDAO().sauvegarderPartie(this, nomFichier);
	}
	
	/**
	 * Permet de charger une partie sauvegardee
	 */
	public void chargerPartie(String nomFichier) {
		factory.getBatailleDAO().chargerPartie(nomFichier);
	}

	public JoueurOrdinateur getOrdinateur() {
		return ordinateur;
	}

	public JoueurHumain getHumain() {
		return humain;
	}

	public int getJoueurCourant() {
		return joueurCourant;
	}
	
	public void setJoueurCourant(int n) {
		joueurCourant = n;
	}
}
