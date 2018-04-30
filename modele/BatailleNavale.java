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
	
	
	public BatailleNavale(AbstractDAOFactory factory, EpoqueFactory epoque, Strategie strat) {
		this.factory = factory;
		creerPartie(epoque, strat);
	}

	public BatailleNavale(AbstractDAOFactory factory) {
		this.factory = factory;

		// On stocke le nom des parties déjà présentes dans le dossier de sauvegarde
		String currentDirectory = System.getProperty("user.dir");

        // dossier contenant les sauvegardes
        File savesDirectory = new File(currentDirectory + "/sauvegardes");

        ArrayList<String> names = new ArrayList<>();
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
		
		System.out.println(ordinateur.getPlateau());
		System.out.println(humain.getPlateau());
		
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

	public void tirer() {
		if (joueurCourant == JOUEURHUMAIN) {
			ordinateur.subirTir(caseSelectionnee);
			if (!ordinateur.aPerdu())
				changerJoueurCourant();
				tirer();
		} else {
			Position p = ordinateur.recupPosTir();
			humain.subirTir(p);
			if (!humain.aPerdu())
				changerJoueurCourant();
		}
		
		setChanged();
		notifyObservers();
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
}
