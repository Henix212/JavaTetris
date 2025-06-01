package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePiece;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test unitaire pour VuePuits.
 * Cette classe teste le comportement de la vue principale du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte de la vue
 * - Les getters et setters
 * - La gestion des événements de changement de propriété
 * - Le comportement avec différentes tailles de cases
 *
 * @author Hugo
 */
public class VuePuitsTest {

    /** Le puits mocké utilisé pour les tests */
    private Puits puits;
    /** La vue du puits à tester */
    private VuePuits vuePuits;

    /**
     * Initialise l'environnement de test avant chaque méthode de test.
     * Crée un puits mocké avec des dimensions prédéfinies et sa vue associée.
     */
    @BeforeEach
    void setUp() {
        puits = mock(Puits.class); // Utilisation de Mockito pour simuler un Puits
        when(puits.getLargeur()).thenReturn(10);
        when(puits.getProfondeur()).thenReturn(20);
        vuePuits = new VuePuits(puits, 30); 
    }

    /**
     * Teste le constructeur de VuePuits.
     * Vérifie que :
     * - La vue est correctement initialisée
     * - La taille des cases est correcte
     * - Le puits est correctement associé
     * - Le constructeur par défaut fonctionne aussi
     */
    @Test
    @DisplayName("Test du constructeur")
    void testConstructor() {
        VuePuits VuePuitsBasic = new VuePuits(puits);

        assertNotNull(vuePuits, "L'objet VuePuits ne devrait pas être null");
        assertEquals(30, vuePuits.getTaille(), "La taille des cases devrait être 30");
        assertEquals(puits, vuePuits.getPuits(), "Le puits associé devrait être celui passé au constructeur");
        assertEquals(puits, VuePuitsBasic.getPuits(), "La largeur du puits devrait être 10");

        assertNotNull(VuePuitsBasic, "L'objet VuePuits ne devrait pas être null");
        assertEquals(30, VuePuitsBasic.getTaille(), "La taille des cases devrait être 30" );
    }

    /**
     * Teste les getters et setters de VuePuits.
     * Vérifie que :
     * - La taille des cases peut être modifiée
     * - Le puits associé peut être changé
     */
    @Test
    @DisplayName("Test des getters et setters")
    void testGettersAndSetters() {
        vuePuits.setTaille(40);
        assertEquals(40, vuePuits.getTaille(), "La taille des cases devrait être mise à jour à 40");

        Puits nouveauPuits = mock(Puits.class);
        vuePuits.setPuits(nouveauPuits);
        assertEquals(nouveauPuits, vuePuits.getPuits(), "Le puits associé devrait être mis à jour");
    }

    /**
     * Teste la gestion des événements de changement de pièce actuelle.
     * Vérifie que la vue réagit correctement à un changement de pièce.
     */
    @Test
    @DisplayName("Test de la gestion des PropertyChangeEvents")
    void testPropertyChange() {
        PropertyChangeEvent event = new PropertyChangeEvent(puits, "PIECE ACTUELLE", null, mock(VuePiece.class));
        assertDoesNotThrow(() -> vuePuits.propertyChange(event), "La méthode propertyChange ne devrait pas lever d'exception");
    }

    /**
     * Teste la gestion des événements de propriété non pertinents.
     * Vérifie que la vue ignore correctement les événements non liés à la pièce actuelle.
     */
    @Test
    @DisplayName("Test de la gestion des PropertyChangeEvents")
    void testPropertyChangeNotgoodEvent() {
        PropertyChangeEvent event = new PropertyChangeEvent(puits, "PIECE", null, mock(VuePiece.class));
        assertDoesNotThrow(() -> vuePuits.propertyChange(event), "La méthode propertyChange ne devrait pas lever d'exception");
    }
}