package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modele.BatailleNavale;
import modele.Bateau;
import modele.BateauSimple;
import modele.JoueurHumain;
import modele.JoueurOrdinateur;
import modele.Plateau;
import modele.Position;
import modele.strategie.Strategie;
import modele.strategie.TirAleatoire;
import modele.strategie.TirCroix;

public class CsvReader {
	private BatailleNavale bataille;

	public CsvReader() {
		bataille = new BatailleNavale(AbstractDAOFactory.getAbstractDAOFactory());
	}
	
	public BatailleNavale run(String nomFichier) {
		BufferedReader br = null;
		String line = "";
		String csvPositionSeparator = " , ";
		String csvPlateauSeparator = "#";
		try {
			br = new BufferedReader(new FileReader(nomFichier));
			
			// on recupere le joueur courant
			while (!(line = br.readLine()).matches("Plateau joueur humain")) {
				if ((line == "0") || (line == "1")) {
					bataille.setJoueurCourant(Integer.parseInt(line));
				}
			}

			// on recupere le plateau du joueur humain
			int[][] t = new int[10][10];
			int j = 0;
			while (!(line = br.readLine()).matches("Bateaux humain")) {
				String[] ligne = line.split(csvPlateauSeparator);
				
				for (int i = 0; i < t.length; i++) {
					t[i][j] = Integer.parseInt(ligne[i]);
				}
				j++;
			}
			Plateau p = new Plateau(t);

			ArrayList<Bateau> bateauxHumain = new ArrayList<>();

			// on recupere les bateaux humain
			while (!(line = br.readLine()).matches("Cases touchees ordinateur")) {
				String[] ligne = line.split(csvPositionSeparator);
				bateauxHumain.add(new BateauSimple(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1]), new Position(Integer.parseInt(ligne[2]), Integer.parseInt(ligne[3])), Boolean.parseBoolean(ligne[4])));
			}

			JoueurHumain humain = new JoueurHumain(p, bateauxHumain);
			
			// on recupere la liste des cases touchees par le joueur ordinateur
			while (!(line = br.readLine()).matches("Cases ratees ordinateur")) {
				String[] ligne = line.split(csvPositionSeparator);
				humain.getListeCaseTouche().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
			}
			
			// on recupere la liste des cases ratees par le joueur ordinateur
			while (!(line = br.readLine()).matches("Tirs reussis humain")) {
				String[] ligne = line.split(csvPositionSeparator);
				humain.getListeCaseRate().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
			}

			// on recupere les tirs reussis du joueur humain
			while (!(line = br.readLine()).matches("Tirs rates humain")) {
				humain.setNombreTirsReussis(Integer.parseInt(line));
			}
			
			// on recupere les tirs rates du joueur humain
			while (!(line = br.readLine()).matches("Plateau joueur ordinateur")) {
				humain.setNombreTirsRates(Integer.parseInt(line));
			}

			bataille.setHumain(humain);
			
			// on recupere le plateau du joueur Ordinateur
			int[][] t2 = new int[10][10];
			int j2 = 0;
			while (!(line = br.readLine()).matches("Bateaux ordinateur")) {
				String[] ligne = line.split(csvPlateauSeparator);
				for (int i = 0; i < t2.length; i++) {
					t2[i][j2] = Integer.parseInt(ligne[i]);
				}
				j2++;
			}
			Plateau p2 = new Plateau(t2);

			ArrayList<Bateau> bateauxOrdi = new ArrayList<>();
			
			// on recupere les bateaux ordi
			while (!(line = br.readLine()).matches("Strategie ordinateur")) {
				String[] ligne = line.split(csvPositionSeparator);
				bateauxOrdi.add(new BateauSimple(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1]), new Position(Integer.parseInt(ligne[2]), Integer.parseInt(ligne[3])), Boolean.parseBoolean(ligne[4])));
			}

			Strategie strategie = null;
			// on recupere la strategie
			while (!(line = br.readLine()).matches("Cases touchees humain")) {
				if (line.matches("TirAleatoire"))
					strategie = new TirAleatoire();
				else
					strategie = new TirCroix();
			}

			JoueurOrdinateur ordinateur = new JoueurOrdinateur(p2, bateauxOrdi, strategie);

			
			// on recupere la liste des cases touchees par le joueur humain
			while (!(line = br.readLine()).matches("Cases ratees humain")) {
				String[] ligne = line.split(csvPositionSeparator);
				ordinateur.getListeCaseTouche().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
			}

			// on recupere la liste des cases ratees par le joueur humain
			while (!(line = br.readLine()).matches("Tirs reussis ordinateur")) {
				String[] ligne = line.split(csvPositionSeparator);
				ordinateur.getListeCaseRate().add(new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])));
			}

			// on recupere les tirs reussis du joueur ordinateur
			while (!(line = br.readLine()).matches("Tirs rates ordinateur")) {
				ordinateur.setNombreTirsReussis(Integer.parseInt(line));
			}

			// on recupere les tirs rates du joueur ordinateur
			while ((line = br.readLine()) != null) {
				ordinateur.setNombreTirsRates(Integer.parseInt(line));
			}

			bataille.setOrdinateur(ordinateur);


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
