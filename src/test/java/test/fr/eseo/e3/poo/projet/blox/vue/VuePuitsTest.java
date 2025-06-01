package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePiece;
import java.awt.image.BufferedImage;


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
    @DisplayName("Test de paintComponent")
    void testPaintComponentWithPropertyChange() {
        // Initialisation du puits et de la vue
        Puits puits = new Puits(10, 20); // ou ton constructeur réel
        VuePuits vuePuits = new VuePuits(puits); // dépend de ton code 

        Piece piece = mock(Piece.class);
        VuePiece vuePiece = new VuePiece(piece,30); // dépend de ta structure réelle

        PropertyChangeEvent event = new PropertyChangeEvent(
            puits, "PIECE ACTUELLE", null, vuePiece
        );
        vuePuits.propertyChange(event);

        // Vérification que le paint ne lève pas d’exception
        assertDoesNotThrow(() -> {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            vuePuits.paintComponent(g2d);
            g2d.dispose();
        }, "paintComponent ne doit pas lever exception"); 
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