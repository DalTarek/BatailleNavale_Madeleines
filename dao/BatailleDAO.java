package dao;

import modele.BatailleNavale;

public abstract class BatailleDAO {
	public abstract void sauvegarderPartie(BatailleNavale bn);
	public abstract BatailleNavale chargerPartie();
}
