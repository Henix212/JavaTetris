package test.fr.eseo.e3.poo.projet.blox.utils;

import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.modele.Score;

import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalsTest {

    @Test
    public void testStaticsNotNull() {
        assertNotNull(Globals.frame, "Le JFrame doit être instancié");
        assertTrue(Globals.frame instanceof JFrame, "frame doit être une instance de JFrame");

        assertNotNull(Globals.routeur, "Le routeur doit être instancié");
        assertTrue(Globals.routeur instanceof Routeur, "routeur doit être une instance de Routeur");

        assertNotNull(Globals.score, "Le score doit être instancié");
        assertTrue(Globals.score instanceof Score, "score doit être une instance de Score");
    }
}