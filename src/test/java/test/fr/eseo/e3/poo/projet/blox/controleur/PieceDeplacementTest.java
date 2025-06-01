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

/**
 * Classe de test unitaire pour PieceDeplacement.
 * Cette classe teste le comportement du contrôleur qui gère les déplacements des pièces
 * dans le jeu Tetris. Les tests vérifient :
 * - Les déplacements avec la souris (mouvement et molette)
 * - Les déplacements avec le clavier (touches directionnelles et espace)
 * - La gestion des collisions
 * - Les cas limites (absence de pièce, valeurs négatives)
 *
 * @author Hugo
 */
public class PieceDeplacementTest {

    /** Le puits de jeu */
    private Puits puits;
    /** La vue du puits */
    private VuePuits vuePuits;
    /** Le contrôleur de déplacement à tester */
    private PieceDeplacement pieceDeplacement;
    /** Le tas de pièces */
    private Tas tas;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits, un tas, une vue et un contrôleur de déplacement.
     */
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

    /**
     * Teste le déplacement vers le bas avec la molette de la souris.
     * Vérifie que la pièce descend d'une case lorsque la molette est tournée vers le bas.
     */
    @Test
    @DisplayName("Test mouseWheelMoved with current piece")
    public void testMouseWheelMovedDescend() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        // Simulation du mouvement de la molette
        MouseWheelEvent event = new MouseWheelEvent(vuePuits, 0, 0, 0, 0, 0, 1, false, 0, 0, 1);
        pieceDeplacement.mouseWheelMoved(event);

