package test.fr.eseo.e3.poo.projet.blox.utils;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;

import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.*;

public class ControllAdaptateurTest {

    // Sous-classe concrète minimale pour instancier ControllAdaptateur
    static class ConcreteControlAdapter extends ControllAdaptateur {}

    private final ControllAdaptateur adapter = new ConcreteControlAdapter();

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

    @Test
    public void testMouseWheelEvent() {
        MouseWheelEvent mouseWheelEvent = new MouseWheelEvent(new java.awt.Label(), 0, 0, 0, 0, 0, 1, false, MouseWheelEvent.WHEEL_UNIT_SCROLL, 1, 1);
        assertDoesNotThrow(() -> adapter.mouseWheelMoved(mouseWheelEvent));
    }

    @Test
    public void testKeyEvents() {
        KeyEvent keyEvent = new KeyEvent(new java.awt.Label(), 0, 0, 0, KeyEvent.VK_A, 'a');
        assertDoesNotThrow(() -> adapter.keyTyped(keyEvent));
        assertDoesNotThrow(() -> adapter.keyPressed(keyEvent));
        assertDoesNotThrow(() -> adapter.keyReleased(keyEvent));
    }
}