package test.fr.eseo.e3.poo.projet.blox.controleur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.junit.jupiter.api.Assertions.*;

public class PieceRotationTest {

    private PieceRotation pieceRotation;
    private VuePuits vuePuits;
    private Puits puits;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        vuePuits = new VuePuits(puits);
        pieceRotation = new PieceRotation(vuePuits);
    }

    @Test
    public void testMouseClickedRightButton() {
        puits.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));
        puits.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);

        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    @Test
    public void testMouseClickedLeftButtonWithPiece() {
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.BLEU));
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    @Test
    public void testMouseClickedRightButtonWithPiece() {
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.BLEU));
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    @Test
    public void testMouseClickedLeftButtonWithOTetromino() {
        puits.setPieceSuivante(new OTetromino(new Coordonnees(4, 1), Couleur.VERT));
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    @Test
    public void testKeyPressedOtherKey() {
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }

    @Test
    public void testKeyPressedUpArrowWithPiece() {
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.JAUNE));
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }

    @Test
    public void testMouseClickedLeftButtonWithoutPiece() {
        // No piece in the puits
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    @Test
    public void testMouseClickedRightButtonWithoutPiece() {
        // No piece in the puits
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    @Test
    public void testKeyPressedUpArrowWithoutPiece() {
        // No piece in the puits
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }
}
