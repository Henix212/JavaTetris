package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Score;

import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour Score.
 * Cette classe teste le comportement du système de score du jeu Tetris.
 * Les tests vérifient :
 * - L'initialisation du score
 * - L'ajout de points
 * - La réinitialisation du score
 * - La gestion des événements de changement de score
 * - L'ajout et la suppression des écouteurs d'événements
 *
 * @author Hugo
 */
public class ScoreTest {

    /** Le système de score à tester */
    private Score score;

    /**
     * Initialise l'environnement de test avant chaque méthode.
     * Crée une nouvelle instance de Score.
     */
    @BeforeEach
    public void setUp() {
        score = new Score();
    }

    /**
     * Teste l'initialisation du score.
     * Vérifie que le score est initialisé à 0.
     */
    @Test
    public void testInitialScore() {
        assertEquals(0, score.getScore());
    }

    /**
     * Teste l'ajout de points au score.
     * Vérifie que :
     * - Les points sont correctement ajoutés
     * - Le score total est mis à jour
     */
    @Test
    public void testAjouterPoints() {
        score.ajouter(100);
        assertEquals(100, score.getScore());

        score.ajouter(50);
        assertEquals(150, score.getScore());
    }

    /**
     * Teste la réinitialisation du score.
     * Vérifie que :
     * - Le score peut être réinitialisé à 0
     * - La réinitialisation fonctionne même après avoir ajouté des points
     */
    @Test
    public void testResetScore() {
        score.ajouter(200);
        assertEquals(200, score.getScore());

        score.reset();
        assertEquals(0, score.getScore());
    }

    /**
     * Teste le déclenchement des événements de changement de score.
     * Vérifie que :
     * - Les écouteurs sont notifiés lors d'un changement de score
     * - Les anciennes et nouvelles valeurs sont correctement transmises
     */
    @Test
    public void testPropertyChangeListenerTriggered() {
        AtomicBoolean triggered = new AtomicBoolean(false);
        AtomicInteger oldScore = new AtomicInteger();
        AtomicInteger newScore = new AtomicInteger();

        PropertyChangeListener listener = evt -> {
            if ("SCORE".equals(evt.getPropertyName())) {
                triggered.set(true);
                oldScore.set((Integer) evt.getOldValue());
                newScore.set((Integer) evt.getNewValue());
            }
        };

        score.addPropertyChangeListener(listener);
        score.ajouter(123);

        assertTrue(triggered.get());
        assertEquals(0, oldScore.get());
        assertEquals(123, newScore.get());
    }

    /**
     * Teste la suppression des écouteurs d'événements.
     * Vérifie que :
     * - Les écouteurs peuvent être supprimés
     * - Les écouteurs supprimés ne sont plus notifiés des changements
     */
    @Test
    public void testRemovePropertyChangeListener() {
        AtomicBoolean triggered = new AtomicBoolean(false);

        PropertyChangeListener listener = evt -> {
            if ("SCORE".equals(evt.getPropertyName())) {
                triggered.set(true);
            }
        };

        score.addPropertyChangeListener(listener);
        score.removePropertyChangeListener(listener);

        // Tente de déclencher un changement de score
        score.ajouter(100);

        // Le listener ne doit pas être appelé
        assertFalse(triggered.get(), "Le listener ne doit pas être déclenché après suppression");
    }
}
