package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

import java.awt.*;

public class VuePiece {
    static double MULTIPLIER_TEINTE = 0.7;
    private Piece piece;

    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
    }

    public Color teinte(Color couleur) {
        int rouge = couleur.getRed();
        int vert = couleur.getGreen();
        int blue = couleur.getBlue();
        
        rouge = (int)(rouge + (255 - rouge) * MULTIPLIER_TEINTE);
        vert = (int)(vert + (255 - vert) * MULTIPLIER_TEINTE);
        blue = (int)(blue + (255 - blue) * MULTIPLIER_TEINTE);

        return new Color(rouge, vert, blue);
    }

    public void afficherPiece(Graphics2D g2D, float largeurCase, float hauteurCase) {
        for (Element elt : this.piece.getElements()) {
            int x = Math.round(elt.getCoordonnees().getAbscisse() * largeurCase);
            int y = Math.round(elt.getCoordonnees().getOrdonnee() * hauteurCase);
            int w = Math.round(largeurCase);
            int h = Math.round(hauteurCase);

            g2D.setColor(getCouleurAffichage(elt));
            g2D.fill3DRect(x, y, w, h, false);

            g2D.setColor(Color.BLACK);
            g2D.drawRect(x, y, w, h);
        }
    }

    protected Color getCouleurAffichage(Element elt) {
        return elt.getCouleur().getCouleurPourAffichage();
    }
}
