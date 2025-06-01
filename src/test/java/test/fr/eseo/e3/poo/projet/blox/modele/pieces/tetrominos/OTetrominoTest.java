package test.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import java.util.stream.Collectors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour OTetromino.
 * Cette classe teste le comportement spécifique de la pièce O (carré) du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte des éléments (2x2 carré)
 * - La couleur uniforme de tous les éléments
 * - La rotation qui ne change pas la forme (car la pièce est symétrique)
 *
 * @author Hugo
 */
public class OTetrominoTest {

    /** Le puits de jeu associé à la pièce */
    private Puits puits;
    /** La pièce O à tester */
    private OTetromino piece;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions 10x20 et une pièce O en position (4,5) de couleur JAUNE.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        piece = new OTetromino(new Coordonnees(4, 5), Couleur.JAUNE);
        piece.setPuits(puits);
    }

    /**
     * Teste l'initialisation des éléments de la pièce O.
     * Vérifie que :
     * - La pièce contient exactement 4 éléments
     * - Les éléments forment un carré 2x2
     * - Les coordonnées sont correctement positionnées autour du point central
     */
    @Test
    public void testElementsInitialisation() {
        List<Element> elements = piece.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 4))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 4))));
    }

    /**
     * Teste la couleur des éléments de la pièce.
     * Vérifie que tous les éléments de la pièce ont la même couleur (JAUNE).
     */
    @Test
    public void testCouleur() {
        for (Element element : piece.getElements()) {
            assertEquals(Couleur.JAUNE, element.getCouleur());
        }
    }

    /**
     * Teste que la rotation ne change pas la forme de la pièce O.
     * Vérifie que les coordonnées des éléments restent identiques après rotation
     * car la pièce O est symétrique (carré).
     */
    @Test
    public void testRotationNeChangePasLesCoordonnees() {
        List<Coordonnees> avant = piece.getElements().stream()
            .map(Element::getCoordonnees)
            .collect(Collectors.toList());
        assertDoesNotThrow(() -> piece.tourner(true));
        List<Coordonnees> apres = piece.getElements().stream()
            .map(Element::getCoordonnees)
            .collect(Collectors.toList());
        assertEquals(avant, apres);
    }
}
