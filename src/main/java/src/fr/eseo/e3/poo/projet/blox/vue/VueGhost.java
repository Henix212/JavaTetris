package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import java.awt.*;

/**
 * Vue représentant le fantôme d'une pièce dans Tetris.
 * Le fantôme indique où la pièce actuelle atterrira si elle est lâchée.
 * Il est affiché avec une transparence pour ne pas perturber la visibilité.
 *
 * @author Hugo
 */
public class VueGhost extends VuePiece {

    /**
     * Constructeur du fantôme d'une pièce.
     * Crée une copie de la pièce et la fait descendre jusqu'au point d'atterrissage.
     *
     * @param piece La pièce dont on veut afficher le fantôme
     * @param taille La taille des cases en pixels
     */
    public VueGhost(Piece piece, int taille) {
        super(cloneAndDrop(piece), taille);
    }

    /**
     * Clone une pièce et la fait descendre jusqu'à ce qu'elle ne puisse plus bouger.
     * Cette méthode simule la chute de la pièce pour déterminer sa position finale.
     *
     * @param piece La pièce à cloner et faire descendre
     * @return La pièce clonée à sa position finale
     */
    private static Piece cloneAndDrop(Piece piece) {
        Piece ghost = piece.clone();
        try {
            while (true) {
                ghost.deplacerDe(0, 1);
            }
        } catch (BloxException e) {
            // La pièce a atteint le bas ou une collision
        }
        return ghost;
    }

    /**
     * Retourne la couleur d'affichage d'un élément du fantôme.
     * La couleur est la même que celle de la pièce originale mais avec une transparence.
     *
     * @param elt L'élément dont on veut la couleur
     * @return La couleur transparente de l'élément
     */
    @Override
    protected Color getCouleurAffichage(Element elt) {
        Color base = elt.getCouleur().getCouleurPourAffichage();
        return new Color(base.getRed(), base.getGreen(), base.getBlue(), 80); // alpha 80
    }
}