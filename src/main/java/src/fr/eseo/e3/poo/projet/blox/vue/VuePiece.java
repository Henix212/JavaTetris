package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

import java.awt.*;

/**
 * Vue représentant une pièce de Tetris.
 * Cette classe gère l'affichage graphique d'une pièce et ses éléments,
 * avec des effets visuels comme la teinte et l'effet 3D.
 *
 * @author Hugo
 */
public class VuePiece {
    /** Multiplicateur pour l'effet de teinte des couleurs */
    static double MULTIPLIER_TEINTE = 0.7;
    /** La pièce à afficher */
    private Piece piece;

    /**
     * Constructeur de la vue d'une pièce.
     *
     * @param piece La pièce à afficher
     * @param taille La taille des cases en pixels (non utilisé dans cette implémentation)
     */
    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
    }

    /**
     * Applique un effet de teinte à une couleur.
     * La teinte est calculée en mélangeant la couleur avec du blanc.
     *
     * @param couleur La couleur à teinter
     * @return La couleur teintée
     */
    public Color teinte(Color couleur) {
        int rouge = couleur.getRed();
        int vert = couleur.getGreen();
        int blue = couleur.getBlue();
        
        // Mélange avec du blanc selon le multiplicateur
        rouge = (int)(rouge + (255 - rouge) * MULTIPLIER_TEINTE);
        vert = (int)(vert + (255 - vert) * MULTIPLIER_TEINTE);
        blue = (int)(blue + (255 - blue) * MULTIPLIER_TEINTE);

        return new Color(rouge, vert, blue);
    }

    /**
     * Affiche la pièce dans le contexte graphique spécifié.
     * Dessine chaque élément de la pièce avec sa couleur et un effet 3D.
     *
     * @param g2D Le contexte graphique pour le dessin
     * @param largeurCase La largeur d'une case en pixels
     * @param hauteurCase La hauteur d'une case en pixels
     */
    public void afficherPiece(Graphics2D g2D, float largeurCase, float hauteurCase) {
        for (Element elt : this.piece.getElements()) {
            // Calcul des coordonnées de l'élément
            int x = Math.round(elt.getCoordonnees().getAbscisse() * largeurCase);
            int y = Math.round(elt.getCoordonnees().getOrdonnee() * hauteurCase);
            int w = Math.round(largeurCase);
            int h = Math.round(hauteurCase);

            // Dessin de l'élément avec effet 3D
            g2D.setColor(getCouleurAffichage(elt));
            g2D.fill3DRect(x, y, w, h, false);

            // Contour de l'élément
            g2D.setColor(Color.BLACK);
            g2D.drawRect(x, y, w, h);
        }
    }

    /**
     * Retourne la couleur d'affichage d'un élément.
     * Cette méthode peut être surchargée pour modifier l'apparence des éléments.
     *
     * @param elt L'élément dont on veut la couleur
     * @return La couleur de l'élément
     */
    protected Color getCouleurAffichage(Element elt) {
        return elt.getCouleur().getCouleurPourAffichage();
    }
}
