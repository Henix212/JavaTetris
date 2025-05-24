package src.fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.CardLayout;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class Routeur {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final HashMap<String, JComponent> vues = new HashMap<>();

    public Routeur(JFrame frame) {
        this.frame = frame;
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.frame.setContentPane(mainPanel);
    }

    public void ajouterVue(String nom, JComponent vue) {
        vues.put(nom, vue);
        mainPanel.add(vue, nom);
    }

    public void afficherVue(String nom) {
        cardLayout.show(mainPanel, nom);
        frame.revalidate();
        frame.repaint();
    }

    public JComponent getVue(String nom) {
        return vues.get(nom);
    }
}
