package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce T du jeu Tetris.
 * Cette pièce est composée de 4 éléments formant un "T".
 * 
 * <p>Structure de la pièce :
 * <pre>
 *    [ ][ ][ ]
 *   [ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class TTetromino extends Tetromino {

    /**
     * Constructeur de la pièce T.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public TTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce T.
     * Crée un tableau de 4 éléments formant un "T" :
     * - Un élément à gauche
     * - Un élément en bas à gauche
     * - Un élément au centre (position de référence)
     * - Un élément à gauche du centre
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
            // Élément à gauche
            new Element(new Coordonnees(coordonnees.getAbscisse() -1, coordonnees.getOrdonnee()), couleur),
            // Élément en bas à gauche
            new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee() + 1), couleur),
            // Élément au centre (position de référence)
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur),
            // Élément à gauche du centre
            new Element(new Coordonnees(coordonnees.getAbscisse() -2, coordonnees.getOrdonnee()), couleur)
        };
    }
}
