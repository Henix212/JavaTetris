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

public class ITetrominoTest {

    private ITetromino piece;
    private Puits puits;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new ITetromino(new Coordonnees(5, 5), Couleur.CYAN);
        piece.setPuits(puits);
    }

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

    @Test
    public void testCouleur() {
        for (Element element : piece.getElements()) {
            assertEquals(Couleur.CYAN, element.getCouleur());
        }
    }

    @Test
    public void testDeplacerDeSansException() {
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
    }

    @Test
    public void testTournerSansException() {
        assertDoesNotThrow(() -> piece.tourner(true));
        assertDoesNotThrow(() -> piece.tourner(false));
    }
}