package test.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.JTetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour JTetromino.
 * Cette classe teste le comportement spécifique de la pièce J du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte des éléments (forme en J)
 * - La couleur uniforme de tous les éléments
 * - Les déplacements légaux
 * - Les rotations dans les deux sens
 *
 * @author Hugo
 */
public class JTetrominoTest {

    /** La pièce J à tester */
    private JTetromino piece;
    /** Le puits de jeu associé à la pièce */
    private Puits puits;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions 10x20 et une pièce J en position (4,5) de couleur BLEU.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new JTetromino(new Coordonnees(4, 5), Couleur.BLEU);
        piece.setPuits(puits);
    }

    /**
     * Teste l'initialisation des éléments de la pièce J.
     * Vérifie que :
     * - La pièce contient exactement 4 éléments
     * - Les éléments forment un J
     * - Les coordonnées sont correctement positionnées autour du point central
     */
    @Test
    public void testElementsInitialisation() {
        List<Element> elements = piece.getElements();
        assertEquals(4, elements.size());
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 7))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 6))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 7))));
    }

    /**
     * Teste la couleur des éléments de la pièce.
     * Vérifie que tous les éléments de la pièce ont la même couleur (BLEU).
     */
    @Test
    public void testCouleur() {
        for (Element e : piece.getElements()) {
            assertEquals(Couleur.BLEU, e.getCouleur());
        }
    }

    /**
     * Teste les déplacements légaux de la pièce.
     * Vérifie que la pièce peut se déplacer d'une case vers le bas ou sur le côté.
     */
    @Test
    public void testDeplacerDeSansException() {
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
    }

    /**
     * Teste les rotations de la pièce.
     * Vérifie que la pièce peut tourner dans les deux sens (horaire et anti-horaire).
     */
    @Test
    public void testTournerSansException() {
        assertDoesNotThrow(() -> piece.tourner(true));
        assertDoesNotThrow(() -> piece.tourner(false));
    }
}