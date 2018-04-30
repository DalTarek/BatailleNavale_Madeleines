package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import modele.BatailleNavale;
import modele.Plateau;
import modele.Position;

public class CsvReader {
	private BatailleNavale bataille;

	public CsvReader() {
		bataille = new BatailleNavale(AbstractDAOFactory.getAbstractDAOFactory());
	}
	
	public BatailleNavale run(String nomFichier) {
		BufferedReader br = null;
		String line = "";
		String csvPositionSeparator = " , ";
		String csvPlateauSeparator = " | ";
		try {
			br = new BufferedReader(new FileReader(nomFichier));
			
			// on recupere le joueur courant
			while (!(line = br.readLine()).matches("Liste des cases touchees par le joueur Ordinateur")) {
				if ((line == "0") || (line == "1")) {
					System.out.println(line);
					bataille.setJoueurCourant(Integer.parseInt(line));
				}
			}
			
			// on recupere la liste des cases touchees par le joueur ordinateur
			while (!(line = br.readLine()).matches("Liste des cases ratees par le joueur Ordinateur")) {
				if (line != "") {
				String[] ligne = line.split(csvPositionSeparator);
				System.out.println(ligne[0] + "  " + ligne[1]);
				bataille.getHumain().getListeCaseTouche().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
				}
			}
			
			// on recupere la liste des cases ratees par le joueur ordinateur
			while (!(line = br.readLine()).matches("Plateau du joueur Ordinateur")) {
				if (line != "") {
					String[] ligne = line.split(csvPositionSeparator);
					bataille.getHumain().getListeCaseRate().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
				}
			}
			
			// on recupere le plateau du joueur Ordinateur
			int[][] t = new int[10][10];
			int j = 0;
			while (!(line = br.readLine()).matches("Liste des cases touchees par le joueur Humain")) {
				String[] ligne = line.split(csvPlateauSeparator);
				for (int i = 0; i < t.length; i++) {
					System.out.println(ligne[i]);
					t[i][j] = Integer.parseInt(ligne[i]);
				}
				j++;
			}
			Plateau p = new Plateau(t);
			//System.out.println(p);
			bataille.getOrdinateur().setPlateau(p);
			
			// on recupere la liste des cases touchees par le joueur humain
			while (!(line = br.readLine()).matches("Liste des cases ratees par le joueur Humain")) {
				if (line != "") {
					String[] ligne = line.split(csvPositionSeparator);
					bataille.getOrdinateur().getListeCaseTouche().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
				}
			}

			// on recupere la liste des cases ratees par le joueur humain
			while (!(line = br.readLine()).matches("Plateau du joueur Humain")) {
				if (line != "") {
					String[] ligne = line.split(csvPositionSeparator);
					bataille.getOrdinateur().getListeCaseRate().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
				}
			}

			// on recupere le plateau du joueur Ordinateur
			int[][] t2 = new int[10][10];
			int j2 = 0;
			while ((line = br.readLine()) != null) {
				if (line != "") {
					String[] ligne = line.split(csvPlateauSeparator);
					for (int i = 0; i < t2.length; i++)
						t2[i][j2] = Integer.parseInt(ligne[i]);				
				}
				j2++;
			}
			Plateau p2 = new Plateau(t);
			bataille.getOrdinateur().setPlateau(p2);
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
