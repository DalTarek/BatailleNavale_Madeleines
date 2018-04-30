package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;

import epoque.EpoqueFactory;
import epoque.EpoqueXVI;
import epoque.EpoqueXX;
import modele.BatailleNavale;
import modele.strategie.Strategie;
import modele.strategie.TirAleatoire;
import modele.strategie.TirCroix;

import javax.swing.event.ListSelectionEvent;


import test.*;

public class NewGame extends JPanel {
    final private static String[] ages = {"EpoqueXVI", "EpoqueXX"};
    final private static String[] strategies = {"Tir aleatoire", "Tir en croix"};

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

        JPanel lists = new JPanel(new GridLayout(2, 2));

        JLabel agesLabel = new JLabel("Epoques");
        agesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        agesLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel strategiesLabel = new JLabel("Strategies");
        strategiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        strategiesLabel.setBorder(BorderFactory.createLineBorder(Color.black));        

        lists.add(agesLabel);
        lists.add(strategiesLabel);

        ageList = new JList<String>(ages);
        ageList.setBorder(BorderFactory.createLineBorder(Color.black));
        
        lists.add(ageList);

        strategyList = new JList<String>(strategies);
        strategyList.setBorder(BorderFactory.createLineBorder(Color.black));        
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
                //epoque/strategie
                
                EpoqueFactory epoque = null;
                Strategie strategie = null;

                // verification de l'epoque                 
                String ageListValue = (String)ageList.getSelectedValue();
                if (ageListValue == ages[0]) {
                    epoque = new EpoqueXVI();
                } else if (ageListValue == ages[1]) {
                    epoque = new EpoqueXX();
                }

                // verification de la strategie
                String strategyListValue = (String)strategyList.getSelectedValue();
                if (strategyListValue == strategies[0]) {
                    strategie = new TirAleatoire();
                } else if (strategyListValue == strategies[1]) {
                    strategie = new TirCroix();
                }

                bataille.creerPartie(epoque, strategie);

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