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

public class JTetrominoTest {

    private JTetromino piece;
    private Puits puits;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new JTetromino(new Coordonnees(4, 5), Couleur.BLEU);
        piece.setPuits(puits);
    }

    @Test
    public void testElementsInitialisation() {
        List<Element> elements = piece.getElements();
        assertEquals(4, elements.size());
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 7))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 6))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 7))));
    }

    @Test
    public void testCouleur() {
        for (Element e : piece.getElements()) {
            assertEquals(Couleur.BLEU, e.getCouleur());
        }
    }

    @Test
    public void testDeplacerDeSansException() {
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
    }

    @Test
    public void testTournerSansException() {
        assertDoesNotThrow(() -> piece.tourner(true));
        assertDoesNotThrow(() -> piece.tourner(false));
    }
}