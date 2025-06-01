package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Coordonnees.
 * Cette classe teste le comportement des coordonnées dans le jeu Tetris.
 * Les tests vérifient :
 * - La création et l'initialisation des coordonnées
 * - Les getters et setters pour l'abscisse et l'ordonnée
 * - La représentation textuelle des coordonnées
 * - L'égalité et le hashcode des coordonnées
 * - La gestion des valeurs négatives
 *
 * @author Hugo
 */
public class CoordonneesTest {

    /** Première coordonnée de test (5,10) */
    private Coordonnees coord1;
    /** Deuxième coordonnée de test (5,10) - identique à coord1 */
    private Coordonnees coord2;
    /** Troisième coordonnée de test (7,14) - différente de coord1 */
    private Coordonnees coord3;
    /** Quatrième coordonnée de test (2,10) - même ordonnée que coord1 */
    private Coordonnees coord4;
    /** Cinquième coordonnée de test (5,2) - même abscisse que coord1 */
    private Coordonnees coord5;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée cinq coordonnées différentes pour tester les comparaisons.
     */
    @BeforeEach
    void setUp() {
        coord1 = new Coordonnees(5, 10);
        coord2 = new Coordonnees(5, 10);
        coord3 = new Coordonnees(7, 14);
        coord4 = new Coordonnees(2, 10);
        coord5 = new Coordonnees(5, 2);
    }

    /**
     * Teste le constructeur de Coordonnees.
     * Vérifie que les coordonnées sont correctement initialisées.
     */
    @Test
    @DisplayName("Test du constructeur")
    void testConstructor() {
        assertEquals(5, coord1.getAbscisse(), "L'abscisse devrait être 5");
        assertEquals(10, coord1.getOrdonnee(), "L'ordonnée devrait être 10");
    }

    /**
     * Teste les méthodes setter de Coordonnees.
     * Vérifie que les coordonnées peuvent être modifiées correctement.
     */
    @Test
    @DisplayName("Test de des méthodes des Setters")
    void testSetters() {
        coord1.setAbscisse(8);
        coord1.setOrdonnee(12);
        assertEquals(8, coord1.getAbscisse(), "L'abscisse devrait être 8 après modification");
        assertEquals(12, coord1.getOrdonnee(), "L'ordonnée devrait être 12 après modification");
    }

    /**
     * Teste la méthode toString de Coordonnees.
     * Vérifie que la représentation textuelle est au format "(x, y)".
     */
    @Test
    @DisplayName("Test de la méthode toString")
    void testToString() {
        assertEquals("(5, 10)", coord1.toString(), "La méthode toString devrait retourner '(5, 10)'");
    }

    /**
     * Teste la méthode equals de Coordonnees.
     * Vérifie que :
     * - Deux coordonnées identiques sont égales
     * - Une coordonnée est égale à elle-même
     * - Des coordonnées différentes ne sont pas égales
     * - Une coordonnée n'est pas égale à null ou à un autre type d'objet
     */
    @Test
    @DisplayName("Test de la méthode equals")
    void testEquals() {
        assertEquals(coord1, coord2, "Les coordonnées devraient être égales");
        assertTrue(coord1.equals(coord2), "Les coordonnées devraient être égales");
        assertEquals(coord1, coord1, "Une coordonnée devrait être égale à elle-même");
        assertFalse(coord1.equals(coord3), "Les coordonnées ne devraient pas être égales");
        assertFalse(coord1.equals(coord4), "Les coordonnées ne devraient pas être égales");
        assertFalse(coord1.equals(coord5), "Les coordonnées ne devraient pas être égales");
        assertNotEquals(coord1, coord3, "Les coordonnées ne devraient pas être égales");
        assertNotEquals(coord1, null, "Une coordonnée ne devrait pas être égale à null");
        assertNotEquals(coord1, "String", "Une coordonnée ne devrait pas être égale à un objet d'un autre type");
    }

    /**
     * Teste la méthode hashCode de Coordonnees.
     * Vérifie que :
     * - Des coordonnées identiques ont le même hashCode
     * - Des coordonnées différentes ont des hashCode différents
     */
    @Test
    @DisplayName("Test de la méthode hashCode")
    void testHashCode() {
        assertEquals(coord1.hashCode(), coord2.hashCode(), "Les hashCodes devraient être égaux pour des coordonnées identiques");
        assertNotEquals(coord1.hashCode(), coord3.hashCode(), "Les hashCodes ne devraient pas être égaux pour des coordonnées différentes");
    }

    /**
     * Teste les méthodes getter de Coordonnees.
     * Vérifie que les valeurs initiales sont correctement récupérées.
     */
    @Test
    @DisplayName("Test des getters")
    void testGetters() {
        assertEquals(5, coord1.getAbscisse(), "Le getter pour l'abscisse devrait retourner 5");
        assertEquals(10, coord1.getOrdonnee(), "Le getter pour l'ordonnée devrait retourner 10");
    }

    /**
     * Teste les méthodes setter avec des valeurs négatives.
     * Vérifie que les coordonnées peuvent être modifiées avec des valeurs négatives.
     */
    @Test
    @DisplayName("Test de la méthode setters avec des valeurs négatives")
    void testSettersWithNegativeValues() {
        coord1.setAbscisse(-3);
        coord1.setOrdonnee(-7);
        assertEquals(-3, coord1.getAbscisse(), "L'abscisse devrait être -3 après modification");
        assertEquals(-7, coord1.getOrdonnee(), "L'ordonnée devrait être -7 après modification");
    }
}


