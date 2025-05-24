package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

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
        for(int i = 0; i < this.piece.getElements().size(); i++) {
            if (i == 0){
                g2D.setColor(teinte(piece.getElements().get(i).getCouleur().getCouleurPourAffichage()));
            } else {
                g2D.setColor(piece.getElements().get(i).getCouleur().getCouleurPourAffichage());
            }
            Coordonnees coordonnees = this.piece.getElements().get(i).getCoordonnees();
            g2D.fill3DRect((int) (coordonnees.getAbscisse() * largeurCase), (int) (coordonnees.getOrdonnee() * hauteurCase), (int) largeurCase, (int) hauteurCase,false);
        }
    }
}
