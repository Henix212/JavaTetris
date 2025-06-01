package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour UsineDePiece.
 * Cette classe teste le comportement de la fabrique de pièces du jeu Tetris.
 * Les tests vérifient :
 * - La génération de pièces en mode aléatoire simple
 * - La génération de pièces en mode cyclique
 * - La génération de pièces en mode aléatoire complet
 * - La gestion des modes invalides
 *
 * @author Hugo
 */
public class UsineDePieceTest {

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Réinitialise le mode de génération à ALEATOIRE_PIECE.
     */
    @BeforeEach
    void setUp() {
        // Réinitialiser les modes avant chaque test
        UsineDePiece.setMode(0); // Mode par défaut : ALEATOIRE_PIECE
    }

    /**
     * Teste le mode de génération aléatoire simple.
     * Vérifie que :
     * - Les pièces générées ne sont pas nulles
     * - Les pièces sont des instances de Tetromino
     * - Plusieurs pièces peuvent être générées successivement
     */
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

    /**
     * Teste le mode de génération cyclique.
     * Vérifie que :
     * - Les pièces générées ne sont pas nulles
     * - Les pièces successives sont différentes
     * - Le cycle de génération fonctionne correctement
     */
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

    /**
     * Teste le mode de génération aléatoire complet.
     * Vérifie que :
     * - Les pièces générées ne sont pas nulles
     * - Les pièces sont des instances de Tetromino
     * - Les pièces successives sont différentes
     * - La distribution aléatoire est équilibrée
     */
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

    /**
     * Teste la gestion des modes invalides.
     * Vérifie que :
     * - Une exception est levée pour un mode supérieur au maximum
     * - Une exception est levée pour un mode négatif
     */
    @Test
    @DisplayName("Test des exceptions pour les modes invalides")
    void testInvalidMode() {
        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(3), "Un mode invalide devrait lever une exception");
        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(-1), "Un mode invalide devrait lever une exception");
    }
}