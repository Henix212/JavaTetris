package test.fr.eseo.e3.poo.projet.blox.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.views.VueGameOver;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour VueGameOver.
 * Cette classe teste l'interface de fin de partie du jeu Tetris.
 * Les tests vérifient la présence et le bon fonctionnement des composants
 * de l'interface (titre, score, boutons) ainsi que la mise à jour du score.
 *
 * @author Hugo
 */
public class VueGameOverTest {

    /** Vue de fin de partie à tester */
    private VueGameOver vue;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée une nouvelle fenêtre, un routeur et une vue de fin de partie.
     */
    @BeforeEach
    public void setUp() {
        JFrame frame = new JFrame();
        Routeur routeur = new Routeur(frame);
        Globals.routeur = routeur;
        vue = new VueGameOver(routeur);
    }

    /**
     * Teste la présence et l'affichage correct de tous les composants de l'interface.
     * Vérifie que :
     * - Le titre "Game Over" est présent
     * - Le label de score est présent avec la bonne taille de police
     * - Le bouton "Rejouer" est présent
     * - Le bouton "Quitter" est présent
     */
    @Test
    public void testComposantsAffiches() {
        // Variables pour suivre la présence des composants
        boolean contientTitre = false;
        boolean contientScore = false;
        boolean contientBoutonRejouer = false;
        boolean contientBoutonQuitter = false;

        // Parcours de tous les composants pour vérifier leur présence
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

        // Vérification de la présence de tous les composants
        assertTrue(contientTitre, "Le titre 'Game Over' est absent.");
        assertTrue(contientScore, "Le label de score est absent.");
        assertTrue(contientBoutonRejouer, "Le bouton 'Rejouer' est absent.");
        assertTrue(contientBoutonQuitter, "Le bouton 'Quitter' est absent.");
    }

    /**
     * Teste la mise à jour et l'affichage du score.
     * Vérifie que :
     * - Le score est réinitialisé
     * - Un nouveau score est ajouté
     * - La vue est mise à jour
     * - Le score est correctement affiché dans l'interface
     */
    @Test
    public void testUpdateScore() {
        // Réinitialisation et ajout d'un score
        Globals.score.reset();
        Globals.score.ajouter(100);
        vue.updateScore();

        // Vérification de l'affichage du score
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
