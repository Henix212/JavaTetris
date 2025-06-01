package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Couleur.
 * Cette classe teste le comportement de l'énumération des couleurs utilisées dans le jeu Tetris.
 * Les tests vérifient :
 * - L'association correcte entre les constantes et les couleurs AWT
 * - Le nombre total de constantes de couleur
 * - La méthode valueOf pour la conversion de chaînes
 * - Les noms des constantes de couleur
 *
 * @author Hugo
 */
public class CouleurTest {

    /**
     * Teste l'association entre les constantes de couleur et les couleurs AWT.
     * Vérifie que chaque constante de couleur est associée à la bonne couleur AWT :
     * - ROUGE -> Color.RED
     * - ORANGE -> Color.ORANGE
     * - BLEU -> Color.BLUE
     * - VERT -> Color.GREEN
     * - JAUNE -> Color.YELLOW
     * - CYAN -> Color.CYAN
     * - VIOLET -> Color personnalisé (128, 0, 128)
     */
    @Test
    @DisplayName("Test des couleurs associées à chaque constante")
    void testCouleursAssociees() {
        assertEquals(Color.RED, Couleur.ROUGE.getCouleurPourAffichage(), "La couleur associée à ROUGE devrait être Color.RED");
        assertEquals(Color.ORANGE, Couleur.ORANGE.getCouleurPourAffichage(), "La couleur associée à ORANGE devrait être Color.ORANGE");
        assertEquals(Color.BLUE, Couleur.BLEU.getCouleurPourAffichage(), "La couleur associée à BLEU devrait être Color.BLUE");
        assertEquals(Color.GREEN, Couleur.VERT.getCouleurPourAffichage(), "La couleur associée à VERT devrait être Color.GREEN");
        assertEquals(Color.YELLOW, Couleur.JAUNE.getCouleurPourAffichage(), "La couleur associée à JAUNE devrait être Color.YELLOW");
        assertEquals(Color.CYAN, Couleur.CYAN.getCouleurPourAffichage(), "La couleur associée à CYAN devrait être Color.CYAN");
        assertEquals(new Color(128, 0, 128), Couleur.VIOLET.getCouleurPourAffichage(), "La couleur associée à VIOLET devrait être un violet personnalisé");
    }

    /**
     * Teste le nombre total de constantes de couleur.
     * Vérifie que l'énumération contient exactement 7 constantes de couleur.
     */
    @Test
    @DisplayName("Test de l'existence de toutes les constantes")
    void testNombreDeConstantes() {
        Couleur[] couleurs = Couleur.values();
        assertEquals(7, couleurs.length, "Il devrait y avoir 7 constantes dans l'énumération Couleur");
    }

    /**
     * Teste la méthode valueOf de l'énumération.
     * Vérifie que la méthode valueOf convertit correctement les chaînes en constantes de couleur.
     */
    @Test
    @DisplayName("Test de la méthode valueOf")
    void testValueOf() {
        assertEquals(Couleur.ROUGE, Couleur.valueOf("ROUGE"), "La méthode valueOf devrait retourner ROUGE pour 'ROUGE'");
        assertEquals(Couleur.BLEU, Couleur.valueOf("BLEU"), "La méthode valueOf devrait retourner BLEU pour 'BLEU'");
    }

    /**
     * Teste les noms des constantes de couleur.
     * Vérifie que chaque constante a le nom correct en majuscules :
     * - ROUGE, ORANGE, BLEU, VERT, JAUNE, CYAN, VIOLET
     */
    @Test
    @DisplayName("Test des noms des constantes")
    void testNomsDesConstantes() {
        assertEquals("ROUGE", Couleur.ROUGE.name(), "Le nom de la constante ROUGE devrait être 'ROUGE'");
        assertEquals("ORANGE", Couleur.ORANGE.name(), "Le nom de la constante ORANGE devrait être 'ORANGE'");
        assertEquals("BLEU", Couleur.BLEU.name(), "Le nom de la constante BLEU devrait être 'BLEU'");
        assertEquals("VERT", Couleur.VERT.name(), "Le nom de la constante VERT devrait être 'VERT'");
        assertEquals("JAUNE", Couleur.JAUNE.name(), "Le nom de la constante JAUNE devrait être 'JAUNE'");
        assertEquals("CYAN", Couleur.CYAN.name(), "Le nom de la constante CYAN devrait être 'CYAN'");
        assertEquals("VIOLET", Couleur.VIOLET.name(), "Le nom de la constante VIOLET devrait être 'VIOLET'");
    }
}
