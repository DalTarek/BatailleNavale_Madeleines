package modele.strategie;

import java.util.ArrayList;

import modele.Position;

/**
 * Strategie
 */
public interface Strategie {
    /**
     * Permet de récupérer la position du prochain tir de l'ordinateur
     * @param p : plateau sur lequel chercher la position
     */
    public Position getProchainTir(ArrayList<Position> listeTirsReussis, ArrayList<Position> listeTirsRates);
}