package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.views.VueGameOver;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class VueGameOverTest {

    private VueGameOver vue;

    @BeforeEach
    public void setUp() {
        JFrame frame = new JFrame();
        Routeur routeur = new Routeur(frame);
        Globals.routeur = routeur;
        vue = new VueGameOver(routeur);
    }

    @Test
    public void testComposantsAffiches() {
        boolean contientTitre = false;
        boolean contientScore = false;
        boolean contientBoutonRejouer = false;
        boolean contientBoutonQuitter = false;

        for (Component comp : vue.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if ("Game Over".equals(label.getText())) contientTitre = true;
                if (label.getFont().getSize() == 24) contientScore = true;
            }
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("Rejouer".equals(button.getText())) contientBoutonRejouer = true;
                if ("Quitter".equals(button.getText())) contientBoutonQuitter = true;
            }
        }

        assertTrue(contientTitre, "Le titre 'Game Over' est absent.");
        assertTrue(contientScore, "Le label de score est absent.");
        assertTrue(contientBoutonRejouer, "Le bouton 'Rejouer' est absent.");
        assertTrue(contientBoutonQuitter, "Le bouton 'Quitter' est absent.");
    }

    @Test
    public void testUpdateScore() {
        Globals.score.reset();
        Globals.score.ajouter(100);
        vue.updateScore();
        boolean contientScore = false;
        for (Component comp : vue.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if (label.getText() != null && label.getText().contains("Score")) {
                    contientScore = true;
                }
            }
        }
        assertTrue(contientScore, "Le score ne s'affiche pas correctement.");
    }
}
