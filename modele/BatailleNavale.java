package modele;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import epoque.EpoqueFactory;
import modele.strategie.Strategie;
import modele.strategie.TirAleatoire;

public class BatailleNavale {
	private Position caseSelectionnee;
	private JoueurOrdinateur ordinateur;
	private JoueurHumain humain;
	private int joueurCourant; // 0 pour humain et 1 pour ordinateur
	private static int JOUEURHUMAIN = 0, JOUEURORDI = 1;
	
	
	private AbstractDAOFactory factory;
	
	
	public BatailleNavale(AbstractDAOFactory factory, EpoqueFactory epoque, Strategie strat) {
		this.factory = factory;
		creerPartie(epoque, strat);
	}
	
	public void creerPartie(EpoqueFactory epoqueFactory, Strategie strat) {
		ArrayList<Bateau> listeBateauxHumain = epoqueFactory.creerBateaux();
		Plateau plateauHumain = new Plateau(listeBateauxHumain);
		humain = new JoueurHumain(plateauHumain, listeBateauxHumain);
		
		ArrayList<Bateau> listeBateauxOrdi = epoqueFactory.creerBateaux();
		Plateau plateauOrdinateur = new Plateau(listeBateauxOrdi);
		ordinateur = new JoueurOrdinateur(plateauOrdinateur, listeBateauxOrdi, strat);
		joueurCourant = JOUEURHUMAIN;
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
