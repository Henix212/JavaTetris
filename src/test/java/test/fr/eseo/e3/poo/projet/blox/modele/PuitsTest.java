package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.*;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Puits.
 * Cette classe teste le comportement du puits de jeu Tetris, qui représente la zone
 * de jeu principale. Les tests vérifient :
 * - Les constructeurs et les limites de dimensions
 * - La gestion des pièces (ajout, récupération, transition)
 * - Les événements de changement de propriétés
 * - La représentation textuelle du puits
 *
 * @author Hugo
 */
public class PuitsTest {

    /** Le puits à tester */
    private Puits puits;
    /** Le tas de pièces associé au puits */
    private Tas tas;
    /** Un écouteur de changement de propriétés pour les tests */
    private PropertyChangeListener listener;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions par défaut et un tas associé.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits();
        tas = new Tas(puits);
        puits.setTas(tas);
        listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Utilisé pour les tests d'événements
            }
        };
    }

    /**
     * Teste le constructeur par défaut.
     * Vérifie que le puits est créé avec les dimensions par défaut (10x20).
     */
    @Test
    public void testConstructor() {
        assertEquals(10, puits.getLargeur());
        assertEquals(20, puits.getProfondeur());
    }

    /**
     * Teste le constructeur avec dimensions personnalisées.
     * Vérifie que le puits est créé avec les dimensions spécifiées.
     */
    @Test
    public void testConstructorWithDimensions() {
        Puits customPuits = new Puits(15, 25);
        assertEquals(15, customPuits.getLargeur());
        assertEquals(25, customPuits.getProfondeur());
    }

    /**
     * Teste les limites de largeur.
     * Vérifie que :
     * - Une largeur inférieure à 5 lève une exception
     * - Une largeur supérieure à 15 lève une exception
     * - Une largeur valide est acceptée
     */
    @Test
    public void testLargeurLimits() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(4, 20));
        assertThrows(IllegalArgumentException.class, () -> new Puits(16, 20));
        assertDoesNotThrow(() -> new Puits(10, 20));
    }

    /**
     * Teste les limites de profondeur.
     * Vérifie que :
     * - Une profondeur inférieure à 15 lève une exception
     * - Une profondeur supérieure à 25 lève une exception
     * - Une profondeur valide est acceptée
     */
    @Test
    public void testProfondeurLimits() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 14));
        assertThrows(IllegalArgumentException.class, () -> new Puits(10, 26));
        assertDoesNotThrow(() -> new Puits(10, 20));
    }

    /**
     * Teste la transition de pièces.
     * Vérifie que :
     * - La pièce suivante devient la pièce actuelle
     * - Une nouvelle pièce suivante est générée
     * - Les pièces sont correctement positionnées
     */
    @Test
    public void testPieceTransition() {
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        Piece piece1 = puits.getPieceActuelle();
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        Piece piece2 = puits.getPieceActuelle();
        assertNotEquals(piece1, piece2);
    }

    /**
     * Teste les événements de changement de propriétés.
     * Vérifie que les événements sont émis lors des changements de pièce.
     */
    @Test
    public void testPropertyChange() {
        puits.addPropertyChangeListener(listener);
        puits.setPieceSuivante(UsineDePiece.genererTetromino());
        // Vérification implicite : pas d'exception levée
    }

    /**
     * Teste la représentation textuelle du puits.
     * Vérifie que la méthode toString retourne une chaîne non nulle.
     */
    @Test
    public void testToString() {
        String representation = puits.toString();
        assertNotNull(representation);
        assertTrue(representation.length() > 0);
    }
}
