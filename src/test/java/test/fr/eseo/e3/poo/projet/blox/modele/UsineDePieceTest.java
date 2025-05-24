package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;

import static org.junit.jupiter.api.Assertions.*;

public class UsineDePieceTest {

    @BeforeEach
    void setUp() {
        // Réinitialiser les modes avant chaque test
        UsineDePiece.setMode(0); // Mode par défaut : ALEATOIRE_PIECE
    }

    @Test
    @DisplayName("Test du mode ALEATOIRE_PIECE")
    void testModeAleatoirePiece() {
        UsineDePiece.setMode(0); // Activer le mode ALEATOIRE_PIECE
        Tetromino piece1 = UsineDePiece.genererTetromino();
        Tetromino piece2 = UsineDePiece.genererTetromino();
        Tetromino piece3 = UsineDePiece.genererTetromino();

        assertNotNull(piece1, "La pièce générée ne devrait pas être null");
        assertNotNull(piece2, "La pièce générée ne devrait pas être null");
        assertNotNull(piece3, "La pièce générée ne devrait pas être null");
        assertTrue(piece1 instanceof Tetromino, "La pièce générée devrait être une instance de Tetromino");
        assertTrue(piece2 instanceof Tetromino, "La pièce générée devrait être une instance de Tetromino");
        assertTrue(piece3 instanceof Tetromino, "La pièce générée devrait être une instance de Tetromino");
    }

    @Test
    @DisplayName("Test du mode CYCLIC")
    void testModeCyclic() {
        UsineDePiece.setMode(1); // Activer le mode CYCLIC
        Tetromino piece1 = UsineDePiece.genererTetromino();
        Tetromino piece2 = UsineDePiece.genererTetromino();

        assertNotNull(piece1, "La pièce générée ne devrait pas être null");
        assertNotNull(piece2, "La pièce générée ne devrait pas être null");
        assertNotEquals(piece1, piece2, "Les pièces générées en mode CYCLIC devraient être différentes");
    }

    @Test
    @DisplayName("Test du mode ALEATOIRE_COMPLET")
    void testModeAleatoireComplet() {
        UsineDePiece.setMode(2); // Activer le mode ALEATOIRE_COMPLET
        Tetromino piece1 = UsineDePiece.genererTetromino();
        Tetromino piece2 = UsineDePiece.genererTetromino();

        assertNotNull(piece1, "La pièce générée ne devrait pas être null");
        assertNotNull(piece2, "La pièce générée ne devrait pas être null");
        assertTrue(piece1 instanceof Tetromino, "La pièce générée devrait être une instance de Tetromino");
        assertTrue(piece2 instanceof Tetromino, "La pièce générée devrait être une instance de Tetromino");
        assertNotEquals(piece1, piece2, "Les pièces générées en mode ALEATOIRE_COMPLET devraient être différentes");
    }

    @Test
    @DisplayName("Test des exceptions pour les modes invalides")
    void testInvalidMode() {
        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(3), "Un mode invalide devrait lever une exception");
        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(-1), "Un mode invalide devrait lever une exception");
    }
}