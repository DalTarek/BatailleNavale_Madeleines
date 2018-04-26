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
	public void sauvegarderPartie(BatailleNavale bataille) {
		new CsvWriter(bataille);
	}

	@Override
	public BatailleNavale chargerPartie() {
		CsvReader r = new CsvReader();
		return r.run();
	}

}