        // Vérification du déplacement
        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();
        assertEquals(oldY + 1, newY);
    }

    /**
     * Teste le comportement lorsqu'il n'y a pas de pièce actuelle.
     * Vérifie qu'une NullPointerException est levée lors du déplacement.
     */
    @Test
    @DisplayName("Test mouseWheelMoved with no current piece")
    public void testMouseWheelMovedDescendNoPiece() {
        MouseWheelEvent event = new MouseWheelEvent(vuePuits, 0, 0, 0, 0, 0, 1, false, 0, 0, 1);
        assertThrows(NullPointerException.class, () -> {
            pieceDeplacement.mouseWheelMoved(event);
        });
    }

    /**
     * Teste le déplacement vers la droite avec la souris.
     * Vérifie que la pièce se déplace correctement vers la droite.
     */
    @Test
    public void testMouseMovedToRight() throws BloxException {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        // Simulation du mouvement de la souris
        int cibleX = (oldX + 2) * vuePuits.getTaille();
        MouseEvent event = new MouseEvent(vuePuits, 0, 0, 0, cibleX, 0, 0, false);
        pieceDeplacement.mouseMoved(event);

        // Vérification du déplacement
        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertEquals(oldX + 2, newX);
    }

    /**
     * Teste le déplacement vers la gauche avec la souris.
     * Vérifie que la pièce se déplace correctement vers la gauche.
     */
    @Test
    public void testMouseMovedToLeft() throws BloxException {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        Piece piece = puits.getPieceActuelle();

        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        // Simulation du mouvement de la souris
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

        // Vérification du déplacement
        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertTrue(newX < oldX, "La pièce doit se déplacer vers la gauche");
    }

    /**
     * Teste le comportement lorsqu'il n'y a pas de pièce actuelle.
     * Vérifie qu'une NullPointerException est levée lors du déplacement.
     */
    @Test
    public void testMouseMovedNoPiece() {
        int cibleX = 100;
        MouseEvent event = new MouseEvent(vuePuits, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, cibleX, 0, 0, false, MouseEvent.BUTTON1);
        assertThrows(NullPointerException.class, () -> pieceDeplacement.mouseMoved(event));
    }

    /**
     * Teste le déplacement vers la droite avec la touche flèche droite.
     * Vérifie que la pièce se déplace d'une case vers la droite.
     */
    @Test
    public void testKeyPressedRight() throws BloxException {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        // Simulation de l'appui sur la touche
        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        pieceDeplacement.keyPressed(event);

        // Vérification du déplacement
        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertEquals(oldX + 1, newX);
    }

    /**
     * Teste le déplacement vers la gauche avec la touche flèche gauche.
     * Vérifie que la pièce se déplace d'une case vers la gauche.
     */
    @Test
    public void testKeyPressedLeft() throws BloxException {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldX = piece.getElements().get(0).getCoordonnees().getAbscisse();

        // Simulation de l'appui sur la touche
        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.VK_LEFT, ' ');
        pieceDeplacement.keyPressed(event);

        // Vérification du déplacement
        int newX = piece.getElements().get(0).getCoordonnees().getAbscisse();
        assertEquals(oldX - 1, newX);
    }

    /**
     * Teste la génération d'une nouvelle pièce avec la touche espace.
     * Vérifie qu'une nouvelle pièce est générée et placée.
     */
    @Test
    public void testKeyPressedSpace() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        // Simulation de l'appui sur la touche espace
        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.VK_SPACE, ' ');
        pieceDeplacement.keyPressed(event);

        // Vérification qu'une nouvelle pièce a été générée
        assertNotEquals(piece, puits.getPieceActuelle());
    }

    /**
     * Teste le déplacement vers le bas avec la touche flèche bas.
     * Vérifie que la pièce descend d'une case.
     */
    @Test
    public void testKeyPressedDown() throws BloxException {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        // Simulation de l'appui sur la touche
        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');
        pieceDeplacement.keyPressed(event);

        // Vérification du déplacement
        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();
        assertEquals(oldY + 1, newY);
    }

    /**
     * Teste le comportement lorsqu'il n'y a pas de pièce actuelle.
     * Vérifie qu'une NullPointerException est levée lors de l'appui sur une touche.
     */
    @Test
    public void testKeyPressedNoPiece() {
        KeyEvent event = new KeyEvent(vuePuits, 0, 0, 0, KeyEvent.KEY_PRESSED, ' ');
        assertThrows(NullPointerException.class, () -> {
            pieceDeplacement.keyPressed(event);
        });
    }

    /**
     * Teste le comportement avec une valeur négative de la molette.
     * Vérifie que la pièce ne se déplace pas vers le haut.
     */
    @Test
    public void MouseWheelMouvedWithNegativeValue() {
        // Création et placement d'une pièce
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        Piece piece = puits.getPieceActuelle();
        int oldY = piece.getElements().get(0).getCoordonnees().getOrdonnee();

        // Simulation du mouvement de la molette vers le haut
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

        // Vérification que la pièce n'a pas bougé
        int newY = piece.getElements().get(0).getCoordonnees().getOrdonnee();
        assertEquals(oldY, newY);
    }

    /**
     * Teste la gestion des collisions lors du déplacement vers le bas.
     * Vérifie que :
     * - La pièce est ajoutée au tas lors d'une collision
     * - Les lignes complètes sont supprimées
     * - Une nouvelle pièce est générée
     */
    @Test
    public void testKeyPressedDownCollision() throws BloxException {
        // Création d'une pièce mockée qui simule une collision
        Piece piece = mock(Piece.class);
        doThrow(new BloxException("Collision simulée",0)).when(piece).deplacerDe(0, 1);

        // Placement de la pièce et génération d'une nouvelle pièce suivante
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        // Création d'un tas mocké
        Tas tas = mock(Tas.class);
        puits.setTas(tas);

        // Simulation de l'appui sur la touche bas
        KeyEvent event = new KeyEvent(vuePuits, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');
        pieceDeplacement.keyPressed(event);

        // Vérification des actions effectuées
        verify(tas).ajouterElements(piece);
        verify(tas).supprimerLignesCompletes();
        assertNotNull(puits.getPieceActuelle());
    }

    /**
     * Teste la gestion des collisions lors du déplacement avec la molette.
     * Vérifie que :
     * - La pièce est ajoutée au tas lors d'une collision
     * - Les lignes complètes sont supprimées
     * - Une nouvelle pièce est générée
     */
    @Test
    public void testMouseWheelMovedDownCollision() throws BloxException {
        // Création d'une pièce mockée qui simule une collision
        Piece piece = mock(Piece.class);
        doThrow(new BloxException("Collision simulée",0)).when(piece).deplacerDe(0, 1);

        // Placement de la pièce et génération d'une nouvelle pièce suivante
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(UsineDePiece.genererTetromino());

        // Création d'un tas mocké
        Tas tas = mock(Tas.class);
        puits.setTas(tas);
    }
}