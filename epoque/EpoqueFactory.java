package epoque;

import java.util.ArrayList;
import modele.Bateau;

public abstract class EpoqueFactory {

    public ArrayList<Bateau> creerBateaux() {
        ArrayList<Bateau> bateaux = new ArrayList<>();

        bateaux.add(getBateauTresLong());
        bateaux.add(getBateauLong());
        bateaux.add(getBateauMoyen());
        bateaux.add(getBateauMoyen());
        bateaux.add(getBateauCourt());        

        return bateaux;
    }

    protected abstract Bateau getBateauTresLong();
    protected abstract Bateau getBateauLong();
    protected abstract Bateau getBateauMoyen();
    protected abstract Bateau getBateauCourt();
}
