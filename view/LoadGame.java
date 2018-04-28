package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.BatailleNavale;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import test.Application;

public class LoadGame extends JPanel {

    private Application application;

    private JButton playButton;
    private JList savedGamesList;
    
    private BatailleNavale bataille;

    public LoadGame(Application app, BatailleNavale bataille) {
        application = app;
        this.bataille = bataille;
        buildPanel();
    }

    private void buildPanel() {
        this.setLayout(new GridLayout(2, 1));

        // temporary selections
        String[] games = {"game1", "game2"};
        // load dynamically names of games saved
        // TODO
        
        savedGamesList = new JList<>(games);

        savedGamesList.addListSelectionListener(new ListSelectionListener(){
        
            @Override
            public void valueChanged(ListSelectionEvent e) {
                playButton.setEnabled(true);
            }
        });

        this.add(savedGamesList);

        playButton = new JButton("Jouer");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedGamesList.clearSelection();
                playButton.setEnabled(false);

                // load the state of the game with the choosen name
                // TODO
                bataille.chargerPartie("test.csv"/*a changer*/);

                // switch to the game
                application.switchToPanel("jeu");
            }
        });

        playButton.setEnabled(false);
        this.add(playButton);
    }
    
}