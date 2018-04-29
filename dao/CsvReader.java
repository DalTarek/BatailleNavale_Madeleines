package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import modele.BatailleNavale;

public class CsvReader {
	private BatailleNavale bataille;

	public CsvReader() {
		//bataille = new BatailleNavale(AbstractDAOFactory.getAbstractDAOFactory());
	}
	
	public BatailleNavale run(String nomFichier) {
		BufferedReader br = null;
		String line = "";
		String csvSeparator = " | ";
		try {
			br = new BufferedReader(new FileReader(nomFichier));		
			while ((line = br.readLine()) != null) {
				String[] ligne = line.split(csvSeparator);
				bataille.setJoueurCourant(Integer.parseInt(ligne[0]));
				//TODO Recuperer les plateaux des deux joueurs
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			  if (br != null) {
				  try {
					  br.close();
				  } catch (IOException e) {
					  e.printStackTrace();
				  }
			  }
		  }
			
		return bataille;
	}
}
