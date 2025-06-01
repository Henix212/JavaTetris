package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce Z du jeu Tetris.
 * Cette pièce est composée de 4 éléments formant un "Z".
 * 
 * <p>Structure de la pièce :
 * <pre>
 *    [ ][ ]
 *   [ ][ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class ZTetromino extends Tetromino {
    /**
     * Constructeur de la pièce Z.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public ZTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce Z.
     * Crée un tableau de 4 éléments formant un "Z" :
     * - Un élément à la position de référence
     * - Un élément à droite
     * - Un élément au-dessus
     * - Un élément à gauche et au-dessus
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
            // Élément à la position de référence
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur), 
            // Élément à droite
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur),
            // Élément au-dessus
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1), couleur),
            // Élément à gauche et au-dessus
            new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee() - 1), couleur)
        };
    }
}
