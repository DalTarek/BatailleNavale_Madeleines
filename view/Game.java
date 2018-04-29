package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import modele.BatailleNavale;
import test.Application;

@SuppressWarnings("deprecation")
public class Game extends JPanel implements Observer {
    final public static int BUTTONSNUMBER = 10;

    private Application application;
    
    private BatailleNavale bataille;

    public Game(Application app, BatailleNavale bataille) {
        super(new BorderLayout());
        application = app;
        this.bataille = bataille;
        this.bataille.addObserver(this);
        this.buildPanel();
    }

    private void buildPanel() {
        /*JPanel tablesPanel = new JPanel(new GridLayout(2, 1));        

        JTable playerTable = new JTable(BUTTONSNUMBER+1, BUTTONSNUMBER+1);

        JTable computerTable = new JTable(BUTTONSNUMBER+1, BUTTONSNUMBER+1);

        for (int i = 1; i < BUTTONSNUMBER+1; i++) {
            playerTable.setValueAt(i, i, 0);
            playerTable.setValueAt((char) (i-1 + 65), 0, i);

            computerTable.setValueAt(i, i, 0);
            computerTable.setValueAt((char) (i-1 + 65), 0, i);
        }

        /* TEST */
        /*TableCellRenderer renderer = new CustomTableCellRenderer();
        try {
			playerTable.setDefaultRenderer(Class.forName("java.lang.Integer"), renderer);
			computerTable.setDefaultRenderer(Class.forName("java.lang.Integer"), renderer);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        playerTable.setEnabled(false);
        computerTable.setRowSelectionAllowed(false);

        tablesPanel.add(computerTable);
        tablesPanel.add(playerTable);

        this.add(tablesPanel, BorderLayout.CENTER);*/
    	
    	JPanel plateauxPanel = new JPanel(new GridLayout(2, 1));
    	
    	Font font = new Font(Font.DIALOG, Font.BOLD, 11);
    	
    	JPanel computerPanel = new JPanel(new GridLayout(BUTTONSNUMBER + 1, BUTTONSNUMBER + 1));
    	for (int j = 0; j < BUTTONSNUMBER + 1; j++) {
    		for (int i = 0; i < BUTTONSNUMBER + 1; i++) {
    			if (j == 0) {
    				if (i != 0) {
	    				JButton b = new JButton("" + (char)(i+64));
	        			computerPanel.add(b);
    				} else {
    					JButton b = new JButton();
    					computerPanel.add(b);
    				}
    			} else {
    				if (i == 0) {
    					if (j != 0) {
	    					JButton b = new JButton("" + (j));
	    					b.setFont(font);
	    	    			computerPanel.add(b);
    					}
    				} else {
		    			JButton b = new JButton();
		    			computerPanel.add(b);
    				}
    			}
    		}
    	}
    	
    	JPanel playerPanel = new JPanel(new GridLayout(BUTTONSNUMBER + 1, BUTTONSNUMBER + 1));
    	for (int j = 0; j < BUTTONSNUMBER + 1; j++) {
    		for (int i = 0; i < BUTTONSNUMBER + 1; i++) {
    			if (j == 0) {
    				if (i != 0) {
	    				JButton b = new JButton("" + (char)(i+64));
	    				b.setEnabled(false);
	        			playerPanel.add(b);
    				} else {
    					JButton b = new JButton();
    					b.setEnabled(false);
    					playerPanel.add(b);
    				}
    			} else {
    				if (i == 0) {
    					if (j != 0) {
	    					JButton b = new JButton("" + (j));
	    					b.setFont(font);
	    					b.setEnabled(false);
	    	    			playerPanel.add(b);
    					}
    				} else {
		    			JButton b = new JButton();
		    			b.setEnabled(false);
		    			playerPanel.add(b);
    				}
    			}
    		}
    	}
    	
    	plateauxPanel.add(computerPanel);
    	plateauxPanel.add(playerPanel);
    	
    	this.add(plateauxPanel, BorderLayout.CENTER);
    	
    	/* --------------------------------------- */
    	
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
                    bataille.sauvegarderPartie(name);
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
            	/*if (bataille.estValide(/* position )) {
            		bataille.tirer();
            	} else {
            		shoot.setEnabled(false);
            	}*/      		
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}