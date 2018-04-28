package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import modele.BatailleNavale;
import test.Application;

public class Game extends JPanel {
    final public static int BUTTONSNUMBER = 10;

    private Application application;
    
    private BatailleNavale bataille;

    public Game(Application app, BatailleNavale bataille) {
        super(new BorderLayout());
        application = app;
        this.bataille = bataille;
        this.buildPanel();
    }

    private void buildPanel() {
        JPanel tablesPanel = new JPanel(new GridLayout(2, 1));        

        JTable playerTable = new JTable(BUTTONSNUMBER+1, BUTTONSNUMBER+1);

        JTable computerTable = new JTable(BUTTONSNUMBER+1, BUTTONSNUMBER+1);

        for (int i = 1; i < BUTTONSNUMBER+1; i++) {
            playerTable.setValueAt(i, i, 0);
            playerTable.setValueAt((char) (i-1 + 65), 0, i);

            computerTable.setValueAt(i, i, 0);
            computerTable.setValueAt((char) (i-1 + 65), 0, i);
        }

        playerTable.setEnabled(false);
        computerTable.setRowSelectionAllowed(false);

        tablesPanel.add(computerTable);
        tablesPanel.add(playerTable);

        this.add(tablesPanel, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(2, 1));

        JButton exit = new JButton("Quitter");
        exit.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // open a window to ask user for saving the game and ask its name
                int answer = JOptionPane.showConfirmDialog(null, "Sauvegarder avant de quitter ?", "Sauvegarde", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (answer==JOptionPane.CANCEL_OPTION)
                    return;
                if (answer==JOptionPane.YES_OPTION){
                    String name = (String) JOptionPane.showInputDialog(null, "Quel nom souhaitez-vous donner à la sauvegarde ?");

                    // save the state of the game in a file using DAO
                    // TODO
                    bataille.sauvegarderPartie("test.csv" /*a changer*/);
                }

                // go back to main menu
                application.switchToPanel("menu");
            }
        });

        buttons.add(exit);

        JButton shoot = new JButton("Feu!");
        shoot.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selection of player table
                // verify if it can be selected ( a shoot was done yet)
                // yes : shoot
                // no : disable button
                // TODO
            	if (bataille.estValide(/* position */)) {
            		bataille.tirer();
            	} else {
            		shoot.setEnabled(false);
            	}      		
            }
        });

        buttons.add(shoot);

        this.add(buttons, BorderLayout.EAST);
        
    }

    private class CustomListener implements ActionListener {
        int xCoord, yCoord;

        public CustomListener(int i, int j) {
            xCoord = i;
            yCoord = j;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {

        }
    }
}