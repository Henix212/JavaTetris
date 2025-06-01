package test.fr.eseo.e3.poo.projet.blox.controleur;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

public class RouteurTest {

    private Routeur routeur;
    private JFrame frame;

    @BeforeEach
    public void setup() {
        frame = new JFrame();
        routeur = new Routeur(frame);
    }

    @Test
    public void testAjouterEtRecupererVue() {
        JLabel vue1 = new JLabel("Vue 1");
        routeur.ajouterVue("vue1", vue1);

        JComponent vueRecuperee = routeur.getVue("vue1");
        assertNotNull(vueRecuperee, "La vue ajoutée doit être récupérée.");
        assertEquals(vue1, vueRecuperee, "La vue récupérée doit être la même que celle ajoutée.");
    }

    @Test
    public void testAfficherVue() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        routeur.ajouterVue("panel1", panel1);
        routeur.ajouterVue("panel2", panel2);


        routeur.afficherVue("panel1");

        assertTrue(frame.getContentPane().getComponent(0) instanceof JPanel, "Le content pane doit contenir un JPanel.");

        routeur.afficherVue("panel2");
    }
}