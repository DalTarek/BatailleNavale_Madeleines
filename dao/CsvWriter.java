package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modele.BatailleNavale;
import modele.Plateau;
import modele.Position;

public class CsvWriter {

	public CsvWriter(BatailleNavale bataille, String nomFichier) {
		genererFichierCsv(bataille, nomFichier);
	}
	
	private static void genererFichierCsv(BatailleNavale bataille, String nomFichier) {
		try {
			FileWriter writer = new FileWriter(nomFichier);
			String csvSeparator = "  |  ";
			
			// joueur courant
			writer.append(bataille.getJoueurCourant() + "\n\n");
			
			// liste des cases touchees par le joueur ordinateur		
			writer.append("Liste des cases touchees par le joueur Ordinateur\n");
			ArrayList<Position> listeCaseToucheeHumain = bataille.getHumain().getListeCaseTouche();
			for (Position p : listeCaseToucheeHumain) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}

			writer.append("\n\n");

			// liste des cases ratees par le joueur ordinateur						
			writer.append("Liste des cases ratees par le joueur Ordinateur\n");
			ArrayList<Position> listeCaseRateeHumain = bataille.getHumain().getListeCaseRate();
			for (Position p : listeCaseRateeHumain) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}
			
			writer.append("\n\n");
			
			// plateau du joueur ordinateur
			writer.append("Plateau du joueur Ordinateur\n");
			Plateau plateauOrdinateur = bataille.getOrdinateur().getPlateau();				
			for (int j = 0; j < plateauOrdinateur.TAILLELIGNE; j++) {
				for (int i = 0; i < plateauOrdinateur.TAILLELIGNE; i++) {					
					writer.append("" + plateauOrdinateur.getValeur(i, j));
					writer.append(csvSeparator);
				}
				writer.append("\n");
			}

			writer.append("\n\n");
			
			// liste des cases touchees par le joueur humain
			writer.append("Liste des cases touchees par le joueur Humain\n");
			ArrayList<Position> listeCaseToucheeOrdi = bataille.getOrdinateur().getListeCaseTouche();
			for (Position p : listeCaseToucheeOrdi) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}

			writer.append("\n\n");

			// liste des cases ratees par le joueur humain	
			writer.append("Liste des cases ratees par le joueur Humain\n");
			ArrayList<Position> listeCaseRateeOrdi = bataille.getOrdinateur().getListeCaseRate();
			for (Position p : listeCaseRateeOrdi) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}
			
			writer.append("\n\n");
			
			// plateau du joueur humain
			writer.append("Plateau du joueur Humain\n");
			Plateau plateauHumain = bataille.getHumain().getPlateau();
			for (int j = 0; j < plateauHumain.TAILLELIGNE; j++) {
				for (int i = 0; i < plateauHumain.TAILLELIGNE; i++) {
					writer.append("" + plateauHumain.getValeur(i, j));
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
