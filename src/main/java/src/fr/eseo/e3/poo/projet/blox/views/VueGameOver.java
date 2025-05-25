package src.fr.eseo.e3.poo.projet.blox.views;

import javax.swing.JPanel;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

public class VueGameOver extends JPanel{
    public VueGameOver(Routeur routeur) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
    }
}
