package test;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;

import view.*;

/**
 * @author guillaume bergerot
 */
public class Application extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Application() {
        super("Bataille Navale");
        this.buildFrame();
    }

    private void buildFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 500));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.mainPanel.add(new MainMenu(this), "menu");
        this.mainPanel.add(new NewGame(this), "nouvellePartie");
        this.mainPanel.add(new Game(this), "jeu");
        this.mainPanel.add(new LoadGame(this), "reprendrePartie");            

        this.add(mainPanel);

        this.pack();
        this.setVisible(true);
    }

    public void switchToPanel(String panelName) {
        switch (panelName) {
            case "menu" :
                cardLayout.show(mainPanel, "menu");
                break;
            case "nouvellePartie" :
                cardLayout.show(mainPanel, "nouvellePartie");
                break;
            case "reprendrePartie" :
                cardLayout.show(mainPanel, "reprendrePartie");
                break;
            case "jeu" :
                cardLayout.show(mainPanel, "jeu");

        }
    }
    
    public static void main(String[] args) {
        new Application();
    }
}