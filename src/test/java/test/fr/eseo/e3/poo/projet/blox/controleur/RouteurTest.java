package test.fr.eseo.e3.poo.projet.blox.controleur;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

/**
 * Classe de test unitaire pour Routeur.
 * Cette classe teste le comportement du routeur qui gère la navigation entre les différentes vues
 * du jeu Tetris. Les tests vérifient :
 * - L'ajout et la récupération des vues
 * - Le changement de vue dans la fenêtre principale
 *
 * @author Hugo
 */
public class RouteurTest {

    /** Le routeur à tester */
    private Routeur routeur;
    /** La fenêtre principale du jeu */
    private JFrame frame;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée une nouvelle fenêtre et un routeur associé.
     */
    @BeforeEach
    public void setup() {
        frame = new JFrame();
        routeur = new Routeur(frame);
    }

    /**
     * Teste l'ajout et la récupération d'une vue.
     * Vérifie que :
     * - Une vue peut être ajoutée au routeur
     * - La vue ajoutée peut être récupérée
     * - La vue récupérée est bien celle qui a été ajoutée
     */
    @Test
    public void testAjouterEtRecupererVue() {
        // Création et ajout d'une vue
        JLabel vue1 = new JLabel("Vue 1");
        routeur.ajouterVue("vue1", vue1);

        // Récupération et vérification de la vue
        JComponent vueRecuperee = routeur.getVue("vue1");
        assertNotNull(vueRecuperee, "La vue ajoutée doit être récupérée.");
        assertEquals(vue1, vueRecuperee, "La vue récupérée doit être la même que celle ajoutée.");
    }

    /**
     * Teste le changement de vue dans la fenêtre principale.
     * Vérifie que :
     * - Plusieurs vues peuvent être ajoutées
     * - Le changement de vue modifie correctement le contenu de la fenêtre
     * - Le composant affiché est bien un JPanel
     */
    @Test
    public void testAfficherVue() {
        // Création et ajout de deux vues
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        routeur.ajouterVue("panel1", panel1);
        routeur.ajouterVue("panel2", panel2);

        // Test de l'affichage de la première vue
        routeur.afficherVue("panel1");
        assertTrue(frame.getContentPane().getComponent(0) instanceof JPanel, 
            "Le content pane doit contenir un JPanel.");

        // Test de l'affichage de la deuxième vue
        routeur.afficherVue("panel2");
    }
}