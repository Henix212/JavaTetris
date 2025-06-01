package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.views.VueMenu;

import javax.swing.JButton;
import java.awt.Component;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test unitaire pour VueMenu.
 * Cette classe teste l'interface du menu principal du jeu Tetris.
 * Les tests vérifient l'initialisation correcte de la vue et la présence
 * des boutons avec leurs écouteurs d'événements.
 *
 * @author Hugo
 */
public class VueMenuTest {

    /** Routeur mocké pour les tests */
    private Routeur routeur;
    /** Vue du menu à tester */
    private VueMenu vueMenu;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un routeur mocké et une nouvelle instance de VueMenu.
     */
    @BeforeEach
    public void setUp() {
        routeur = mock(Routeur.class);
        vueMenu = new VueMenu(routeur);
    }

    /**
     * Teste l'initialisation correcte de la vue du menu.
     * Vérifie que :
     * - La vue n'est pas null
     * - La vue contient le bon nombre de composants (4)
     */
    @Test
    public void testVueMenuIsNotNull() {
        assertNotNull(vueMenu);
        assertEquals(4, vueMenu.getComponentCount());
    }

    /**
     * Teste la présence et la configuration des boutons du menu.
     * Vérifie que :
     * - Le bouton "Jouer" est présent et a des écouteurs d'événements
     * - Le bouton "Quit" est présent et a des écouteurs d'événements
     */
    @Test
    public void testButtonsHaveActionListeners() {
        // Variables pour stocker les références aux boutons
        JButton jouerButton = null;
        JButton quitButton = null;

        // Recherche des boutons dans les composants
        for (Component comp : vueMenu.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String text = btn.getText();
                if ("Jouer".equals(text)) {
                    jouerButton = btn;
                } else if ("Quit".equals(text)) {
                    quitButton = btn;
                }
            }
        }

        // Vérification du bouton "Jouer"
        assertNotNull(jouerButton, "Le bouton 'Jouer' est absent");
        assertTrue(jouerButton.getActionListeners().length > 0, "Le bouton 'Jouer' n'a pas d'écouteurs d'événements");

        // Vérification du bouton "Quit"
        assertNotNull(quitButton, "Le bouton 'Quit' est absent");
        assertTrue(quitButton.getActionListeners().length > 0, "Le bouton 'Quit' n'a pas d'écouteurs d'événements");
    }
}