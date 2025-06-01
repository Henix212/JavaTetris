package test.fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;

/**
 * Classe de test unitaire pour BloxException.
 * Cette classe teste le comportement des exceptions personnalisées du jeu Tetris.
 * Les tests vérifient :
 * - La création d'exceptions de collision
 * - La création d'exceptions de sortie de puits
 * - La récupération du message et du type d'exception
 *
 * @author Hugo
 */
public class BloxExceptionTest {

    /**
     * Teste la création d'une exception de collision.
     * Vérifie que :
     * - Le message d'erreur est correctement défini
     * - Le type d'exception est correctement défini
     * - Les getters retournent les bonnes valeurs
     */
    @Test
    public void testConstructeurEtGetType_Collision() {
        String message = "Collision détectée";
        int type = BloxException.BLOX_COLLISION;

        BloxException exception = new BloxException(message, type);

        assertEquals(message, exception.getMessage());
        assertEquals(type, exception.getType());
    }

    /**
     * Teste la création d'une exception de sortie de puits.
     * Vérifie que :
     * - Le message d'erreur est correctement défini
     * - Le type d'exception est correctement défini
     * - Les getters retournent les bonnes valeurs
     */
    @Test
    public void testConstructeurEtGetType_Sortie() {
        String message = "Sortie du puits";
        int type = BloxException.BLOX_SORTIE_PUITS;

        BloxException exception = new BloxException(message, type);

        assertEquals(message, exception.getMessage());
        assertEquals(type, exception.getType());
    }
}