package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import java.awt.*;

public class VueGhost extends VuePiece {

    public VueGhost(Piece piece, int taille) {
        super(cloneAndDrop(piece), taille);
    }

    // Clone la pièce et la fait descendre tout en bas
    private static Piece cloneAndDrop(Piece piece) {
        Piece ghost = piece.clone();
        try {
            while (true) {
                ghost.deplacerDe(0, 1);
            }
        } catch (BloxException e) {

        }
        return ghost;
    }

    @Override
    protected Color getCouleurAffichage(Element elt) {
        Color base = elt.getCouleur().getCouleurPourAffichage();
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), 80); // alpha 80
    }
}