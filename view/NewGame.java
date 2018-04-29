package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import javax.swing.event.ListSelectionEvent;


import test.*;

/**
 * @author guillaume bergerot
 */
public class NewGame extends JPanel {
    private Application application;

    private JList ageList, strategyList;
    private JButton playButton;

    public NewGame(Application app) {
        application = app;
        this.buildPanel();
    }

    private void buildPanel() {
        this.setLayout(new GridLayout(2, 1));

        JPanel lists = new JPanel(new GridLayout(1, 2));

        // temporary selections
        String[] ages = {"epoque1", "epoque2", "epoque3"};

        // load dynamically all the strategies allowed
        // TODO 

        ageList = new JList<String>(ages);
        lists.add(ageList);

        // temporary selections
        String[] strategies = {"strategy1", "strategy2", "strategy3"};

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