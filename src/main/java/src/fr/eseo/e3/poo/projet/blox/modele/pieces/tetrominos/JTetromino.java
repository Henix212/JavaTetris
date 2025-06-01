package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce J du jeu Tetris.
 * Cette pièce est composée de 4 éléments formant un "J" inversé.
 * 
 * <p>Structure de la pièce :
 * <pre>
 *       [ ]
 *       [ ]
 *    [ ][ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class JTetromino extends Tetromino {

    /**
     * Constructeur de la pièce J.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public JTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce J.
     * Crée un tableau de 4 éléments formant un "J" inversé :
     * - Un élément à la position de référence
     * - Un élément en dessous
     * - Un élément encore plus bas
     * - Un élément à droite du bas
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
                // Élément du bas à gauche
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 2), couleur),
                // Élément du milieu
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur),
                // Élément du haut (point de référence)
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur),
                // Élément du bas à droite
                new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 2), couleur)
        };
    }
}
