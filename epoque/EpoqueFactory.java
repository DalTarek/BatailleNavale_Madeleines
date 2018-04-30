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

        boolean parcoursNormal = random.nextBoolean();

        int zone = 0;
        int tailleMoitieLigne = Plateau.TAILLELIGNE / 2;

        // On prend 4 zones et on y met 1 bateau dans chaque (Haut-gauche   /  Haut-droit    /   Bas-gauche   /   Bas-droit)
        for (int i = 0; i < bateaux.size() - 1; i++) {
            // Permet d'avoir les coordonnées de chaque zone
            // Haut-gauche -> 0,0
            // Haut-droit -> 1,0
            // Bas-gauche -> 0,1
            // Bas-droit -> 1,1
            int xZone = zone % 2;
            int yZone = (int) zone / 2;

            Bateau bateau = parcoursNormal ? bateaux.get(i) : bateaux.get((bateaux.size()-1)-i);

            int xBateau, yBateau;

            if (bateau.getOrientation()) { // le bateau est vertical

                // On place assez aléatoirement le bateau dans la zone, en regardant sa longueur pour ne pas qu'il en sorte
                // Random jusque tailleMoitieLigne-1 pour etre sûr d'en placer un au milieu à la fin (5eme bateau)

                xBateau = random.nextInt(tailleMoitieLigne-1);
                yBateau = (bateau.getLongueur() == tailleMoitieLigne) ? 0 
                                                            : random.nextInt(tailleMoitieLigne - bateau.getLongueur());

                bateau.setPosition(new Position(xZone*tailleMoitieLigne + xBateau, yZone*tailleMoitieLigne + yBateau));
            } else { // le bateau est horizontal

                // On place assez aléatoirement le bateau dans la zone, en regardant sa longueur pour ne pas qu'il en sorte              
                // Random jusque tailleMoitieLigne-1 pour etre sûr d'en placer un au milieu à la fin (5eme bateau)

                xBateau = (bateau.getLongueur() == tailleMoitieLigne) ? 0 
                                                            : random.nextInt(tailleMoitieLigne - bateau.getLongueur());
                yBateau = random.nextInt(tailleMoitieLigne-1);

                bateau.setPosition(new Position(xZone*tailleMoitieLigne + xBateau, yZone*tailleMoitieLigne + yBateau));                
            }

            zone++;
        }

        // Il reste un bateau, on le place au milieu
        Bateau bateau = parcoursNormal ? bateaux.get(bateaux.size() - 1) : bateaux.get(0);
        bateau.setPosition(new Position(tailleMoitieLigne-1, tailleMoitieLigne-1));

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
