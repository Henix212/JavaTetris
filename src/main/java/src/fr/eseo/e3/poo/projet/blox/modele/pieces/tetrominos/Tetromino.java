package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.*;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.util.Arrays;
import java.util.List;

public abstract class Tetromino implements Piece {
    protected Element[] elements;
    private Puits puits;

    protected Tetromino(Coordonnees coordonnees, Couleur couleur) {
        setElements(coordonnees, couleur);
    }

    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);

    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1 || (deltaX != 0 && deltaY != 0)) {
            throw new IllegalArgumentException("Déplacement invalide pour un Tetromino");
        }

        for (Element element : elements) {
            int nouvelleAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
            int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;

            // Vérification des collisions avec le fond du puits
            if (nouvelleOrdonnee >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
            }

            // Vérification de dépassement latéral (sortie du puits)
            if (nouvelleAbscisse < 0 || nouvelleAbscisse >= puits.getLargeur()) {
                throw new BloxException("Sortie latérale du puits", BloxException.BLOX_SORTIE_PUITS);
            }

            // Vérification de collision avec des éléments du tas
            for (Element e : puits.getTas().getElements()) {
                if (e.getCoordonnees().equals(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee))) {
                    throw new BloxException("Collision avec un élément du tas", BloxException.BLOX_COLLISION);
                }
            }
        }

        // Si tout est OK, déplacer les éléments
        for (Element element : elements) {
            element.deplacerDe(deltaX, deltaY);
        }
    }

    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        if (this instanceof OTetromino) {
            return; // Pas de rotation pour O-Tetromino
        }

        int refX = elements[0].getCoordonnees().getAbscisse();
        int refY = elements[0].getCoordonnees().getOrdonnee();

        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        for (int i = 0; i < elements.length; i++) {
            int x = elements[i].getCoordonnees().getAbscisse() - refX;
            int y = elements[i].getCoordonnees().getOrdonnee() - refY;

            int newX, newY;
            if (sensHoraire) {
                newX = y;
                newY = -x;
            } else {
                newX = -y;
                newY = x;
            }

            nouvellesCoordonnees[i] = new Coordonnees(refX + newX, refY + newY);

            // Vérification que la rotation ne sort pas du puits
            if (nouvellesCoordonnees[i].getAbscisse() < 0 || nouvellesCoordonnees[i].getAbscisse() >= puits.getLargeur()) {
                throw new BloxException("Sortie du puits après rotation", BloxException.BLOX_SORTIE_PUITS);
            }

            if (nouvellesCoordonnees[i].getOrdonnee() >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond après rotation", BloxException.BLOX_COLLISION);
            }

            // Vérification collision avec des éléments du tas
            for (Element e : puits.getTas().getElements()) {
                if (e.getCoordonnees().equals(nouvellesCoordonnees[i])) {
                    throw new BloxException("Collision après rotation", BloxException.BLOX_COLLISION);
                }
            }

        }

        // Si tout est OK, appliquer les nouvelles coordonnées
        for (int i = 0; i < elements.length; i++) {
            elements[i].setCoordonnes(nouvellesCoordonnees[i]);
        }
    }

    @Override
    public List<Element> getElements() {
        return List.of(this.elements);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " :\n");

        Arrays.stream(elements).forEach(element ->
                result.append("\t(")
                        .append(element.getCoordonnees().getAbscisse())
                        .append(", ")
                        .append(element.getCoordonnees().getOrdonnee())
                        .append(") - ")
                        .append(element.getCouleur().name())
                        .append("\n")
        );
        return result.toString();
    }

    @Override
    public Puits getPuits() {
        return puits;
    }

    @Override
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    @Override
    public void setPosition(int abscisse, int ordonnee) {
        int dx = abscisse - elements[0].getCoordonnees().getAbscisse();
        int dy = ordonnee - elements[0].getCoordonnees().getOrdonnee();

        for (Element element : elements) {
            Coordonnees nouvelleCoord = new Coordonnees(
                    element.getCoordonnees().getAbscisse() + dx,
                    element.getCoordonnees().getOrdonnee() + dy
            );
            element.setCoordonnes(nouvelleCoord);
        }
    }

    @Override
    public Piece clone() {
        try {
            Class<? extends Tetromino> clazz = this.getClass();

            Coordonnees pivot = new Coordonnees(
                elements[0].getCoordonnees().getAbscisse(),
                elements[0].getCoordonnees().getOrdonnee()
            );
            Couleur couleur = elements[0].getCouleur();

            Tetromino clone = clazz.getConstructor(Coordonnees.class, Couleur.class)
                    .newInstance(pivot, couleur);

            for (int i = 0; i < elements.length; i++) {
                Coordonnees c = elements[i].getCoordonnees();
                clone.elements[i].setCoordonnes(new Coordonnees(c.getAbscisse(), c.getOrdonnee()));
            }

            clone.setPuits(this.getPuits());
            return clone;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du clonage du Tetromino", e);
        }
    }
}
