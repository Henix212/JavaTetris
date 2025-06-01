package src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import src.fr.eseo.e3.poo.projet.blox.modele.*;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.util.Arrays;
import java.util.List;

/**
 * Classe abstraite représentant une pièce de Tetris (Tetromino).
 * Un Tetromino est composé de 4 éléments qui peuvent être déplacés et tournés.
 * Cette classe implémente l'interface Piece et fournit les fonctionnalités de base
 * pour toutes les pièces du jeu.
 *
 * @author Hugo
 */
public abstract class Tetromino implements Piece {
    /** Tableau des éléments composant la pièce */
    protected Element[] elements;
    /** Référence au puits dans lequel la pièce évolue */
    private Puits puits;

    /**
     * Constructeur d'un Tetromino.
     * Initialise la pièce avec les coordonnées et la couleur spécifiées.
     *
     * @param coordonnees Les coordonnées du point de référence de la pièce
     * @param couleur La couleur de la pièce
     */
    protected Tetromino(Coordonnees coordonnees, Couleur couleur) {
        setElements(coordonnees, couleur);
    }

    /**
     * Méthode abstraite définissant la position des éléments de la pièce.
     * Chaque type de Tetromino doit implémenter cette méthode pour définir
     * sa forme spécifique.
     *
     * @param coordonnees Les coordonnées du point de référence
     * @param couleur La couleur des éléments
     */
    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);

    /**
     * Déplace la pièce dans le puits selon les déplacements spécifiés.
     * Vérifie les collisions avec les bords du puits et les autres pièces.
     *
     * @param deltaX Déplacement horizontal (-1: gauche, 0: aucun, 1: droite)
     * @param deltaY Déplacement vertical (-1: haut, 0: aucun, 1: bas)
     * @throws BloxException Si le déplacement est impossible (collision ou sortie du puits)
     * @throws IllegalArgumentException Si le déplacement est invalide (plus d'une case ou diagonale)
     */
    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        // Vérification de la validité du déplacement
        if (Math.abs(deltaX) > 1 || Math.abs(deltaY) > 1 || (deltaX != 0 && deltaY != 0)) {
            throw new IllegalArgumentException("Déplacement invalide pour un Tetromino");
        }

        // Vérification des collisions pour chaque élément
        for (Element element : elements) {
            int nouvelleAbscisse = element.getCoordonnees().getAbscisse() + deltaX;
            int nouvelleOrdonnee = element.getCoordonnees().getOrdonnee() + deltaY;

            // Vérification collision avec le fond du puits
            if (nouvelleOrdonnee >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
            }

            // Vérification sortie latérale du puits
            if (nouvelleAbscisse < 0 || nouvelleAbscisse >= puits.getLargeur()) {
                throw new BloxException("Sortie latérale du puits", BloxException.BLOX_SORTIE_PUITS);
            }

            // Vérification collision avec les éléments du tas
            for (Element e : puits.getTas().getElements()) {
                if (e.getCoordonnees().equals(new Coordonnees(nouvelleAbscisse, nouvelleOrdonnee))) {
                    throw new BloxException("Collision avec un élément du tas", BloxException.BLOX_COLLISION);
                }
            }
        }

        // Application du déplacement si aucune collision n'est détectée
        for (Element element : elements) {
            element.deplacerDe(deltaX, deltaY);
        }
    }

    /**
     * Fait tourner la pièce dans le sens spécifié.
     * La rotation est effectuée autour du premier élément de la pièce.
     * Vérifie les collisions après rotation.
     *
     * @param sensHoraire true pour une rotation horaire, false pour anti-horaire
     * @throws BloxException Si la rotation est impossible (collision ou sortie du puits)
     */
    @Override
    public void tourner(boolean sensHoraire) throws BloxException {
        // La pièce O ne tourne pas
        if (this instanceof OTetromino) {
            return; 
        }

        // Calcul des nouvelles coordonnées après rotation
        int refX = elements[0].getCoordonnees().getAbscisse();
        int refY = elements[0].getCoordonnees().getOrdonnee();

        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        for (int i = 0; i < elements.length; i++) {
            // Calcul des coordonnées relatives au point de référence
            int x = elements[i].getCoordonnees().getAbscisse() - refX;
            int y = elements[i].getCoordonnees().getOrdonnee() - refY;

            // Application de la matrice de rotation
            int newX, newY;
            if (sensHoraire) {
                newX = y;
                newY = -x;
            } else {
                newX = -y;
                newY = x;
            }

            nouvellesCoordonnees[i] = new Coordonnees(refX + newX, refY + newY);

            // Vérification des collisions après rotation
            if (nouvellesCoordonnees[i].getAbscisse() < 0 || nouvellesCoordonnees[i].getAbscisse() >= puits.getLargeur()) {
                throw new BloxException("Sortie du puits après rotation", BloxException.BLOX_SORTIE_PUITS);
            }

            if (nouvellesCoordonnees[i].getOrdonnee() >= puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond après rotation", BloxException.BLOX_COLLISION);
            }

            // Vérification collision avec les éléments du tas
            for (Element e : puits.getTas().getElements()) {
                if (e.getCoordonnees().equals(nouvellesCoordonnees[i])) {
                    throw new BloxException("Collision après rotation", BloxException.BLOX_COLLISION);
                }
            }
        }

        // Application de la rotation si aucune collision n'est détectée
        for (int i = 0; i < elements.length; i++) {
            elements[i].setCoordonnes(nouvellesCoordonnees[i]);
        }
    }

    /**
     * Retourne la liste des éléments composant la pièce.
     *
     * @return Une liste non modifiable des éléments de la pièce
     */
    @Override
    public List<Element> getElements() {
        return List.of(this.elements);
    }

    /**
     * Retourne une représentation textuelle de la pièce.
     * Affiche le type de pièce et les coordonnées de chaque élément.
     *
     * @return Une chaîne de caractères représentant la pièce
     */
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

    /**
     * Retourne le puits dans lequel la pièce évolue.
     *
     * @return Le puits associé à la pièce
     */
    @Override
    public Puits getPuits() {
        return puits;
    }

    /**
     * Définit le puits dans lequel la pièce évolue.
     *
     * @param puits Le nouveau puits associé à la pièce
     */
    @Override
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    /**
     * Déplace la pièce à une nouvelle position absolue.
     * Le déplacement est calculé par rapport au premier élément de la pièce.
     *
     * @param abscisse La nouvelle abscisse du point de référence
     * @param ordonnee La nouvelle ordonnée du point de référence
     */
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

    /**
     * Crée une copie profonde de la pièce.
     * La copie conserve la même forme, position et couleur que l'original.
     *
     * @return Une nouvelle instance de la pièce
     * @throws RuntimeException Si une erreur survient lors du clonage
     */
    @Override
    public Piece clone() {
        try {
            Class<? extends Tetromino> clazz = this.getClass();

            // Création d'une nouvelle instance avec les mêmes paramètres
            Coordonnees pivot = new Coordonnees(
                elements[0].getCoordonnees().getAbscisse(),
                elements[0].getCoordonnees().getOrdonnee()
            );
            Couleur couleur = elements[0].getCouleur();

            Tetromino clone = clazz.getConstructor(Coordonnees.class, Couleur.class)
                    .newInstance(pivot, couleur);

            // Copie des coordonnées de chaque élément
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
