package dao;

import java.io.FileWriter;
import java.io.IOException;

import modele.BatailleNavale;
import modele.Plateau;

public class CsvWriter {

	public CsvWriter(BatailleNavale bataille, String nomFichier) {
		genererFichierCsv(bataille, nomFichier);
	}
	
	private static void genererFichierCsv(BatailleNavale bataille, String nomFichier) {
		try {
			FileWriter writer = new FileWriter(nomFichier);
			String csvSeparator = " | ";
			
			writer.append(bataille.getJoueurCourant() + "\n\n");
			
			Plateau plateauHumain = bataille.getHumain().getPlateau();
			for (int i = 0; i < plateauHumain.TAILLELIGNE; i++) {
				for (int j = 0; j < plateauHumain.TAILLELIGNE; j++) {
					writer.append("" + plateauHumain.getValeur(i, j));
					writer.append(csvSeparator);
				}
				writer.append("\n");
			}
			writer.append("\n\n");
			Plateau plateauOrdinateur = bataille.getOrdinateur().getPlateau();
			for (int i = 0; i < plateauOrdinateur.TAILLELIGNE; i++) {
				for (int j = 0; j < plateauOrdinateur.TAILLELIGNE; j++) {
					writer.append("" + plateauOrdinateur.getValeur(i, j));
					writer.append(csvSeparator);
				}
				writer.append("\n");
			}
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
