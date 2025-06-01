package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.views.VueJeux;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class VueJeuxTest {

    private VueJeux vueJeux;

    @BeforeEach
    public void setUp() {
        vueJeux = new VueJeux();
    }

    @Test
    public void testVueJeuxNonNull() {
        assertNotNull(vueJeux);
    }

    @Test
    public void testPuitsEtTas() {
        Puits puits = vueJeux.getPuits();
        Tas tas = vueJeux.getTas();

        assertNotNull(puits, "Le puits ne doit pas être null");
        assertNotNull(tas, "Le tas ne doit pas être null");
        assertEquals(tas, puits.getTas(), "Le tas doit être correctement assigné au puits");
    }

    @Test
    public void testVuePuitsNonNullEtFocusable() {
        VuePuits vuePuits = vueJeux.getVuePuits();
        assertNotNull(vuePuits);
        assertTrue(vuePuits.isFocusable());
    }

    @Test
    public void testScoreLabelPresent() {
        boolean found = false;
        for (java.awt.Component comp : vueJeux.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText().startsWith("Score")) {
                    found = true;
                    break;
                }
            }
        }
        assertTrue(found, "Un JLabel de score doit être présent");
    }
}