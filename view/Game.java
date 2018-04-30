package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import modele.BatailleNavale;
import modele.Plateau;
import modele.Position;
import test.Application;

import java.io.File;

@SuppressWarnings("deprecation")
public class Game extends JPanel implements Observer {
    final public static int BUTTONSNUMBER = 10;
    private JButton[][] buttonPlateauOrdinateur = new JButton[10][10];
	private JButton[][] buttonPlateauHumain = new JButton[10][10];
	
	private JButton shoot;
    
    Position position = null;

    private Application application;
    
    private BatailleNavale bataille;
    
    Position courantPos;

    public Game(Application app, BatailleNavale bataille) {
        super(new BorderLayout());
        application = app;
        this.bataille = bataille;
        this.bataille.addObserver(this);
        this.buildPanel();
    }

    private void buildPanel() { 	
    	JPanel plateauxPanel = new JPanel(new GridLayout(2, 1));
    	
    	Font font = new Font(Font.DIALOG, Font.BOLD, 8);
    	
    	// Panel pour le joueur Ordinateur
    	JPanel computerPanel = new JPanel(new GridLayout(BUTTONSNUMBER + 1, BUTTONSNUMBER + 1));
    	for (int j = 0; j < BUTTONSNUMBER + 1; j++) {
    		for (int i = 0; i < BUTTONSNUMBER + 1; i++) {
    			if (j == 0) {
    				if (i != 0) { // boutons avec les lettres en haut
	    				JButton b = new JButton("" + (char)(i+64));
	    				b.setEnabled(false);
	        			computerPanel.add(b);
    				} else { //bouton vide en haut a gauche
    					JButton b = new JButton();
    					b.setEnabled(false);
    					computerPanel.add(b);
    				}
    			} else {
    				if (i == 0) {
    					if (j != 0) { // boutons avec les chiffres a gauche
	    					JButton b = new JButton("" + (j));
	    					b.setEnabled(false);
	    					b.setFont(font);
	    	    			computerPanel.add(b);
    					}
    				} else { // boutons pour les bateaux
		    			JButton b = new JButton();
		    			b.addActionListener(new CustomListener(i-1, j-1));
		    			buttonPlateauOrdinateur[i-1][j-1] = b;
		    			computerPanel.add(b);
    				}
    			}
    		}
    	}
    	
    	// Panel pour le joueur humain
    	JPanel playerPanel = new JPanel(new GridLayout(BUTTONSNUMBER + 1, BUTTONSNUMBER + 1));
    	for (int j = 0; j < BUTTONSNUMBER + 1; j++) {
    		for (int i = 0; i < BUTTONSNUMBER + 1; i++) {
    			if (j == 0) {
    				if (i != 0) { // boutons avec les lettres en haut
	    				JButton b = new JButton("" + (char)(i+64));
	    				b.setEnabled(false);
	        			playerPanel.add(b);
    				} else { //bouton vide en haut a gauche
    					JButton b = new JButton();
    					b.setEnabled(false);
    					playerPanel.add(b);
    				}
    			} else {
    				if (i == 0) {
    					if (j != 0) { // boutons avec les chiffres a gauche
	    					JButton b = new JButton("" + (j));
	    					b.setFont(font);
	    					b.setEnabled(false);
	    	    			playerPanel.add(b);
    					}
    				} else { // boutons pour les bateaux
		    			JButton b = new JButton();
		    			buttonPlateauHumain[i-1][j-1] = b;
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
					String currentDirectory = System.getProperty("user.dir");

					// get directory containing saves
					File savesDirectory = new File(currentDirectory + "/sauvegardes");

					if (!savesDirectory.exists()) {
						savesDirectory.mkdir();
					}

					String name = (String) JOptionPane.showInputDialog(null, "Quel nom souhaitez-vous donner à la sauvegarde ?");
					
					bataille.ajouterNomPartieSauvegardee(name);

                    // save the state of the game in a file using DAO
                    bataille.sauvegarderPartie(savesDirectory + "/" + name + ".csv");
                }

                // go back to main menu
                application.switchToPanel("menu");
            }
        });

        buttons.add(exit);

        shoot = new JButton("Feu!");
        shoot.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {  	
				buttonPlateauOrdinateur[courantPos.getX()][courantPos.getY()].setBackground(Color.CYAN);
				bataille.tirer();
            }
		});
		
		shoot.setEnabled(false);

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
			if (courantPos != null
				&& buttonPlateauOrdinateur[courantPos.getX()][courantPos.getY()].getBackground().equals(Color.LIGHT_GRAY)) {

				buttonPlateauOrdinateur[courantPos.getX()][courantPos.getY()].setBackground(Color.CYAN);			
			}
			courantPos=new Position(xCoord,yCoord);

			if(bataille.estValide(courantPos)){
				buttonPlateauOrdinateur[xCoord][yCoord].setBackground(Color.LIGHT_GRAY);
				shoot.setEnabled(true);
			} else {
				shoot.setEnabled(false);
			}

        }
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		Plateau plateauHumain = bataille.getHumain().getPlateau();
		ArrayList<Position> listeCaseRateeH = bataille.getHumain().getListeCaseRate();
		for (int j = 0; j < plateauHumain.TAILLELIGNE; j++) {
			for (int i = 0; i < plateauHumain.TAILLELIGNE; i++) {
				switch (plateauHumain.getValeur(i, j)) {
					case -1:
						buttonPlateauHumain[i][j].setBackground(Color.RED);
						break;
					case 0:
						if (listeCaseRateeH.contains(new Position(i, j)))
							buttonPlateauHumain[i][j].setBackground(Color.BLUE);
						else
							buttonPlateauHumain[i][j].setBackground(Color.CYAN);
						break;
					case 1:
						buttonPlateauHumain[i][j].setBackground(Color.BLACK);
				}
			}
		}
		
		Plateau plateauOrdinateur = bataille.getOrdinateur().getPlateau();
		ArrayList<Position> listeCaseTouchee = bataille.getOrdinateur().getListeCaseTouche();
		ArrayList<Position> listeCaseRatee = bataille.getOrdinateur().getListeCaseRate();
		for (int j = 0; j < plateauHumain.TAILLELIGNE; j++) {
			for (int i = 0; i < plateauHumain.TAILLELIGNE; i++) {
				if (listeCaseTouchee.contains(new Position(i, j)))
					buttonPlateauOrdinateur[i][j].setBackground(Color.RED);
				else
					if (listeCaseRatee.contains(new Position(i, j)))
						buttonPlateauOrdinateur[i][j].setBackground(Color.BLUE);
					else
						buttonPlateauOrdinateur[i][j].setBackground(Color.CYAN);
			}
		}

		if (bataille.partieTerminee()) {
			String joueurGagnant = bataille.getJoueurCourant() == 0 ? "humain" : "ordinateur";
			JOptionPane.showMessageDialog(null, "Le joueur " + joueurGagnant + " a gagné !", "Information", JOptionPane.INFORMATION_MESSAGE);

			application.switchToPanel("menu");
		}

	}
}