package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

/**
 * Représente la pièce O (carré) du jeu Tetris.
 * Cette pièce est composée de 4 éléments formant un carré 2x2.
 * 
 * <p>Structure de la pièce :
 * <pre>
 *    [ ][ ]
 *[ ][ ]
 * </pre>
 * </p>
 *
 * @author Hugo
 */
public class OTetromino extends Tetromino {

    /**
     * Constructeur de la pièce O.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     * @throws NullPointerException Si coordonnees ou couleur est null
     */
    public OTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /**
     * Définit la position des éléments de la pièce O.
     * Crée un tableau de 4 éléments formant un carré 2x2 :
     * - Un élément à la position de référence
     * - Un élément à droite
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
                // Élément à droite
                new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee()), couleur),
                // Élément au-dessus
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1), couleur),
                // Élément à droite et au-dessus
                new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() - 1), couleur)
        };
    }

    /**
     * Surcharge de la méthode tourner.
     * Cette pièce ne peut pas tourner car elle est de forme carrée.
     * La méthode est vide car une rotation ne changerait pas la forme de la pièce.
     */
    public void tourner(){
    }
}
