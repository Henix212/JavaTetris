package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.views.VueJeux;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour VueJeux.
 * Cette classe teste le comportement de la vue principale du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation correcte de la vue
 * - La présence et la configuration du puits et du tas
 * - La configuration de la vue du puits
 * - L'affichage du score
 *
 * @author Hugo
 */
public class VueJeuxTest {

    /** La vue du jeu à tester */
    private VueJeux vueJeux;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée une nouvelle instance de VueJeux.
     */
    @BeforeEach
    public void setUp() {
        vueJeux = new VueJeux();
    }

    /**
     * Teste l'initialisation de la vue du jeu.
     * Vérifie que la vue est correctement instanciée.
     */
    @Test
    public void testVueJeuxNonNull() {
        assertNotNull(vueJeux);
    }

    /**
     * Teste la configuration du puits et du tas.
     * Vérifie que :
     * - Le puits est correctement instancié
     * - Le tas est correctement instancié
     * - Le tas est correctement associé au puits
     */
    @Test
    public void testPuitsEtTas() {
        // Récupération des composants
        Puits puits = vueJeux.getPuits();
        Tas tas = vueJeux.getTas();

        // Vérification de l'initialisation
        assertNotNull(puits, "Le puits ne doit pas être null");
        assertNotNull(tas, "Le tas ne doit pas être null");
        assertEquals(tas, puits.getTas(), "Le tas doit être correctement assigné au puits");
    }

    /**
     * Teste la configuration de la vue du puits.
     * Vérifie que :
     * - La vue du puits est correctement instanciée
     * - La vue du puits est focusable pour la gestion des événements clavier
     */
    @Test
    public void testVuePuitsNonNullEtFocusable() {
        VuePuits vuePuits = vueJeux.getVuePuits();
        assertNotNull(vuePuits);
        assertTrue(vuePuits.isFocusable());
    }

    /**
     * Teste la présence du label de score.
     * Vérifie qu'un JLabel affichant le score est présent dans la vue
     * et qu'il commence par le texte "Score".
     */
    @Test
    public void testScoreLabelPresent() {
        boolean found = false;
        // Parcours des composants pour trouver le label de score
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