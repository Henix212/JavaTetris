package src.fr.eseo.e3.poo.projet.blox.modele.pieces;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.util.List;

/**
 * Interface définissant le comportement d'une pièce dans le jeu Tetris.
 * Une pièce est composée d'un ensemble d'éléments qui peuvent être déplacés
 * et tournés dans le puits de jeu.
 *
 * @author Hugo
 */
public interface Piece {

    /**
     * Retourne la liste des éléments composant la pièce.
     *
     * @return Une liste non modifiable des éléments de la pièce
     */
    List<Element> getElements();

    /**
     * Déplace la pièce à une nouvelle position absolue.
     * Le déplacement est calculé par rapport au point de référence de la pièce.
     *
     * @param abscisse La nouvelle abscisse du point de référence
     * @param ordonnee La nouvelle ordonnée du point de référence
     */
    void setPosition(int abscisse, int ordonnee);

    /**
     * Retourne le puits dans lequel la pièce évolue.
     *
     * @return Le puits associé à la pièce
     */
    Puits getPuits();

    /**
     * Définit le puits dans lequel la pièce évolue.
     *
     * @param puits Le nouveau puits associé à la pièce
     */
    void setPuits(Puits puits);

    /**
     * Déplace la pièce dans le puits selon les déplacements spécifiés.
     * Vérifie les collisions avec les bords du puits et les autres pièces.
     *
     * @param deltaX Déplacement horizontal (-1: gauche, 0: aucun, 1: droite)
     * @param deltaY Déplacement vertical (-1: haut, 0: aucun, 1: bas)
     * @throws BloxException Si le déplacement est impossible (collision ou sortie du puits)
     */
    void deplacerDe(int deltaX, int deltaY) throws BloxException;

    /**
     * Fait tourner la pièce dans le sens spécifié.
     * La rotation est effectuée autour du point de référence de la pièce.
     * Vérifie les collisions après rotation.
     *
     * @param sensHoraire true pour une rotation horaire, false pour anti-horaire
     * @throws BloxException Si la rotation est impossible (collision ou sortie du puits)
     */
    void tourner(boolean sensHoraire) throws BloxException;
        
    /**
     * Crée une copie profonde de la pièce.
     * La copie conserve la même forme, position et couleur que l'original.
     *
     * @return Une nouvelle instance de la pièce
     */
    Piece clone();
}
