package src.fr.eseo.e3.poo.projet.blox.modele.pieces;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.util.List;

public interface Piece {

    List<Element> getElements();

    void setPosition(int abscisse, int ordonnee);

    Puits getPuits();

    void setPuits(Puits puits);

    void deplacerDe(int deltaX, int deltaY) throws BloxException;

    void tourner(boolean sensHoraire) throws BloxException;
        
    Piece clone();
}
