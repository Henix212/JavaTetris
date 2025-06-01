package test.fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;

public class BloxExceptionTest {

    @Test
    public void testConstructeurEtGetType_Collision() {
        String message = "Collision détectée";
        int type = BloxException.BLOX_COLLISION;

        BloxException exception = new BloxException(message, type);

        assertEquals(message, exception.getMessage());
        assertEquals(type, exception.getType());
    }

    @Test
    public void testConstructeurEtGetType_Sortie() {
        String message = "Sortie du puits";
        int type = BloxException.BLOX_SORTIE_PUITS;

        BloxException exception = new BloxException(message, type);

        assertEquals(message, exception.getMessage());
        assertEquals(type, exception.getType());
    }
}