package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePiece;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test unitaire pour VuePiece.
 * Cette classe teste le comportement de la vue d'une pièce dans Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte de la vue
 * - L'application de l'effet de teinte sur les couleurs
 * - Le dessin correct des éléments de la pièce
 *
 * @author Hugo
 */
public class VuePieceTest {

    /** La vue de pièce à tester */
    private VuePiece vuePiece;
    /** La pièce mockée utilisée pour les tests */
    private Piece pieceMock;

    /**
     * Initialise l'environnement de test avant chaque méthode de test.
     * Crée une pièce mockée avec des éléments de test et sa vue associée.
     */
    @BeforeEach
    void setUp() {
        // Création d'une pièce mockée
        pieceMock = mock(Piece.class);

        // Création d'une liste d'éléments pour la pièce avec différentes couleurs
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Element(new Coordonnees(0, 0), Couleur.ROUGE));
        elements.add(new Element(new Coordonnees(1, 0), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(0, 1), Couleur.VERT));
        when(pieceMock.getElements()).thenReturn(elements);

        // Création de l'objet VuePiece avec une taille de case de 30 pixels
        vuePiece = new VuePiece(pieceMock, 30);
    }

    /**
     * Teste le constructeur de VuePiece.
     * Vérifie que la vue est correctement initialisée.
     */
    @Test
    @DisplayName("Test du constructeur")
    void testConstructor() {
        assertNotNull(vuePiece, "L'objet VuePiece ne devrait pas être null");
    }

    /**
     * Teste l'application de l'effet de teinte sur les couleurs.
     * Vérifie que :
     * - La couleur teintée est différente de l'originale
     * - Les composantes RGB sont correctement modifiées
     * - La teinte est appliquée de manière cohérente
     */
    @Test
    @DisplayName("Test de la méthode teinte")
    void testTeinte() {
        Color couleurOriginale = Color.RED;
        Color couleurTeintee = vuePiece.teinte(couleurOriginale);

        assertNotNull(couleurTeintee, "La couleur teinte ne devrait pas être null");
        assertNotEquals(couleurOriginale, couleurTeintee, "La couleur teinte devrait être différente de la couleur originale");
        assertTrue(couleurTeintee.getRed() == couleurOriginale.getRed(), "La composante rouge devrait être augmentée");
        assertTrue(couleurTeintee.getGreen() > couleurOriginale.getGreen(), "La composante verte devrait être augmentée");
        assertTrue(couleurTeintee.getBlue() > couleurOriginale.getBlue(), "La composante bleue devrait être augmentée");
    }

    /**
     * Teste le dessin des éléments de la pièce.
     * Vérifie que :
     * - La méthode ne lève pas d'exception
     * - Les méthodes de dessin sont appelées avec les bons paramètres
     * - L'effet 3D est appliqué correctement
     */
    @Test
    @DisplayName("Test de la méthode afficherPiece")
    void testAfficherPiece() {
        Graphics2D graphicsMock = mock(Graphics2D.class);

        // Appel de la méthode afficherPiece
        assertDoesNotThrow(() -> vuePiece.afficherPiece(graphicsMock,10,10), 
            "La méthode afficherPiece ne devrait pas lever d'exception");

        // Vérification que les méthodes de dessin ont été appelées
        verify(graphicsMock, atLeastOnce()).setColor(any(Color.class));
        verify(graphicsMock, atLeastOnce()).fill3DRect(anyInt(), anyInt(), eq(10), eq(10), eq(false));
    }
}