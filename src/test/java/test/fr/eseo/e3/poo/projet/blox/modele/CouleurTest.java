package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class CouleurTest {

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

    @Test
    @DisplayName("Test de l'existence de toutes les constantes")
    void testNombreDeConstantes() {
        Couleur[] couleurs = Couleur.values();
        assertEquals(7, couleurs.length, "Il devrait y avoir 7 constantes dans l'énumération Couleur");
    }

    @Test
    @DisplayName("Test de la méthode valueOf")
    void testValueOf() {
        assertEquals(Couleur.ROUGE, Couleur.valueOf("ROUGE"), "La méthode valueOf devrait retourner ROUGE pour 'ROUGE'");
        assertEquals(Couleur.BLEU, Couleur.valueOf("BLEU"), "La méthode valueOf devrait retourner BLEU pour 'BLEU'");
    }

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
