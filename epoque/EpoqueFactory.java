package epoque;

import java.util.ArrayList;
import modele.Bateau;
import modele.Plateau;
import modele.Position;

import java.util.Random;

public abstract class EpoqueFactory {

    final public static Random random = new Random();

    public ArrayList<Bateau> creerBateaux() {
        ArrayList<Bateau> bateaux = new ArrayList<>();

        bateaux.add(getBateauTresLong());
        bateaux.add(getBateauLong());
        bateaux.add(getBateauMoyen());
        bateaux.add(getBateauMoyen());
        bateaux.add(getBateauCourt());    
        
        // Positionnement aléatoire des bateaux

        int zone = 0;
        int tailleMoitieLigne = Plateau.TAILLELIGNE / 2;

        // On prend 3 zones et on y met 1 bateau dans chaque (Haut-gauche   /  Haut-droit    /   Bas-gauche)
        for (int i = 0; i < bateaux.size() - 2; i++) {
            int xZone = zone % 2;
            int yZone = (int) zone / 2;

            Bateau bateau = bateaux.get(i);

            if (bateau.getOrientation()) { // le bateau est vertical
                bateau.setPosition(new Position(xZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne), yZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne - bateau.getLongueur() + 1)));
            } else { // le bateau est horizontal
                bateau.setPosition(new Position(xZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne - bateau.getLongueur() + 1), yZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne)));                
            }

            zone++;
        }
        
        // Il reste 2 bateaux, on en met un en bas a droite du plateau
        Bateau bateau2 = bateaux.get(bateaux.size() - 2);
        if (bateau2.getOrientation()) { // le bateau est vertical
        	bateau2.setPosition(new Position(tailleMoitieLigne + 2 + random.nextInt(3), tailleMoitieLigne + random.nextInt(3)));
        } else { // le bateau est horizontal
        	bateau2.setPosition(new Position(tailleMoitieLigne + 2, tailleMoitieLigne + random.nextInt(tailleMoitieLigne)));
        }

        // Il reste un bateau, on le place à peu près au milieu
        Bateau bateau = bateaux.get(bateaux.size() - 1);
        if (bateau.getOrientation()) { // le bateau est vertical
            bateau.setPosition(new Position(tailleMoitieLigne + random.nextInt(2), tailleMoitieLigne + random.nextInt(tailleMoitieLigne - bateau.getLongueur())));
        } else { // le bateau est horizontal
            bateau.setPosition(new Position(tailleMoitieLigne, tailleMoitieLigne + random.nextInt(tailleMoitieLigne)));
        }

        return bateaux;
    }

    /**
     * création de bateau selon l'époque choisie
     */
    protected abstract Bateau getBateauTresLong();
    protected abstract Bateau getBateauLong();
    protected abstract Bateau getBateauMoyen();
    protected abstract Bateau getBateauCourt();
}
