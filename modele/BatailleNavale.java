package modele;

import java.util.ArrayList;
import java.util.Observable;

import java.io.File;

import dao.AbstractDAOFactory;
import epoque.EpoqueFactory;
import modele.strategie.Strategie;
import modele.strategie.TirAleatoire;

public class BatailleNavale extends Observable {
	private Position caseSelectionnee;
	private JoueurOrdinateur ordinateur;
	private JoueurHumain humain;
	private int joueurCourant; // 0 pour humain et 1 pour ordinateur
	private static int JOUEURHUMAIN = 0, JOUEURORDI = 1;

	private ArrayList<String> nomPartiesSauvegardees = new ArrayList<>();
		
	private AbstractDAOFactory factory;

	
	public BatailleNavale(AbstractDAOFactory factory) {
		this.factory = factory;

		// On stocke le nom des parties déjà présentes dans le dossier de sauvegarde
		String currentDirectory = System.getProperty("user.dir");

        // dossier contenant les sauvegardes
        File savesDirectory = new File(currentDirectory + "/sauvegardes");

        if (savesDirectory.exists()) {
            for (File f : savesDirectory.listFiles()) {
                // On enlève l'extension de fichier au nom
                String name = f.getName().substring(0, f.getName().indexOf("."));
                nomPartiesSauvegardees.add(name);
            }
        }
	}
	
	public void creerPartie(EpoqueFactory epoqueFactory, Strategie strat) {
		ArrayList<Bateau> listeBateauxHumain = epoqueFactory.creerBateaux();
		Plateau plateauHumain = new Plateau(listeBateauxHumain);
		humain = new JoueurHumain(plateauHumain, listeBateauxHumain);
		
		ArrayList<Bateau> listeBateauxOrdi = epoqueFactory.creerBateaux();
		Plateau plateauOrdinateur = new Plateau(listeBateauxOrdi);
		ordinateur = new JoueurOrdinateur(plateauOrdinateur, listeBateauxOrdi, strat);
		joueurCourant = JOUEURHUMAIN;
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * 
	 * @param p
	 */
	private void stockCaseSelectionnee(Position p) {
		caseSelectionnee = p;
	}

	/**
	 * permet aux joueurs de tirer
	 */
	public void tirer() {
		if (joueurCourant == JOUEURHUMAIN) {
			// L'ordinateur subit le tir a la case que le joueur a selectionne
			// dans l'interface graphique
			ordinateur.subirTir(caseSelectionnee);
			if (!ordinateur.aPerdu()) {
				// On change le joueur qui doit jouer
				changerJoueurCourant();

				// On notifie l'interface graphique du tir du joueur humain
				setChanged();
				notifyObservers();

				// L'ordinateur joue son tour directement
				tirer();
			} else {
				// On notifie l'interface graphique du tir du joueur humain
				setChanged();
				notifyObservers();
			}
		} else {
			// L'ordinateur recupere la prochaine position de tir
			Position p = ordinateur.recupPosTir(humain.getListeCaseTouche(), humain.getListeCaseRate());
			// L'humain subit le tir
			humain.subirTir(p);
			if (!humain.aPerdu()) {
				// On change le joueur courant, c'est a l'humain de jouer
				changerJoueurCourant();

				setChanged();
				notifyObservers();
			} else {
				// On notifie l'interface graphique du tir du joueur humain
				setChanged();
				notifyObservers();
			}
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

		if (joueurCourant == JOUEURHUMAIN) {	
	       
			valide = !ordinateur.caseDejaTouchee(p);
		}
		
		if (valide)
			stockCaseSelectionnee(p);
		
		return valide;
	}

	/**
	 * Indique si la partie est terminee
	 */
	public boolean partieTerminee() {
		return (ordinateur.aPerdu() || humain.aPerdu());
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
		BatailleNavale bataille = factory.getBatailleDAO().chargerPartie(nomFichier);
		this.humain = bataille.getHumain();
		this.ordinateur = bataille.getOrdinateur();
		this.joueurCourant = bataille.getJoueurCourant();
		
		setChanged();
		notifyObservers();
	}

	public JoueurOrdinateur getOrdinateur() {
		return ordinateur;
	}
	
	public void setOrdinateur(JoueurOrdinateur ordi) {
		this.ordinateur = ordi;
	}

	public JoueurHumain getHumain() {
		return humain;
	}

	public void setHumain(JoueurHumain humain) {
		this.humain = humain;
	}
	
	public int getJoueurCourant() {
		return joueurCourant;
	}
	
	public void setJoueurCourant(int n) {
		joueurCourant = n;
	}
	
	public void ajouterNomPartieSauvegardee(String nom) {
		nomPartiesSauvegardees.add(nom);

		setChanged();
		notifyObservers();
	}

	public String getNomPartieSauvegardee(int index) {
		return nomPartiesSauvegardees.get(index);
	}

	public int getTailleListePartiesSauvegardees() {
		return nomPartiesSauvegardees.size();
	}

	public int getNombreTirsRates(int joueur) {
		if (joueur == 0)
			return humain.getNombreTirsRates();

		return ordinateur.getNombreTirsRates();
	}

	public int getNombreTirsReussis(int joueur) {
		if (joueur == 0)
			return humain.getNombreTirsReussis();

		return ordinateur.getNombreTirsReussis();
	}
}
