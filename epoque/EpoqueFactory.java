package epoque;

import java.util.ArrayList;
import modele.Bateau;

public abstract class EpoqueFactory {

    public ArrayList<Bateau> creerBateaux() {
        ArrayList<Bateau> bateaux = new ArrayList<>();

        bateaux.add(getBateauTresLong(true));
        bateaux.add(getBateauLong(false));
        bateaux.add(getBateauMoyen(false));
        bateaux.add(getBateauMoyen(true));
        bateaux.add(getBateauCourt(false));        

        return bateaux;
    }
    
    /**
     * 
     * @param b Vrai si le bateau est vertical, faux si horizontal
     * @return
     */
    protected abstract Bateau getBateauTresLong(boolean b);
    protected abstract Bateau getBateauLong(boolean b);
    protected abstract Bateau getBateauMoyen(boolean b);
    protected abstract Bateau getBateauCourt(boolean b);
}
