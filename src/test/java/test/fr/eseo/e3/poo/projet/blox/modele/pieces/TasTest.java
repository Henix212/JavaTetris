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

public class TasTest {

    private Puits puits;
    private Tas tas;

    @BeforeEach
    public void setUp() {
        puits = new Puits(5, 15);
        tas = new Tas(puits);
    }

    @Test
    public void testGetPuits(){
        assertEquals(puits, tas.getPuits(), "Le puits du tas doit être le même que celui passé au constructeur");
    }

    @Test
    public void testAjouterElementsEtGameOver() {
        Piece piece = new ITetromino(new Coordonnees(0, 0),Couleur.ROUGE);
        Piece piece2 = new ITetromino(new Coordonnees(0, 1), Couleur.ROUGE);

        tas.ajouterElements(piece);
        tas.ajouterElements(piece2);
        assertTrue(tas.isGameOver());
        assertEquals(8, tas.getElements().size());
    }

    @Test
    public void testSupprimerLigneComplete() {
        for (int i = 0; i < 5; i++) {
            tas.ajouterElements(new ITetromino(new Coordonnees(i, 0), Couleur.ROUGE));
        }
        tas.supprimerLignesCompletes();
        assertEquals(0, tas.getElements().size());
    }
    

    @Test
    public void testConstructeurTasAvecElements() {
        Tas autreTas = new Tas(new Puits(5, 15), 6, 2);
        assertNotNull(autreTas.getElements());
        assertEquals(6, autreTas.getElements().size(), "Le nombre d'éléments dans le tas doit correspondre au nombre spécifié");
    }

    @Test
    public void testConstructeurSansRandomOuNBlignes(){
        new Tas(puits, 4);
        assertNotNull(tas.getElements(), "Le tas doit être construit même sans Random ou nombre de lignes");
    }

    @Test
    public void testConstruireTasException() {
        assertThrows(IllegalArgumentException.class, () -> new Tas(puits,16,2,new Random()), "Le nombre d'éléments doit être supérieur à 0");
        assertThrows(IllegalArgumentException.class, () -> new Tas(puits,80,200,new Random()), "Le nombre d'éléments doit être supérieur à 0");
    }

    @Test
    public void testConstruireTasWithRandom() {
        Tas tasAvecNullRandom = new Tas(puits, 5, 2, new Random());
        assertNotNull(tasAvecNullRandom.getElements(), "Le tas doit être construit");
        assertEquals(5, tasAvecNullRandom.getElements().size(), "Le nombre d'éléments doit correspondre au nombre spécifié");
    }

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
