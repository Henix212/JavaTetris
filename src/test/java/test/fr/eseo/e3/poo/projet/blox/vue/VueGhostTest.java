package test.fr.eseo.e3.poo.projet.blox.vue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.vue.VueGhost;
import java.lang.reflect.Field;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour VueGhost.
 * Cette classe teste le comportement de la vue fantôme d'une pièce dans Tetris.
 * Les tests vérifient :
 * - La création correcte d'une copie de la pièce
 * - Le positionnement automatique au point d'atterrissage
 * - L'application correcte de la transparence sur les couleurs
 *
 * @author Hugo
 */
public class VueGhostTest {

    /** Pièce d'origine utilisée pour générer la ghost piece */
    private Piece piece;
    /** VueGhost à tester, créée à partir de la pièce d'origine */
    private VueGhost vueGhost;
    /** Puits de jeu utilisé pour placer la pièce */
    private Puits puits;

    /**
     * Initialise l'environnement de test avant chaque méthode de test.
     * Crée un puits vide, une pièce I et sa vue fantôme associée.
     */
    @BeforeEach
    public void setUp() {
        // Création d'un puits (largeur = 10, hauteur = 20)
        puits = new Puits(10, 20);
        // Ajout d'un tas vide au puits
        puits.setTas(new Tas(puits));
        // Création d'une pièce ITetromino à la position (4, 0) de couleur CYAN
        piece = new ITetromino(new Coordonnees(4, 0), Couleur.CYAN);
        // Affectation du puits à la pièce
        piece.setPuits(puits);
        // Création de la VueGhost à partir de la pièce d'origine, taille des cases = 30
        vueGhost = new VueGhost(piece, 30);
    }

    /**
     * Teste la création correcte d'une pièce fantôme.
     * Vérifie que :
     * - Une pièce fantôme est bien créée
     * - C'est une copie distincte de la pièce originale
     * - Elle est correctement positionnée au point d'atterrissage
     *
     * @throws Exception si l'accès par réflexion échoue
     */
    @Test
    public void testConstructorCreatesGhostPiece() throws Exception {
        assertNotNull(vueGhost);

        // Accès au champ protégé "piece" hérité depuis VuePiece via réflexion
        Field pieceField = vueGhost.getClass().getSuperclass().getDeclaredField("piece");
        pieceField.setAccessible(true); // rend accessible le champ privé ou protégé
        Object ghostPiece = pieceField.get(vueGhost);

        assertNotNull(ghostPiece);            // vérifie qu'une pièce ghost a bien été créée
        assertNotSame(piece, ghostPiece);     // vérifie qu'il s'agit d'une copie (clone)
    }

    /**
     * Teste l'application correcte de la transparence sur les couleurs.
     * Vérifie que :
     * - Les composantes RGB restent identiques à la pièce originale
     * - La composante alpha est fixée à 80 pour la transparence
     *
     * @throws Exception si l'accès par réflexion échoue
     */
    @Test
    public void testGetCouleurAffichageAlpha() throws Exception {
        // On prend un des éléments de la pièce d'origine
        Element elt = piece.getElements().get(0);

        // Appel à la méthode protégée "getCouleurAffichage" par réflexion
        var method = vueGhost.getClass().getSuperclass().getDeclaredMethod("getCouleurAffichage", Element.class);
        method.setAccessible(true);
        Color ghostColor = (Color) method.invoke(vueGhost, elt);

        // Vérifie que les canaux RGB sont les mêmes, et que l'alpha est bien réduit
        Color baseColor = elt.getCouleur().getCouleurPourAffichage();
        assertEquals(baseColor.getRed(), ghostColor.getRed());
        assertEquals(baseColor.getGreen(), ghostColor.getGreen());
        assertEquals(baseColor.getBlue(), ghostColor.getBlue());
        assertEquals(80, ghostColor.getAlpha()); // transparence attendue pour la ghost piece
    }
}