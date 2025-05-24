package src.fr.eseo.e3.poo.projet.blox;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePiece;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import javax.swing.*;
import java.awt.*;

public class FallingBlox {
    public static void main(String[] args) {
        // Initialisation du puits
        Puits puits = new Puits();
        Tas tas = new Tas(puits);

        puits.setTas(tas); // Associer le tas au puits
        
        UsineDePiece.setMode(0); // Choix du mode de génération des pièces
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        
        // Définir la pièce suivante dans le puits
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);

        // Création de la vue associée au puits
        VuePuits vuePuits = new VuePuits(puits, 30); // Taille des cases définie à 30 pixels
        VuePiece vuePiece = new VuePiece(puits.getPieceActuelle(), 30);
        vuePuits.setVuePiece(vuePiece);
        vuePuits.setFocusable(true);
        vuePuits.requestFocusInWindow();
        // Configuration de la fenêtre
        JFrame frame = new JFrame("Falling Blox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 600)); // Dimensions ajustées pour correspondre au puits
        frame.setContentPane(vuePuits); // Ajout de la vue du puits à la fenêtre
        frame.setVisible(true); // Rendre la fenêtre visible
    }
}
