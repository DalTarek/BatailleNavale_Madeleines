package dao;

import modele.BatailleNavale;

public class CsvDAO extends BatailleDAO {
	private static CsvDAO instance = null;
	
	private CsvDAO() {
		
	}
	
	public static CsvDAO getInstance() {
		if (instance == null)
			return new CsvDAO();
		return instance;
	}

	@Override
	public void sauvegarderPartie(BatailleNavale bataille, String nomFichier) {
		new CsvWriter(bataille, nomFichier);
	}

	@Override
	public BatailleNavale chargerPartie(String nomFichier) {
		CsvReader r = new CsvReader();
		return r.run(nomFichier);
	}

}
