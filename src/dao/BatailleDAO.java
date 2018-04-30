package dao;

import modele.BatailleNavale;

public abstract class BatailleDAO {
	public abstract void sauvegarderPartie(BatailleNavale bn, String nomFichier);
	public abstract BatailleNavale chargerPartie(String nomFichier);
}
