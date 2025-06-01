package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Element.
 * Cette classe teste le comportement des éléments qui composent les pièces du jeu Tetris.
 * Les tests vérifient :
 * - Les différents constructeurs
 * - Le déplacement des éléments
 * - La représentation textuelle
 * - L'égalité et le hachage
 * - Les getters et setters
 *
 * @author Hugo
 */
public class ElementTest {

    /** Premier élément de test avec coordonnées (5, 10) et couleur ROUGE */
    private Element element1;
    /** Deuxième élément de test identique à element1 */
    private Element element2;
    /** Troisième élément de test avec coordonnées (7, 14) et couleur BLEU */
    private Element element3;
    /** Quatrième élément de test avec coordonnées (5, 10) et couleur ORANGE */
    private Element element4;
    /** Cinquième élément de test avec coordonnées (5, 2) et couleur ROUGE */
    private Element element5;
    /** Sixième élément de test avec coordonnées (2, 10) et couleur ROUGE */
    private Element element6;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée six éléments de test avec différentes configurations.
     */
    @BeforeEach
    void setUp() {
        element1 = new Element(new Coordonnees(5, 10), Couleur.ROUGE);
        element2 = new Element(new Coordonnees(5, 10), Couleur.ROUGE);
        element3 = new Element(new Coordonnees(7, 14), Couleur.BLEU);
        element4 = new Element(new Coordonnees(5, 10), Couleur.ORANGE);
        element5 = new Element(new Coordonnees(5, 2), Couleur.ROUGE);
        element6 = new Element(new Coordonnees(2, 10), Couleur.ROUGE);
    }

    /**
     * Teste le constructeur avec coordonnées et couleur.
     * Vérifie que :
     * - Les coordonnées sont correctement initialisées
     * - La couleur est correctement initialisée
     */
    @Test
    @DisplayName("Test du constructeur avec Coordonnees et Couleur")
    void testConstructorWithCoordonneesAndCouleur() {
        assertEquals(new Coordonnees(5, 10), element1.getCoordonnees(), "Les coordonnées devraient être (5, 10)");
        assertEquals(Couleur.ROUGE, element1.getCouleur(), "La couleur devrait être ROUGE");
    }

    /**
     * Teste le constructeur avec coordonnées uniquement.
     * Vérifie que :
     * - Les coordonnées sont correctement initialisées
     * - La couleur par défaut est la première valeur de l'énumération Couleur
     */
    @Test
    @DisplayName("Test du constructeur avec Coordonnees uniquement")
    void testConstructorWithCoordonneesOnly() {
        Element element = new Element(new Coordonnees(3, 6));
        assertEquals(new Coordonnees(3, 6), element.getCoordonnees(), "Les coordonnées devraient être (3, 6)");
        assertEquals(Couleur.values()[0], element.getCouleur(), "La couleur par défaut devrait être la première valeur de Couleur");
    }

    /**
     * Teste le constructeur avec abscisse et ordonnée.
     * Vérifie que :
     * - Les coordonnées sont correctement initialisées
     * - La couleur par défaut est la première valeur de l'énumération Couleur
     */
    @Test
    @DisplayName("Test du constructeur avec abscisse et ordonnée")
    void testConstructorWithAbscisseAndOrdonnee() {
        Element element = new Element(2, 4);
        assertEquals(new Coordonnees(2, 4), element.getCoordonnees(), "Les coordonnées devraient être (2, 4)");
        assertEquals(Couleur.values()[0], element.getCouleur(), "La couleur par défaut devrait être la première valeur de Couleur");
    }

    /**
     * Teste la méthode deplacerDe.
     * Vérifie que l'élément est correctement déplacé selon les déplacements spécifiés.
     */
    @Test
    @DisplayName("Test de la méthode deplacerDe")
    void testDeplacerDe() {
        element1.deplacerDe(3, -2);
        assertEquals(new Coordonnees(8, 8), element1.getCoordonnees(), "Les coordonnées après déplacement devraient être (8, 8)");
    }

    /**
     * Teste la méthode toString.
     * Vérifie que la représentation textuelle est correcte.
     */
    @Test
    @DisplayName("Test de la méthode toString")
    void testToString() {
        assertEquals("(5, 10) - ROUGE", element1.toString(), "La méthode toString devrait retourner '(5, 10) - ROUGE'");
    }

    /**
     * Teste la méthode equals.
     * Vérifie que :
     * - Un élément est égal à lui-même
     * - Des éléments identiques sont égaux
     * - Des éléments différents ne sont pas égaux
     * - Un élément n'est pas égal à null
     * - Un élément n'est pas égal à un objet d'un autre type
     */
    @Test
    @DisplayName("Test de la méthode equals")
    void testEquals() {
        assertEquals(element1, element1, "Les éléments devraient être égaux");
        assertEquals(element1, element2, "Les éléments devraient être égaux");
        assertNotEquals(element1, element3, "Les éléments ne devraient pas être égaux");
        assertNotEquals(element1, null, "Un élément ne devrait pas être égal à null");
        assertNotEquals(element1, "String", "Un élément ne devrait pas être égal à un objet d'un autre type");
        assertNotEquals(element1, element4, "Les éléments ne devraient");
        assertNotEquals(element1, element5, "Les éléments ne devraient");
        assertNotEquals(element1, element6, "Les éléments ne devraient");
    }

    /**
     * Teste la méthode hashCode.
     * Vérifie que :
     * - Des éléments identiques ont le même hashCode
     * - Des éléments différents ont des hashCode différents
     */
    @Test
    @DisplayName("Test de la méthode hashCode")
    void testHashCode() {
        assertEquals(element1.hashCode(), element2.hashCode(), "Les hashCodes devraient être égaux pour des éléments identiques");
        assertNotEquals(element1.hashCode(), element3.hashCode(), "Les hashCodes ne devraient pas être égaux pour des éléments différents");
    }

    /**
     * Teste les getters et setters.
     * Vérifie que :
     * - Les coordonnées peuvent être modifiées
     * - La couleur peut être modifiée
     * - Les nouvelles valeurs sont correctement récupérées
     */
    @Test
    @DisplayName("Test des getters et setters")
    void testGettersAndSetters() {
        element1.setCoordonnes(new Coordonnees(1, 1));
        element1.setCouleur(Couleur.VERT);
        assertEquals(new Coordonnees(1, 1), element1.getCoordonnees(), "Les coordonnées devraient être (1, 1)");
        assertEquals(Couleur.VERT, element1.getCouleur(), "La couleur devrait être VERT");
    }
}
