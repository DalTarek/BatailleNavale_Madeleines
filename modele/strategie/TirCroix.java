package modele.strategie;

import modele.Position;
import modele.Plateau;

import java.util.ArrayList;

/**
 * TirCroix
 */
public class TirCroix {

    public Position getProchainTir(ArrayList<Position> listeTirsReussis, ArrayList<Position> listeTirsRates) {
        Position p = null;
        // On stocke la dernière position sélectionnée pour en déduire de la suivante
        Position positionTirPrecedent = null;

        // On stocke les dernières positions de chaque liste
        Position dernierePositionReussie = listeTirsReussis.get(listeTirsReussis.size());
        Position dernierePositionRatee = listeTirsRates.get(listeTirsRates.size());
        // Si la dernière position de la liste des tirs ratés a des coordonnées supérieures (sur Y ou sur X si Y équivalent)
        // à la dernière position de la liste des tirs réussis
        // Il faut récupérer la dernière position des tirs ratés
        if (dernierePositionRatee.compareTo(dernierePositionReussie) == 1)
            positionTirPrecedent = dernierePositionRatee;
        else
            positionTirPrecedent = dernierePositionReussie;

        int xSuivant, ySuivant;
        // Si le tir précédent était sur l'avant dernière colonne
        // le prochain tir sera sur la première colonne et la ligne suivante
        if (positionTirPrecedent.getX() == Plateau.TAILLELIGNE-2) {
            xSuivant = 0;
            ySuivant = positionTirPrecedent.getY() + 1;
        
        // Si le tir précédent était sur la dernière colonne
        // le prochain tir sera sur la deuxième colonne et la ligne suivante
        } else if (positionTirPrecedent.getX() == Plateau.TAILLELIGNE-1) {
            xSuivant = 1;
            ySuivant = positionTirPrecedent.getY() + 1;
        // Sinon le tir sera 2 colonnes plus loin sur la même ligne
        } else {
            xSuivant = positionTirPrecedent.getX() + 2;
            ySuivant = positionTirPrecedent.getY();
        }

        return new Position(xSuivant, ySuivant);
    }
}