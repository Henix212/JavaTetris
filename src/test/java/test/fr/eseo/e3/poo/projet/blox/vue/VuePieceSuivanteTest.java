package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePieceSuivante;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class VuePieceSuivanteTest {

    private Puits puits;
    private VuePieceSuivante vuePieceSuivante;

    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        vuePieceSuivante = new VuePieceSuivante(puits);
    }

    @Test
    public void testConstructeurEtPreferredSize() {
        assertEquals(new Dimension(120, 120), vuePieceSuivante.getPreferredSize());
    }

    @Test
    public void testListenerDeclencheRepaint() {
        final boolean[] repainted = {false};

        vuePieceSuivante.addPropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repainted[0] = true;
            }
        });

        vuePieceSuivante.repaint();
        assertFalse(repainted[0]);

        puits.setPieceSuivante(new OTetromino(new Coordonnees(4, 0), Couleur.BLEU));
        vuePieceSuivante.repaint(); 

        vuePieceSuivante.removePropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repainted[0] = true;
            }
        });
    }
}
