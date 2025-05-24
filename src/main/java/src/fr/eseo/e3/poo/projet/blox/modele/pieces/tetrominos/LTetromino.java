package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
public class LTetromino extends Tetromino{
    
    public LTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.elements = new Element[]{
                new Element(new Coordonnees(coordonnees.getAbscisse() - 2, coordonnees.getOrdonnee()), couleur),
                new Element(new Coordonnees(coordonnees.getAbscisse() - 2, coordonnees.getOrdonnee() - 1), couleur),
                new Element(new Coordonnees(coordonnees.getAbscisse() - 1, coordonnees.getOrdonnee()), couleur),
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee()), couleur)
                
        };
    }
}
