package modele.strategie;

import java.util.ArrayList;
import java.util.Random;

import modele.Position;
import modele.Plateau;

/**
 * TirAleatoire
 */
public class TirAleatoire implements Strategie {

    private Random random = new Random();

    public Position getProchainTir(ArrayList<Position> listeTirsReussis, ArrayList<Position> listeTirsRates) {
        // On crée une position aléatoire dans le plateau
        Position position = new Position(random.nextInt(Plateau.TAILLELIGNE), random.nextInt(Plateau.TAILLELIGNE));

        // Tant que la position choisie aléatoirement a déjà été sélectionnée
        while (listeTirsRates.contains(position) || listeTirsReussis.contains(position)) {
            // On récupère une nouvelle position aléatoire
            position = new Position(random.nextInt(Plateau.TAILLELIGNE), random.nextInt(Plateau.TAILLELIGNE));
        }

        return position;
    }
}