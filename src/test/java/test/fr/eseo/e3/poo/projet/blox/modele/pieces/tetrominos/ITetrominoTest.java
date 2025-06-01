package test.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour ITetromino.
 * Cette classe teste le comportement spécifique de la pièce I (barre) du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte des éléments (forme en I verticale)
 * - La couleur uniforme de tous les éléments
 * - Les déplacements légaux
 * - Les rotations dans les deux sens
 *
 * @author Hugo
 */
public class ITetrominoTest {

    /** La pièce I à tester */
    private ITetromino piece;
    /** Le puits de jeu associé à la pièce */
    private Puits puits;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions 10x20 et une pièce I en position (5,5) de couleur CYAN.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new ITetromino(new Coordonnees(5, 5), Couleur.CYAN);
        piece.setPuits(puits);
    }

    /**
     * Teste l'initialisation des éléments de la pièce I.
     * Vérifie que :
     * - La pièce contient exactement 4 éléments
     * - Les éléments forment une barre verticale
     * - Les coordonnées sont correctement positionnées autour du point central
     */
    @Test
    public void testElementsInitialisation() {
        List<Element> elements = piece.getElements();
        assertEquals(4, elements.size());

        List<Coordonnees> coords = elements.stream()
                .map(Element::getCoordonnees)
                .collect(Collectors.toList());

        assertTrue(coords.contains(new Coordonnees(5, 5)));
        assertTrue(coords.contains(new Coordonnees(5, 6)));
        assertTrue(coords.contains(new Coordonnees(5, 4)));
        assertTrue(coords.contains(new Coordonnees(5, 3)));
    }

    /**
     * Teste la couleur des éléments de la pièce.
     * Vérifie que tous les éléments de la pièce ont la même couleur (CYAN).
     */
    @Test
    public void testCouleur() {
        for (Element element : piece.getElements()) {
            assertEquals(Couleur.CYAN, element.getCouleur());
        }
    }

    /**
     * Teste les déplacements légaux de la pièce.
     * Vérifie que la pièce peut se déplacer d'une case vers le bas ou sur le côté.
     */
    @Test
    public void testDeplacerDeSansException() {
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
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