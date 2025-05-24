package src.fr.eseo.e3.poo.projet.blox;
import javax.swing.JFrame;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.views.VueJeux;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FallingBlox");
        Routeur routeur = new Routeur(frame);
        routeur.ajouterVue("JEUX", new VueJeux());
        routeur.afficherVue("JEUX");
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Plein écran
    }
}
