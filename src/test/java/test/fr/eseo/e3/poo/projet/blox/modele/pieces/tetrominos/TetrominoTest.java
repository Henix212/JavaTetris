package test.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Tetromino.
 * Cette classe teste le comportement de base des pièces Tetris (tetrominos).
 * Les tests vérifient :
 * - Les déplacements légaux et illégaux
 * - Les rotations dans les deux sens
 * - La gestion des éléments de la pièce
 * - Le positionnement et le clonage
 * - Les collisions avec le tas
 * - La représentation textuelle
 *
 * @author Hugo
 */
public class TetrominoTest {

    /** La pièce à tester (utilise ITetromino comme exemple) */
    private Piece piece;
    /** Le puits de jeu associé à la pièce */
    private Puits puits;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions 10x20 et une pièce I en position initiale.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        piece.setPuits(puits);
    }

    /**
     * Teste les déplacements légaux d'une pièce.
     * Vérifie que la pièce peut se déplacer d'une case vers le bas ou sur le côté.
     */
    @Test
    public void testDeplacementLegal() {
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
    }

    /**
     * Teste les déplacements illégaux d'une pièce.
     * Vérifie que la pièce ne peut pas se déplacer en diagonale ou de plus d'une case.
     */
    @Test
    public void testDeplacementIllegal() {
        assertThrows(IllegalArgumentException.class, () -> piece.deplacerDe(1, 1));
        assertThrows(IllegalArgumentException.class, () -> piece.deplacerDe(2, 0));
    }

    /**
     * Teste les rotations d'une pièce.
     * Vérifie que la pièce peut tourner dans les deux sens (horaire et anti-horaire).
     */
    @Test
    public void testRotationSensHoraireEtAntiHoraire() {
        assertDoesNotThrow(() -> piece.tourner(true));
        assertDoesNotThrow(() -> piece.tourner(false));
    }

    /**
     * Teste la récupération des éléments d'une pièce.
     * Vérifie que :
     * - La liste des éléments n'est pas nulle
     * - La pièce contient exactement 4 éléments
     */
    @Test
    public void testGetElements() {
        List<Element> elements = piece.getElements();
        assertNotNull(elements);
        assertEquals(4, elements.size());
    }

    /**
     * Teste le positionnement d'une pièce.
     * Vérifie que la pièce est correctement positionnée aux coordonnées spécifiées.
     */
    @Test
    public void testSetPosition() {
        piece.setPosition(2, 3);
        Coordonnees pivot = piece.getElements().get(0).getCoordonnees();
        assertEquals(2, pivot.getAbscisse());
        assertEquals(3, pivot.getOrdonnee());
    }

    /**
     * Teste le clonage d'une pièce.
     * Vérifie que :
     * - Le clone est une nouvelle instance
     * - Le clone a le même nombre d'éléments
     * - Le clone est associé au même puits
     */
    @Test
    public void testClone() {
        Piece clone = piece.clone();
        assertNotSame(piece, clone);
        assertEquals(piece.getElements().size(), clone.getElements().size());
        assertEquals(piece.getPuits(), clone.getPuits());
    }

    /**
     * Teste la représentation textuelle d'une pièce.
     * Vérifie que la chaîne contient le type de la pièce et sa couleur.
     */
    @Test
    public void testToStringFormat() {
        String output = piece.toString();
        assertTrue(output.contains("ITetromino"));
        assertTrue(output.contains("ROUGE"));
    }

    /**
     * Teste la détection de collision avec un élément du tas.
     * Vérifie que :
     * - Une exception est levée lors d'une collision
     * - Le type d'exception est correct
     * - Le message d'erreur est approprié
     */
    @Test
    public void testDeplacementCollisionAvecElementTas() {
        // Ajoute un élément du tas à la position future de déplacement
        Element obstacle = new Element(
            new Coordonnees(
                piece.getElements().get(0).getCoordonnees().getAbscisse(),
                piece.getElements().get(0).getCoordonnees().getOrdonnee() + 1
            ),
            Couleur.BLEU
        );
        puits.getTas().getElements().add(obstacle);

        BloxException exception = assertThrows(BloxException.class, () -> piece.deplacerDe(0, 1));
        assertEquals(BloxException.BLOX_COLLISION, exception.getType());
        assertTrue(exception.getMessage().contains("Collision avec un élément du tas"));
    }

    /**
     * Teste le déplacement sans collision.
     * Vérifie que la pièce peut se déplacer quand il n'y a pas d'obstacle sur son chemin.
     */
    @Test
    public void testDeplacementSansCollisionAvecElementTas() {
        // Ajoute un élément du tas à une autre position
        Element obstacle = new Element(
            new Coordonnees(0, 0), // Position très éloignée de la pièce
            Couleur.BLEU
        );
        puits.getTas().getElements().add(obstacle);

        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
    }

    /**
     * Teste la rotation d'une pièce O (carré).
     * Vérifie que la pièce O peut tourner dans les deux sens sans effet
     * (car elle est symétrique).
     */
    @Test
    public void testTournerAvecOTetromino() {
        Piece oTetromino = new OTetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        oTetromino.setPuits(puits);
        
        assertDoesNotThrow(() -> oTetromino.tourner(true));
        assertDoesNotThrow(() -> oTetromino.tourner(false));
    }
}