

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

public class TetrominoTest {

    private Piece piece;
    private Puits puits;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        puits.setTas(new Tas(puits));
        piece = new ITetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        piece.setPuits(puits);
    }

    @Test
    public void testDeplacementLegal() {
        assertDoesNotThrow(() -> piece.deplacerDe(0, 1));
        assertDoesNotThrow(() -> piece.deplacerDe(1, 0));
    }

    @Test
    public void testDeplacementIllegal() {
        assertThrows(IllegalArgumentException.class, () -> piece.deplacerDe(1, 1));
        assertThrows(IllegalArgumentException.class, () -> piece.deplacerDe(2, 0));
    }

    @Test
    public void testRotationSensHoraireEtAntiHoraire() {
        assertDoesNotThrow(() -> piece.tourner(true));
        assertDoesNotThrow(() -> piece.tourner(false));
    }

    @Test
    public void testGetElements() {
        List<Element> elements = piece.getElements();
        assertNotNull(elements);
        assertEquals(4, elements.size());
    }

    @Test
    public void testSetPosition() {
        piece.setPosition(2, 3);
        Coordonnees pivot = piece.getElements().get(0).getCoordonnees();
        assertEquals(2, pivot.getAbscisse());
        assertEquals(3, pivot.getOrdonnee());
    }

    @Test
    public void testClone() {
        Piece clone = piece.clone();
        assertNotSame(piece, clone);
        assertEquals(piece.getElements().size(), clone.getElements().size());
        assertEquals(piece.getPuits(), clone.getPuits());
    }

    @Test
    public void testToStringFormat() {
        String output = piece.toString();
        assertTrue(output.contains("ITetromino"));
        assertTrue(output.contains("ROUGE"));
    }

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

    @Test
    public void testTournerAvecOTetromino() {
        Piece oTetromino = new OTetromino(new Coordonnees(4, 0), Couleur.ROUGE);
        oTetromino.setPuits(puits);
        
        assertDoesNotThrow(() -> oTetromino.tourner(true));
        assertDoesNotThrow(() -> oTetromino.tourner(false));
    }
}