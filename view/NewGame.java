package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import epoque.EpoqueFactory;
import epoque.EpoqueXVI;
import epoque.EpoqueXX;
import modele.BatailleNavale;
import modele.strategie.Strategie;
import modele.strategie.TirAleatoire;
import modele.strategie.TirCroix;

import javax.swing.event.ListSelectionEvent;


import test.*;

/**
 * @author guillaume bergerot
 */
public class NewGame extends JPanel {
    private Application application;

    private JList ageList, strategyList;
    private JButton playButton;
    private BatailleNavale bataille;

    public NewGame(Application app, BatailleNavale bataille) {
        application = app;
        this.buildPanel();
        this.bataille=bataille;
    }

    private void buildPanel() {
        this.setLayout(new GridLayout(2, 1));

        JPanel lists = new JPanel(new GridLayout(1, 2));

        // temporary selections

        String[] ages = {"EpoqueXVI", "EpoqueXX"};

        // load dynamically all the strategies allowed
        // TODO 

        ageList = new JList<String>(ages);
        lists.add(ageList);

        // temporary selections

        String[] strategies = {"Tir aleatoire", "Tir en croix"};

        // load dynamically all the strategies allowed
        // TODO

        strategyList = new JList<String>(strategies);
        strategyList.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // if an item of age list is selected
                // and value changed in this list (strategy list)
                // we can enable play button
                if (!ageList.isSelectionEmpty())
                    playButton.setEnabled(true);
                }
        });
        lists.add(strategyList);

        this.add(lists);

        playButton = new JButton("Jouer");
        playButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // create a new game with all its variables
                // TODO
            	//epoque/stratégie
            	/***********************A REVOIR***********************/
            	//verification de l'époque 
            	if((String)ageList.getSelectedValue() == "EpoqueXVI"){
            		//vérification de la stratégie
                	if((String)strategyList.getSelectedValue() =="Tir aleatoire"){
                		//creation de la partie en fonction de la stratégie et de l'époque
                    	bataille.creerPartie(new EpoqueXVI(),new TirAleatoire());
                	}else {
                		if((String)strategyList.getSelectedValue() =="Tir en croix"){
	                		//creation de la partie en fonction de la stratégie et de l'époque
	                    	bataille.creerPartie(new EpoqueXVI(),new TirCroix());
                		}
                	}
            	}else { 
            		if((String)ageList.getSelectedValue() =="EpoqueXX") {
	            		//vérification de la stratégie
	                	if((String)strategyList.getSelectedValue() =="Tir aleatoire"){
	                		//creation de la partie en fonction de la stratégie et de l'époque
	                    	bataille.creerPartie(new EpoqueXX(),new TirAleatoire());
	                	}else {
	                		if((String)strategyList.getSelectedValue() =="Tir en croix"){
		                		//creation de la partie en fonction de la stratégie et de l'époque
		                    	bataille.creerPartie(new EpoqueXX(),new TirCroix());
	                		}
	                	}
                	}
            	}
            	/*******************************************************/
            	
                ageList.clearSelection();
                strategyList.clearSelection();
                playButton.setEnabled(false);
                application.switchToPanel("jeu");
            }
        });
        playButton.setEnabled(false);

        this.add(playButton);
    }
}