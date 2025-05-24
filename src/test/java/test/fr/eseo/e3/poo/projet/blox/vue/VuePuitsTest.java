package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePiece;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VuePuitsTest {

    private Puits puits;
    private VuePuits vuePuits;

    @BeforeEach
    void setUp() {
        puits = mock(Puits.class); // Utilisation de Mockito pour simuler un Puits
        when(puits.getLargeur()).thenReturn(10);
        when(puits.getProfondeur()).thenReturn(20);
        vuePuits = new VuePuits(puits, 30); 
    }

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

    @Test
    @DisplayName("Test des getters et setters")
    void testGettersAndSetters() {
        vuePuits.setTaille(40);
        assertEquals(40, vuePuits.getTaille(), "La taille des cases devrait être mise à jour à 40");

        Puits nouveauPuits = mock(Puits.class);
        vuePuits.setPuits(nouveauPuits);
        assertEquals(nouveauPuits, vuePuits.getPuits(), "Le puits associé devrait être mis à jour");
    }

    @Test
    @DisplayName("Test de la méthode paintComponent")
    void testPaintComponent() {
        JPanel panel = new JPanel();
        panel.add(vuePuits);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();

        // Simulez un appel de peinture
        assertDoesNotThrow(() -> {
            Graphics graphics = panel.getGraphics();
            vuePuits.paint(graphics);
        }, "La méthode paint ne devrait pas lever d'exception");
    }

    @Test
    @DisplayName("Test de la méthode paintComponent")
    void testPaintComponentWithVuePiece() {
        JPanel panel = new JPanel();
        panel.add(vuePuits);
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.pack();

        VuePiece vuePieceMock = mock(VuePiece.class);
        PropertyChangeEvent event = new PropertyChangeEvent(puits, "PIECE ACTUELLE", null, vuePieceMock);
        vuePuits.propertyChange(event);

        assertDoesNotThrow(() -> {
            Graphics graphics = panel.getGraphics();
            vuePuits.paint(graphics);
        }, "La méthode paint ne devrait pas lever d'exception");
    }

    @Test
    @DisplayName("Test de la gestion des PropertyChangeEvents")
    void testPropertyChange() {
        PropertyChangeEvent event = new PropertyChangeEvent(puits, "PIECE ACTUELLE", null, mock(VuePiece.class));
        assertDoesNotThrow(() -> vuePuits.propertyChange(event), "La méthode propertyChange ne devrait pas lever d'exception");
    }

    @Test
    @DisplayName("Test de la gestion des PropertyChangeEvents")
    void testPropertyChangeNotgoodEvent() {
        PropertyChangeEvent event = new PropertyChangeEvent(puits, "PIECE", null, mock(VuePiece.class));
        assertDoesNotThrow(() -> vuePuits.propertyChange(event), "La méthode propertyChange ne devrait pas lever d'exception");
    }

    @Test
    @DisplayName("Test de la méthode setVuePiece")
    void testSetVuePiece() {
        VuePiece vuePieceMock = mock(VuePiece.class);
        vuePuits.propertyChange(new PropertyChangeEvent(puits, "PIECE ACTUELLE", null, vuePieceMock));
        assertEquals(vuePieceMock, vuePuits.getVuePiece(), "La vue de la pièce devrait être mise à jour correctement");
    }
}