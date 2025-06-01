package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Score;

import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    private Score score;

    @BeforeEach
    public void setUp() {
        score = new Score();
    }

    @Test
    public void testInitialScore() {
        assertEquals(0, score.getScore());
    }

    @Test
    public void testAjouterPoints() {
        score.ajouter(100);
        assertEquals(100, score.getScore());

        score.ajouter(50);
        assertEquals(150, score.getScore());
    }

    @Test
    public void testResetScore() {
        score.ajouter(200);
        assertEquals(200, score.getScore());

        score.reset();
        assertEquals(0, score.getScore());
    }

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
