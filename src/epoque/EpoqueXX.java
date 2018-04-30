package epoque;

import java.util.ArrayList;
import modele.Bateau;
import modele.BateauSimple;

public class EpoqueXX extends EpoqueFactory {
      
	protected Bateau getBateauTresLong() {
        return new BateauSimple(2, 5, null, random.nextBoolean());
    }

    protected  Bateau getBateauLong() {
        return new BateauSimple(2, 4, null, random.nextBoolean());
    }

    protected Bateau getBateauMoyen() {
        return new BateauSimple(2, 3, null, random.nextBoolean());
    }

    protected Bateau getBateauCourt() {
        return new BateauSimple(2, 2, null, random.nextBoolean());
    }
}
