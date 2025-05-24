package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import javax.swing.*;
import java.awt.*;

public class VueJeux {
    public static void main(String[] args) {
        afficherPageJeu();
    }

    public static void afficherPageJeu() {
        // Initialisation du puits
        Puits puits = new Puits();
        Tas tas = new Tas(puits);
        puits.setTas(tas);

        UsineDePiece.setMode(0);
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);

        VuePuits vuePuits = new VuePuits(puits, 30);

        // Panel pour centrer la grille
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Transparent pour voir le fond si besoin
        centerPanel.add(vuePuits);

        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(centerPanel);
        frame.pack(); // <-- pour que la grille ait la bonne taille
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // plein écran
        frame.setVisible(true);

        vuePuits.setFocusable(true);
        vuePuits.requestFocusInWindow();
    }
}
