package test.fr.eseo.e3.poo.projet.blox.controleur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.*;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;

public class PieceDeplacementTest {

    private Puits puits;
    private VuePuits vuePuits;
    private PieceDeplacement pieceDeplacement;
    private Tas tas;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        tas = new Tas(puits);
        puits.setTas(tas);
                vuePuits = new VuePuits(puits) {
            @Override
            public int getTaille() {
                return 30;
            }
        };
        pieceDeplacement = new PieceDeplacement(vuePuits, puits);
    }

    @Test
    @DisplayName("Test mouseWheelMoved with current piece")
    public void testMouseWheelMovedDescend() {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        MouseWheelEvent event = new MouseWheelEvent(vuePuits, 0, 0, 0, 0, 0, 1, false, 0, 0, 1);
        pieceDeplacement.mouseWheelMoved(event);

        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();
        assertEquals(oldY + 1, newY);
    }

    @Test
    @DisplayName("Test mouseWheelMoved with no current piece")
    public void testMouseWheelMovedDescendNoPiece() {
        MouseWheelEvent event = new MouseWheelEvent(vuePuits, 0, 0, 0, 0, 0, 1, false, 0, 0, 1);
        assertThrows(NullPointerException.class, () -> {
            pieceDeplacement.mouseWheelMoved(event);
        });
    }

    @Test
    public void testMouseMovedToRight() throws BloxException {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        int cibleX = (oldX + 2) * vuePuits.getTaille();
        MouseEvent event = new MouseEvent(vuePuits, 0, 0, 0, cibleX, 0, 0, false);
        pieceDeplacement.mouseMoved(event);

        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        assertEquals(oldX + 2, newX);
        
    }

    @Test
    public void testMouseMovedToLeft() throws BloxException {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        Piece piece = puits.getPieceActuelle();

        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        int cibleX = (oldX - 3) * vuePuits.getTaille();

        MouseEvent event = new MouseEvent(
            vuePuits,
            MouseEvent.MOUSE_MOVED,
            System.currentTimeMillis(),
            0,
            cibleX,
            0,
            0,
            false
        );

        pieceDeplacement.mouseMoved(event);

        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        assertTrue(newX < oldX, "La pièce doit se déplacer vers la gauche");
    }

    @Test
    public void testMouseMovedNoPiece() {
        int cibleX = 100;
        MouseEvent event = new MouseEvent(vuePuits, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, cibleX, 0, 0, false, MouseEvent.BUTTON1);
        assertThrows(NullPointerException.class, () -> pieceDeplacement.mouseMoved(event));
    }

    @Test
    public void testKeyPressedRight() throws BloxException {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        pieceDeplacement.keyPressed(event);

        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertEquals(oldX + 1, newX);
    }

    @Test
    public void testKeyPressedLeft() throws BloxException {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.VK_LEFT, ' ');
        pieceDeplacement.keyPressed(event);

        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertEquals(oldX - 1, newX);
    }

    @Test
    public void testKeyPressedSpace() {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.VK_SPACE, ' ');

        pieceDeplacement.keyPressed(event);

        assertNotEquals(piece, puits.getPieceActuelle()); // Une nouvelle pièce a été générée
    }

    @Test
    public void testKeyPressedDown() throws BloxException {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');
        pieceDeplacement.keyPressed(event);

        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();
        assertEquals(oldY + 1, newY);
    }

    @Test
    public void testKeyPressedNoPiece() {

        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.KEY_PRESSED, ' ');

        assertThrows(NullPointerException.class, () -> {
            pieceDeplacement.keyPressed(event);
        });
    }

    @Test
    public void MouseWheelMouvedWithNegativeValue(){
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        MouseWheelEvent event = new MouseWheelEvent(
            vuePuits,
            MouseEvent.MOUSE_WHEEL,
            System.currentTimeMillis(),
            0,
            0, 0,
            0,
            false,
            MouseWheelEvent.WHEEL_UNIT_SCROLL,
            1,
            -1 
        );

        pieceDeplacement.mouseWheelMoved(event);

        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        assertEquals(oldY, newY);
    
    }

    @Test
    public void testKeyPressedDownCollision() throws BloxException {

        Piece piece = mock(Piece.class);
        doThrow(new BloxException("Collision simulée",0)).when(piece).deplacerDe(0, 1);

        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Tas tas = mock(Tas.class);
        puits.setTas(tas);

        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');

        pieceDeplacement.keyPressed(event);

        verify(tas).ajouterElements(piece);
        verify(tas).supprimerLignesCompletes();
        assertNotNull(puits.getPieceActuelle());
    }

    @Test
    public void testMouseWheelMovedDownCollision() throws BloxException {

        Piece piece = mock(Piece.class);
        doThrow(new BloxException("Collision simulée",0)).when(piece).deplacerDe(0, 1);

        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Tas tas = mock(Tas.class);
        puits.setTas(tas);

        MouseWheelEvent event = new MouseWheelEvent(
            vuePuits,
            MouseEvent.MOUSE_WHEEL,
            System.currentTimeMillis(),
            0,
            0, 0,
            0,
            false,
            MouseWheelEvent.WHEEL_UNIT_SCROLL,
            1,
            1
        );

        pieceDeplacement.mouseWheelMoved(event);

        verify(tas).ajouterElements(piece);
        verify(tas).supprimerLignesCompletes();
        assertNotNull(puits.getPieceActuelle());
    }
}