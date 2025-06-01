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
 * Ce test s'assure que la pièce "fantôme" est bien générée (via clone + descente)
 * et que sa couleur est bien rendue avec transparence.
 */
public class VueGhostTest {

    /** Pièce d'origine utilisée pour générer la ghost piece. */
    private Piece piece;
    /** VueGhost à tester, créée à partir de la pièce d'origine. */
    private VueGhost vueGhost;
    /** Puits de jeu utilisé pour placer la pièce. */
    private Puits puits;

    /**
     * Initialise un puits avec une pièce ITetromino et une instance VueGhost avant chaque test.
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
     * Teste que la VueGhost contient bien une pièce fantôme distincte de l'originale.
     * <p>
     * Utilise la réflexion pour accéder à l'attribut protégé "piece" dans VuePiece.
     * </p>
     * 
     * Le type {@link java.lang.reflect.Field Field} de Java permet d'accéder à des champs privés ou protégés
     * d'une classe par réflexion. Voir :
     * <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Field.html">Java Field API</a>
     *
     * @throws Exception en cas d'accès/reflexion impossible
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
     * Vérifie que la couleur d'affichage de la pièce ghost est correcte (même RGB + alpha 80).
     * <p>
     * Utilise la réflexion pour appeler la méthode protégée getCouleurAffichage.
     * </p>
     *
     * @throws Exception en cas d'accès/reflexion impossible
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