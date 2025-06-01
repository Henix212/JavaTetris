package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce I (barre) du jeu Tetris.
 * Cette pièce est composée de 4 éléments alignés verticalement.
 * 
 * <p>Structure de la pièce :
 * <pre>
 *     [ ]
 *     [ ]
 *     [ ]
 *     [ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class ITetromino extends Tetromino {

    /**
     * Constructeur de la pièce I.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public ITetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce I.
     * Crée un tableau de 4 éléments alignés verticalement :
     * - Un élément à la position de référence
     * - Un élément au-dessus
     * - Deux éléments en dessous
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
                // Élément central (point de référence)
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur),
                // Élément au-dessus
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur),
                // Premier élément en dessous
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1), couleur),
                // Deuxième élément en dessous
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 2), couleur)
        };
    }
}
