package src.fr.eseo.e3.poo.projet.blox.views;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.*;
import java.awt.*;

public class VueJeux extends JPanel {

    private final Puits puits;
    private final Tas tas;
    private final VuePuits vuePuits;

    public VueJeux() {
        // Initialisation du puits et du tas
        this.puits = new Puits();
        this.tas = new Tas(puits);
        puits.setTas(tas);

        UsineDePiece.setMode(0);
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);

        this.vuePuits = new VuePuits(puits, 30);

        // Centrage de la grille
        setLayout(new GridBagLayout());
        setOpaque(false);
        add(vuePuits);

        vuePuits.setFocusable(true);
        vuePuits.requestFocusInWindow();
    }

    public Puits getPuits() {
        return puits;
    }

    public Tas getTas() {
        return tas;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }
}
