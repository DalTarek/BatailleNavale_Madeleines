package epoque;

import java.util.ArrayList;
import modele.Bateau;
import modele.BateauSimple;

public class EpoqueXVI extends EpoqueFactory {

    protected Bateau getBateauTresLong(boolean b) {
        return new BateauSimple(1, 5, null, b);
    }

    protected  Bateau getBateauLong(boolean b) {
        return new BateauSimple(1, 4, null, b);
    }

    protected Bateau getBateauMoyen(boolean b) {
        return new BateauSimple(1, 3, null, b);
    }

    protected Bateau getBateauCourt(boolean b) {
        return new BateauSimple(1, 2, null, b);        
    }
}
