package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modele.BatailleNavale;
import modele.Bateau;
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
			writer.append(bataille.getJoueurCourant() + "\n");
			
			// plateau du joueur humain
			writer.append("Plateau joueur humain\n");
			Plateau plateauHumain = bataille.getHumain().getPlateau();
			for (int j = 0; j < plateauHumain.TAILLELIGNE; j++) {
				for (int i = 0; i < plateauHumain.TAILLELIGNE; i++) {
					writer.append("" + plateauHumain.getValeur(i, j));
					writer.append(csvSeparator);
				}
				writer.append("\n");
			}
			
			// liste des bateaux pour le joueur humain
			writer.append("Bateaux humain\n");
			ArrayList<Bateau> bateauHumain = bataille.getHumain().getListeBateau();
			for (Bateau b : bateauHumain) {
				writer.append(b.getVie() + " , " + b.getLongueur() + " , " + b.getPosX() + " , " + b.getPosY() + " , " + b.getOrientation() + "\n");
			}
			
			// liste des cases touchees par le joueur ordinateur		
			writer.append("Cases touchees ordinateur\n");
			ArrayList<Position> listeCaseToucheeHumain = bataille.getHumain().getListeCaseTouche();
			for (Position p : listeCaseToucheeHumain) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}
			
			// liste des cases ratees par le joueur ordinateur						
			writer.append("Cases ratees ordinateur\n");
			ArrayList<Position> listeCaseRateeHumain = bataille.getHumain().getListeCaseRate();
			for (Position p : listeCaseRateeHumain) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}
			
			// compteur tirs reussis pour le joueur humain
			writer.append("Tirs reussis humain\n");
			writer.append(bataille.getHumain().getNombreTirsReussis() + "\n");

			// compteur tirs rates pour le joueur humain
			writer.append("Tirs rates humain\n");
			writer.append(bataille.getHumain().getNombreTirsRates() + "\n");
			
			// plateau du joueur ordinateur
			writer.append("Plateau joueur ordinateur\n");
			Plateau plateauOrdinateur = bataille.getOrdinateur().getPlateau();				
			for (int j = 0; j < plateauOrdinateur.TAILLELIGNE; j++) {
				for (int i = 0; i < plateauOrdinateur.TAILLELIGNE; i++) {					
					writer.append("" + plateauOrdinateur.getValeur(i, j));
					writer.append(csvSeparator);
				}
				writer.append("\n");
			}
			
			// liste des bateaux pour le joueur ordinateur
			writer.append("Bateaux ordinateur\n");
			ArrayList<Bateau> bateauOrdi = bataille.getOrdinateur().getListeBateau();
			for (Bateau b : bateauOrdi) {
				writer.append(b.getVie() + " , " + b.getLongueur() + " , " + b.getPosX() + " , " + b.getPosY() + " , " + b.getOrientation() + "\n");
			}
			
			// strategie du joueur ordinateur
			writer.append("Strategie ordinateur\n");
			writer.append(bataille.getOrdinateur().getStrategie() + "\n");
			
			// liste des cases touchees par le joueur humain
			writer.append("Cases touchees humain\n");
			ArrayList<Position> listeCaseToucheeOrdi = bataille.getOrdinateur().getListeCaseTouche();
			for (Position p : listeCaseToucheeOrdi) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}

			// liste des cases ratees par le joueur humain	
			writer.append("Cases ratees humain\n");
			ArrayList<Position> listeCaseRateeOrdi = bataille.getOrdinateur().getListeCaseRate();
			for (Position p : listeCaseRateeOrdi) {
				writer.append(p.getX() + " , " + p.getY() + "\n");
			}
			
			// compteur tirs reussis pour le joueur ordinateur
			writer.append("Tirs reussis ordinateur\n");
			writer.append(bataille.getOrdinateur().getNombreTirsReussis() + "\n");

			// compteur tirs rates pour le joueur ordinateur
			writer.append("Tirs rates ordinateur\n");
			writer.append(bataille.getOrdinateur().getNombreTirsRates() + "\n");
		
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
