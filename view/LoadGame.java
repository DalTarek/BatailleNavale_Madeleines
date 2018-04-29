package view;

import java.awt.GridLayout;

import java.io.File;

import java.util.Observer;
import java.util.Observable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modele.BatailleNavale;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import test.Application;

public class LoadGame extends JPanel implements Observer {

    private Application application;

    private JButton playButton;
    private JList savedGamesList;
    
    private BatailleNavale bataille;

    public LoadGame(Application app, BatailleNavale bataille) {
        application = app;
        this.bataille = bataille;
        buildPanel();

        bataille.addObserver(this);
    }

    private void buildPanel() {
        this.setLayout(new GridLayout(2, 1));

        String currentDirectory = System.getProperty("user.dir");

        // get directory containing saves
        File savesDirectory = new File(currentDirectory + "/sauvegardes");

        ArrayList<String> names = new ArrayList<>();
        if (savesDirectory.exists()) {
            for (File f : savesDirectory.listFiles()) {
                // discard extension of file
                String name = f.getName().substring(0, f.getName().indexOf("."));
                names.add(name);
            }
        }
        
        savedGamesList = new JList<>(names.toArray());

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
                // load a game based on selected file name
                String name = (String)savedGamesList.getSelectedValue();
                bataille.chargerPartie(System.getProperty("user.dir") + "/sauvegardes/" + name + ".csv");

                savedGamesList.clearSelection();
                playButton.setEnabled(false);

                // switch to the game
                application.switchToPanel("jeu");
            }
        });

        playButton.setEnabled(false);
        this.add(playButton);
    }

    @Override
	public void update(Observable arg0, Object arg1) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < bataille.getTailleListePartiesSauvegardees(); i++) {
            names.add(bataille.getNomPartieSauvegardee(i));
        }

        savedGamesList.setListData(names.toArray());
	}
    
}