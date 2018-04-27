package modele;

import java.util.ArrayList;

import test.Application;

public class test {
	public static void main(String[] args) {
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
        
        jh.subirTir(new Position(2,3));
    }
}
