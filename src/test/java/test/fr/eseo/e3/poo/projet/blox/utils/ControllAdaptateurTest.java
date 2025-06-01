package test.fr.eseo.e3.poo.projet.blox.utils;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;

import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour ControllAdaptateur.
 * Cette classe teste le comportement de l'adaptateur de contrôle qui gère les événements
 * de souris et de clavier dans le jeu Tetris.
 * Les tests vérifient que toutes les méthodes d'événement peuvent être appelées sans erreur.
 *
 * @author Hugo
 */
public class ControllAdaptateurTest {

    /**
     * Sous-classe concrète minimale pour instancier ControllAdaptateur.
     * Permet de tester les méthodes de l'adaptateur sans implémentation spécifique.
     */
    static class ConcreteControlAdapter extends ControllAdaptateur {}

    /** Instance de l'adaptateur utilisée pour les tests */
    private final ControllAdaptateur adapter = new ConcreteControlAdapter();

    /**
     * Teste la gestion des événements de souris.
     * Vérifie que toutes les méthodes de gestion des événements de souris
     * peuvent être appelées sans lever d'exception.
     */
    @Test
    public void testMouseEvents() {
        MouseEvent mouseEvent = new MouseEvent(new java.awt.Label(), 0, 0, 0, 0, 0, 1, false);
        assertDoesNotThrow(() -> adapter.mouseClicked(mouseEvent));
        assertDoesNotThrow(() -> adapter.mousePressed(mouseEvent));
        assertDoesNotThrow(() -> adapter.mouseReleased(mouseEvent));
        assertDoesNotThrow(() -> adapter.mouseEntered(mouseEvent));
        assertDoesNotThrow(() -> adapter.mouseExited(mouseEvent));
        assertDoesNotThrow(() -> adapter.mouseDragged(mouseEvent));
        assertDoesNotThrow(() -> adapter.mouseMoved(mouseEvent));
    }

    /**
     * Teste la gestion des événements de molette de souris.
     * Vérifie que la méthode de gestion de la molette peut être appelée
     * sans lever d'exception.
     */
    @Test
    public void testMouseWheelEvent() {
        MouseWheelEvent mouseWheelEvent = new MouseWheelEvent(
            new java.awt.Label(), 0, 0, 0, 0, 0, 1, false,
            MouseWheelEvent.WHEEL_UNIT_SCROLL, 1, 1
        );
        assertDoesNotThrow(() -> adapter.mouseWheelMoved(mouseWheelEvent));
    }

    /**
     * Teste la gestion des événements de clavier.
     * Vérifie que toutes les méthodes de gestion des événements de clavier
     * peuvent être appelées sans lever d'exception.
     */
    @Test
    public void testKeyEvents() {
        KeyEvent keyEvent = new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_A, 'a');
        assertDoesNotThrow(() -> adapter.keyTyped(keyEvent));
        assertDoesNotThrow(() -> adapter.keyPressed(keyEvent));
        assertDoesNotThrow(() -> adapter.keyReleased(keyEvent));
    }
}