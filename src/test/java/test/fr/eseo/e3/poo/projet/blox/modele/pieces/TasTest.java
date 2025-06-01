package test.fr.eseo.e3.poo.projet.blox.modele.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Tas.
 * Cette classe teste le comportement du tas de pièces dans le jeu Tetris.
 * Les tests vérifient :
 * - La création du tas avec différentes configurations
 * - L'ajout d'éléments et la détection du game over
 * - La suppression des lignes complètes
 * - La gestion des écouteurs d'événements
 * - Les cas limites et exceptions
 *
 * @author Hugo
 */
public class TasTest {

    /** Le puits de jeu associé au tas */
    private Puits puits;
    /** Le tas de pièces à tester */
    private Tas tas;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée un puits de dimensions 5x15 et un tas associé.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(5, 15);
        tas = new Tas(puits);
    }

    /**
     * Teste la récupération du puits associé au tas.
     * Vérifie que le puits retourné est bien celui passé au constructeur.
     */
    @Test
    public void testGetPuits(){
        assertEquals(puits, tas.getPuits(), "Le puits du tas doit être le même que celui passé au constructeur");
    }

    /**
     * Teste l'ajout d'éléments et la détection du game over.
     * Vérifie que :
     * - Les éléments sont correctement ajoutés au tas
     * - Le game over est détecté quand le tas atteint le haut du puits
     * - Le nombre total d'éléments est correct
     */
    @Test
    public void testAjouterElementsEtGameOver() {
        Piece piece = new ITetromino(new Coordonnees(0, 0),Couleur.ROUGE);
        Piece piece2 = new ITetromino(new Coordonnees(0, 1), Couleur.ROUGE);

        tas.ajouterElements(piece);
        tas.ajouterElements(piece2);
        assertTrue(tas.isGameOver());
        assertEquals(8, tas.getElements().size());
    }

    /**
     * Teste la suppression des lignes complètes.
     * Vérifie que :
     * - Une ligne complète est correctement supprimée
     * - Le nombre d'éléments est mis à jour après la suppression
     */
    @Test
    public void testSupprimerLigneComplete() {
        for (int i = 0; i < 5; i++) {
            tas.ajouterElements(new ITetromino(new Coordonnees(i, 0), Couleur.ROUGE));
        }
        tas.supprimerLignesCompletes();
        assertEquals(0, tas.getElements().size());
    }

    /**
     * Teste le constructeur avec un nombre spécifique d'éléments.
     * Vérifie que :
     * - Le tas est correctement créé
     * - Le nombre d'éléments correspond à celui spécifié
     */
    @Test
    public void testConstructeurTasAvecElements() {
        Tas autreTas = new Tas(new Puits(5, 15), 6, 2);
        assertNotNull(autreTas.getElements());
        assertEquals(6, autreTas.getElements().size(), "Le nombre d'éléments dans le tas doit correspondre au nombre spécifié");
    }

    /**
     * Teste le constructeur sans paramètres optionnels.
     * Vérifie que le tas est correctement construit même sans Random ou nombre de lignes.
     */
    @Test
    public void testConstructeurSansRandomOuNBlignes(){
        new Tas(puits, 4);
        assertNotNull(tas.getElements(), "Le tas doit être construit même sans Random ou nombre de lignes");
    }

    /**
     * Teste les cas d'exception lors de la construction du tas.
     * Vérifie que :
     * - Une exception est levée pour un nombre d'éléments invalide
     * - Une exception est levée pour des dimensions invalides
     */
    @Test
    public void testConstruireTasException() {
        assertThrows(IllegalArgumentException.class, () -> new Tas(puits,16,2,new Random()), "Le nombre d'éléments doit être supérieur à 0");
        assertThrows(IllegalArgumentException.class, () -> new Tas(puits,80,200,new Random()), "Le nombre d'éléments doit être supérieur à 0");
    }

    /**
     * Teste la construction du tas avec un générateur aléatoire.
     * Vérifie que :
     * - Le tas est correctement construit
     * - Le nombre d'éléments correspond à celui spécifié
     */
    @Test
    public void testConstruireTasWithRandom() {
        Tas tasAvecNullRandom = new Tas(puits, 5, 2, new Random());
        assertNotNull(tasAvecNullRandom.getElements(), "Le tas doit être construit");
        assertEquals(5, tasAvecNullRandom.getElements().size(), "Le nombre d'éléments doit correspondre au nombre spécifié");
    }

    /**
     * Teste l'ajout et la suppression des écouteurs d'événements.
     * Vérifie que :
     * - Un écouteur peut être ajouté et supprimé sans erreur
     * - L'écouteur n'est pas déclenché après sa suppression
     */
    @Test
    public void testAjoutEtSuppressionListenerSansEvenement() {
        AtomicBoolean declenche = new AtomicBoolean(false);

        PropertyChangeListener listener = evt -> {
            declenche.set(true);
        };

        tas.addPropertyChangeListener(listener);
        tas.removePropertyChangeListener(listener);

        // Aucune méthode ne déclenche réellement d'événement dans la classe Tas
        // donc on teste simplement que ça ne déclenche rien et ne plante pas
        assertFalse(declenche.get(), "Le listener ne doit pas être déclenché.");
    }    
}
