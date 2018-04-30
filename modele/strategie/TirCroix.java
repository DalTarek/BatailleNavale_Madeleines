package modele.strategie;

import modele.Position;
import modele.Plateau;

import java.util.ArrayList;
import java.util.Random;

/**
 * TirCroix
 */
public class TirCroix implements Strategie {
    // Si on a vu tout le plateau en stratégie en croix, on continue en stratégie aléatoire
    private boolean tirAleatoire = false;

    private Random random = new Random();

    public Position getProchainTir(ArrayList<Position> listeTirsReussis, ArrayList<Position> listeTirsRates) {

        Position p = null;
        // On stocke la dernière position sélectionnée pour en déduire de la suivante
        Position positionTirPrecedent = null;

        // On stocke les dernières positions de chaque liste
        Position dernierePositionReussie = null;
        if (! listeTirsReussis.isEmpty() )
            dernierePositionReussie = listeTirsReussis.get(listeTirsReussis.size()-1);
        else
            dernierePositionReussie = new Position(0, 0);

        Position dernierePositionRatee = null;
        if (! listeTirsRates.isEmpty() )
            dernierePositionRatee = listeTirsRates.get(listeTirsRates.size()-1);
        else 
            dernierePositionRatee = new Position(0, 0);
            
        // Si les deux listes sont vides, on tire sur la première case du plateau
        if (listeTirsRates.isEmpty() && listeTirsReussis.isEmpty() )
            return new Position(0, 0);


        // Si la dernière position de la liste des tirs ratés a des coordonnées supérieures (sur Y ou sur X si Y équivalent)
        // à la dernière position de la liste des tirs réussis
        // Il faut récupérer la dernière position des tirs ratés
        if (dernierePositionRatee.compareTo(dernierePositionReussie) == 1)
            positionTirPrecedent = dernierePositionRatee;
        else
            positionTirPrecedent = dernierePositionReussie;


        // On a terminé de remplir le plateau en croix, on passe en stratégie aléatoire sur les cases restantes
        if (positionTirPrecedent.getY() >= Plateau.TAILLELIGNE-1 && positionTirPrecedent.getX() >= Plateau.TAILLELIGNE-2)
            tirAleatoire = true;


        int xSuivant, ySuivant;

        // Si le tir précédent était la dernière case du plateau (plus ou moins, possible que ce soit l'avant derniere)
        // On continue en 
        if (tirAleatoire) {
            Position position = new Position(random.nextInt(Plateau.TAILLELIGNE), random.nextInt(Plateau.TAILLELIGNE));

            // Tant que la position choisie aléatoirement a déjà été sélectionnée
            while (listeTirsRates.contains(position) || listeTirsReussis.contains(position)) {
                // On récupère une nouvelle position aléatoire
                position = new Position(random.nextInt(Plateau.TAILLELIGNE), random.nextInt(Plateau.TAILLELIGNE));
            }

            xSuivant = position.getX();
            ySuivant = position.getY();
        } else {

            // Si le tir précédent était sur l'avant dernière colonne
            // le prochain tir sera sur la première colonne et la ligne suivante
            if (positionTirPrecedent.getX() == Plateau.TAILLELIGNE-2) {
                xSuivant = 1;
                ySuivant = positionTirPrecedent.getY() + 1;
            
            // Si le tir précédent était sur la dernière colonne
            // le prochain tir sera sur la deuxième colonne et la ligne suivante
            } else if (positionTirPrecedent.getX() == Plateau.TAILLELIGNE-1) {
                xSuivant = 0;
                ySuivant = positionTirPrecedent.getY() + 1;
            // Sinon le tir sera 2 colonnes plus loin sur la même ligne
            } else {
                xSuivant = positionTirPrecedent.getX() + 2;
                ySuivant = positionTirPrecedent.getY();
            }
        }

        return new Position(xSuivant, ySuivant);
    }
}