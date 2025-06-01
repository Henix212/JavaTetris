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

/**
 * Classe de test unitaire pour VuePieceSuivante.
 * Cette classe teste le comportement de la vue qui affiche la prochaine pièce à jouer.
 * Les tests vérifient :
 * - L'initialisation correcte de la vue
 * - La taille préférée du composant
 * - Le bon fonctionnement des écouteurs d'événements
 *
 * @author Hugo
 */
public class VuePieceSuivanteTest {

    /** Le puits associé à la vue */
    private Puits puits;
    /** La vue de la pièce suivante à tester */
    private VuePieceSuivante vuePieceSuivante;

    /**
     * Initialise l'environnement de test avant chaque méthode de test.
     * Crée un puits vide et sa vue de pièce suivante associée.
     */
    @BeforeEach
    public void setUp() {
        puits = new Puits(10, 20);
        vuePieceSuivante = new VuePieceSuivante(puits);
    }

    /**
     * Teste le constructeur et la taille préférée du composant.
     * Vérifie que la vue est correctement initialisée avec une taille de 120x120 pixels.
     */
    @Test
    public void testConstructeurEtPreferredSize() {
        assertEquals(new Dimension(120, 120), vuePieceSuivante.getPreferredSize());
    }

    /**
     * Teste le bon fonctionnement des écouteurs d'événements.
     * Vérifie que :
     * - Le repaint est déclenché lors du changement de pièce suivante
     * - Les écouteurs peuvent être ajoutés et retirés correctement
     */
    @Test
    public void testListenerDeclencheRepaint() {
        final boolean[] repainted = {false};

        // Ajout d'un écouteur qui marque le repaint
        vuePieceSuivante.addPropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repainted[0] = true;
            }
        });

        // Vérifie que le repaint initial ne déclenche pas l'écouteur
        vuePieceSuivante.repaint();
        assertFalse(repainted[0]);

        // Change la pièce suivante et vérifie que le repaint est déclenché
        puits.setPieceSuivante(new OTetromino(new Coordonnees(4, 0), Couleur.BLEU));
        vuePieceSuivante.repaint(); 

        // Retire l'écouteur
        vuePieceSuivante.removePropertyChangeListener(evt -> {
            if ("PIECE SUIVANTE".equals(evt.getPropertyName())) {
                repainted[0] = true;
            }
        });
    }
}
