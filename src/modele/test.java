package modele;

import java.util.ArrayList;

import epoque.EpoqueXVI;
import test.Application;

public class test {
	public static void main(String[] args) {
		//testConstructeurPlateauListBateau();
		testAffichagePlateau();
    }
	
	public static void TestSubirTir(){
		//TEST SUBITIR(position)
        Bateau bateau1=new BateauSimple(4,4,new Position(0,1),false);
        Bateau bateau2=new BateauSimple(3,3,new Position(0,3),false);
        ArrayList<Bateau>listb=new ArrayList<Bateau>();
        listb.add(bateau1);
        listb.add(bateau2);
        
        
        int[][] plat={{0,1,0,1,0},{0,1,0,1,0},{0,1,0,1,0},{0,1,0,0,0},{0,0,0,0,0}};
      /* for (int i =0;i<plat.length;i++){
    	   for(int j=0;j<plat[0].length;j++){
    		   System.out.print(plat[i][j]);
    	   }
    	   System.out.println("");
       }*/
        Plateau p=new Plateau(plat);
        
        
        JoueurHumain jh=new JoueurHumain(p,listb);
        
        jh.subirTir(new Position(3,1));
	}
	
	public static void testConstructeurPlateauListBateau(){
		  
	      Bateau bateau2=new BateauSimple(3,3,new Position(0,3),false);
	      Bateau bateau1=new BateauSimple(4,4,new Position(0,1),true);
	      ArrayList<Bateau>listb=new ArrayList<Bateau>();
	      listb.add(bateau1);
	      listb.add(bateau2);
	      Plateau p=new Plateau(listb);
	      
	      for (int i =0;i<p.TAILLELIGNE;i++){
	    	   for(int j=0;j<p.TAILLELIGNE;j++){
	    		   System.out.print(p.getValeur(j, i));
	    	   }
	    	   System.out.println("");
	       }
	}
	
	private static void testAffichagePlateau() {
		ArrayList<Bateau> listeBateaux = new EpoqueXVI().creerBateaux();
		Plateau plateau = new Plateau(listeBateaux);
		System.out.println(plateau.toString());
	}
}
