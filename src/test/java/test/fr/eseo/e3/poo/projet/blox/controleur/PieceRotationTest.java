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

/**
 * Classe de test unitaire pour PieceRotation.
 * Cette classe teste le comportement du contrôleur qui gère les rotations des pièces
 * dans le jeu Tetris. Les tests vérifient :
 * - Les rotations avec la souris (clic gauche et droit)
 * - Les rotations avec le clavier (touche flèche haut)
 * - Le comportement avec différentes pièces (I, O)
 * - Les cas limites (absence de pièce, touches non pertinentes)
 *
 * @author Hugo
 */
public class PieceRotationTest {

    /** Le contrôleur de rotation à tester */
    private PieceRotation pieceRotation;
    /** La vue du puits */
    private VuePuits vuePuits;
    /** Le puits de jeu */
    private Puits puits;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits, une vue et un contrôleur de rotation.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        vuePuits = new VuePuits(puits);
        pieceRotation = new PieceRotation(vuePuits);
    }

    /**
     * Teste la rotation avec le clic droit de la souris.
     * Vérifie que la rotation s'effectue sans erreur.
     */
    @Test
    public void testMouseClickedRightButton() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));
        puits.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));

        // Simulation du clic droit
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);

        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    /**
     * Teste la rotation avec le clic gauche de la souris.
     * Vérifie que la rotation s'effectue sans erreur avec une pièce présente.
     */
    @Test
    public void testMouseClickedLeftButtonWithPiece() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.BLEU));

        // Simulation du clic gauche
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    /**
     * Teste la rotation avec le clic droit de la souris.
     * Vérifie que la rotation s'effectue sans erreur avec une pièce présente.
     */
    @Test
    public void testMouseClickedRightButtonWithPiece() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.BLEU));

        // Simulation du clic droit
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    /**
     * Teste la rotation avec le clic gauche sur une pièce O.
     * Vérifie que la rotation s'effectue sans erreur avec une pièce O.
     */
    @Test
    public void testMouseClickedLeftButtonWithOTetromino() {
        // Création et placement d'une pièce O
        puits.setPieceSuivante(new OTetromino(new Coordonnees(4, 1), Couleur.VERT));

        // Simulation du clic gauche
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    /**
     * Teste le comportement avec une touche non pertinente.
     * Vérifie que l'action s'effectue sans erreur.
     */
    @Test
    public void testKeyPressedOtherKey() {
        // Simulation de l'appui sur une touche non pertinente
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED);

        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }

    /**
     * Teste la rotation avec la touche flèche haut.
     * Vérifie que la rotation s'effectue sans erreur avec une pièce présente.
     */
    @Test
    public void testKeyPressedUpArrowWithPiece() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(new ITetromino(new Coordonnees(4, 1), Couleur.JAUNE));

        // Simulation de l'appui sur la touche flèche haut
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }

    /**
     * Teste la rotation avec le clic gauche sans pièce.
     * Vérifie que l'action s'effectue sans erreur.
     */
    @Test
    public void testMouseClickedLeftButtonWithoutPiece() {
        // Simulation du clic gauche sans pièce
        MouseEvent leftClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON1);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(leftClick));
    }

    /**
     * Teste la rotation avec le clic droit sans pièce.
     * Vérifie que l'action s'effectue sans erreur.
     */
    @Test
    public void testMouseClickedRightButtonWithoutPiece() {
        // Simulation du clic droit sans pièce
        MouseEvent rightClick = new MouseEvent(vuePuits, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(),
                0, 10, 10, 1, false, MouseEvent.BUTTON3);
        assertDoesNotThrow(() -> pieceRotation.mouseClicked(rightClick));
    }

    /**
     * Teste la rotation avec la touche flèche haut sans pièce.
     * Vérifie que l'action s'effectue sans erreur.
     */
    @Test
    public void testKeyPressedUpArrowWithoutPiece() {
        // Simulation de l'appui sur la touche flèche haut sans pièce
        KeyEvent keyEvent = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED);
        assertDoesNotThrow(() -> pieceRotation.keyPressed(keyEvent));
    }
}
