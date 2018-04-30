package test;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import dao.AbstractDAOFactory;
import epoque.EpoqueXVI;
import modele.BatailleNavale;
import modele.strategie.TirAleatoire;

import java.awt.CardLayout;
import java.awt.Dimension;

import view.*;

public class Application extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Application() {
        super("Bataille Navale");
        //BatailleNavale bataille = new BatailleNavale(AbstractDAOFactory.getAbstractDAOFactory(), new EpoqueXVI(), new TirAleatoire());
        BatailleNavale bataille = new BatailleNavale(AbstractDAOFactory.getAbstractDAOFactory());
        this.buildFrame(bataille);
    }

    private void buildFrame(BatailleNavale bataille) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 500));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        this.mainPanel.add(new MainMenu(this), "menu");
        this.mainPanel.add(new NewGame(this,bataille), "nouvellePartie");
        this.mainPanel.add(new Game(this, bataille), "jeu");
        this.mainPanel.add(new LoadGame(this, bataille), "reprendrePartie");            

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