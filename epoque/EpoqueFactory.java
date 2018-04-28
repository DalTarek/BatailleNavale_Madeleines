package epoque;

import java.util.ArrayList;
import modele.Bateau;
import modele.Plateau;

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

        // On divise le plateau en 4 zones et on met un bateau dans chaque zone
        int zone = 0;
        int tailleMoitieLigne = Plateau.TAILLELIGNE / 2;

        // On prend 4 zones et on y met 1 bateau dans chaque
        for (int i = 0; i < bateaux.size() - 1; i++) {
            int xZone = zone % 2;
            int yZone = (int) zone / 2;

            Bateau bateau = bateaux.get(i);

            if (bateau.getOrientation()) { // le bateau est vertical
                bateau.setPosition(new Position(xZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne), yZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne - bateau.getLongueur())));
            } else { // le bateau est horizontal
                bateau.setPosition(new Position(xZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne - bateau.getLongueur()), yZone*tailleMoitieLigne + random.nextInt(tailleMoitieLigne)));                
            }

            zone++;
        }

        // Il reste un bateau, on le place à peu près au milieu
        Bateau bateau = bateaux.get(bateaux.size() - 1);
        if (bateau.getOrientation()) { // le bateau est vertical
            bateau.setPosition(new Position(tailleMoitieLigne, tailleMoitieLigne - bateau.getLongueur()/2));
        } else { // le bateau est horizontal
            bateau.setPosition(new Position(tailleMoitieLigne - bateau.getLongueur()/2, tailleMoitieLigne));
        }

        return bateaux;
    }
    
    /**
     * 
     * @param b Vrai si le bateau est vertical, faux si horizontal
     * @return
     */
    protected abstract Bateau getBateauTresLong();
    protected abstract Bateau getBateauLong();
    protected abstract Bateau getBateauMoyen();
    protected abstract Bateau getBateauCourt();
}
