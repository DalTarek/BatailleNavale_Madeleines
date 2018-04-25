package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

import test.*;

/**
 * @author guillaume bergerot
 */
public class MainMenu extends JPanel {
    private Application application;

    private JButton newGameButton, savedGameButton;

    public MainMenu(Application app) {
        application = app;
        buildPanel();
    }
    
    private void buildPanel() {
        this.setLayout(new GridLayout(1, 2));

        // Defining a button to create a new game
        newGameButton = new JButton("Nouvelle partie");
        newGameButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                application.switchToPanel("nouvellePartie");
            }
        });

        this.add(newGameButton);

        // Defining a button to continue a previous game
        savedGameButton = new JButton("Reprendre une partie");
        savedGameButton.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                application.switchToPanel("reprendrePartie");
            }
        });

        this.add(savedGameButton);
    }

}