package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.views.VueMenu;

import javax.swing.JButton;
import java.awt.Component;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VueMenuTest {

    private Routeur routeur;
    private VueMenu vueMenu;

    @BeforeEach
    public void setUp() {
        routeur = mock(Routeur.class);
        vueMenu = new VueMenu(routeur);
    }

    @Test
    public void testVueMenuIsNotNull() {
        assertNotNull(vueMenu);
        assertEquals(4, vueMenu.getComponentCount());
    }

    @Test
    public void testButtonsHaveActionListeners() {
        JButton jouerButton = null;
        JButton iaButton = null;
        JButton quitButton = null;

        for (Component comp : vueMenu.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String text = btn.getText();
                if ("Jouer".equals(text)) {
                    jouerButton = btn;
                } else if ("AI Mode".equals(text)) {
                    iaButton = btn;
                } else if ("Quit".equals(text)) {
                    quitButton = btn;
                }
            }
        }

        assertNotNull(jouerButton);
        assertTrue(jouerButton.getActionListeners().length > 0);

        assertNotNull(iaButton);
        assertTrue(iaButton.getActionListeners().length > 0);

        assertNotNull(quitButton);
        assertTrue(quitButton.getActionListeners().length > 0);
    }
}