package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

public class JTetromino extends Tetromino {

    public JTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 2), couleur),     // coin (bas gauche)
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur),
            new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur),
            new Element(new Coordonnees(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() + 2), couleur)
        };
    }
}
