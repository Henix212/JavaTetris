package test.fr.eseo.e3.poo.projet.blox.utils;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.modele.Score;

import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Globals.
 * Cette classe teste l'initialisation correcte des variables globales du jeu Tetris.
 * Les tests vérifient que tous les composants essentiels sont correctement instanciés
 * et du bon type.
 *
 * @author Hugo
 */
public class GlobalsTest {

    /**
     * Teste l'initialisation des variables statiques globales.
     * Vérifie que :
     * - La fenêtre principale (JFrame) est correctement instanciée
     * - Le routeur est correctement instancié
     * - Le système de score est correctement instancié
     * Chaque composant doit être non-null et du bon type.
     */
    @Test
    public void testStaticsNotNull() {
        // Vérification de la fenêtre principale
        assertNotNull(Globals.frame, "Le JFrame doit être instancié");
        assertTrue(Globals.frame instanceof JFrame, "frame doit être une instance de JFrame");

        // Vérification du routeur
        assertNotNull(Globals.routeur, "Le routeur doit être instancié");
        assertTrue(Globals.routeur instanceof Routeur, "routeur doit être une instance de Routeur");

        // Vérification du système de score
        assertNotNull(Globals.score, "Le score doit être instancié");
        assertTrue(Globals.score instanceof Score, "score doit être une instance de Score");
    }
}