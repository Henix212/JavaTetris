
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

public class OTetrominoTest {

    private Puits puits;
    private OTetromino piece;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        piece = new OTetromino(new Coordonnees(4, 5), Couleur.JAUNE);
        piece.setPuits(puits);
    }

    @Test
    public void testElementsInitialisation() {
        List<Element> elements = piece.getElements();
        assertEquals(4, elements.size());

        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 5))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(4, 4))));
        assertTrue(elements.stream().anyMatch(e -> e.getCoordonnees().equals(new Coordonnees(5, 4))));
    }

    @Test
    public void testCouleur() {
        for (Element element : piece.getElements()) {
            assertEquals(Couleur.JAUNE, element.getCouleur());
        }
    }

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
