package epoque;

import java.util.ArrayList;
import modele.Bateau;
import modele.BateauSimple;

public class EpoqueXVI extends EpoqueFactory {

    protected Bateau getBateauTresLong() {
        return new BateauSimple(1, 5, null, random.nextBoolean());
    }

    protected  Bateau getBateauLong() {
        return new BateauSimple(1, 4, null, random.nextBoolean());
    }

    protected Bateau getBateauMoyen() {
        return new BateauSimple(1, 3, null, random.nextBoolean());
    }

    protected Bateau getBateauCourt() {
        return new BateauSimple(1, 2, null, random.nextBoolean()); 
    }
}
