package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce S du jeu Tetris.
 * Cette pièce est composée de 4 éléments formant un "S".
 * 
 * <p>Structure de la pièce :
 * <pre>
 *    [ ][ ]
 *    [ ][ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class STetromino extends Tetromino {
    /**
     * Constructeur de la pièce S.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public STetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce S.
     * Crée un tableau de 4 éléments formant un "S" :
     * - Un élément à la position de référence
     * - Un élément à gauche
     * - Un élément au-dessus
     * - Un élément à droite et au-dessus
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
            // Élément à la position de référence
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur), 
            // Élément à gauche
            new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee()), couleur),
            // Élément au-dessus
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1), couleur),
            // Élément à droite et au-dessus
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() - 1), couleur)
        };
    }
}
