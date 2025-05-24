package test.fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import src.fr.eseo.e3.poo.projet.blox.modele.Couleur;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class PuitsTest {

    private Puits puits;

    @BeforeEach
    void setUp() {
        puits = new Puits();
    }

    @Test
    @DisplayName("Test du constructeur par défaut")
    void testDefaultConstructor() {
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, puits.getLargeur(), "La largeur par défaut devrait être correcte");
        assertEquals(Puits.PROFONDEUR_PAR_DEFAUT, puits.getProfondeur(), "La profondeur par défaut devrait être correcte");
        assertNull(puits.getPieceActuelle(), "La pièce actuelle devrait être null par défaut");
        assertNull(puits.getPieceSuivante(), "La pièce suivante devrait être null par défaut");
    }

    @Test
    @DisplayName("Test du constructeur avec paramètres")
    void testParameterizedConstructor() {
        Puits puitsParam = new Puits(8, 18);
        assertEquals(8, puitsParam.getLargeur(), "La largeur devrait être 8");
        assertEquals(18, puitsParam.getProfondeur(), "La profondeur devrait être 18");
    }

    @Test
    @DisplayName("Test des limites de largeur")
    void testSetLargeurLimits() {
        assertThrows(IllegalArgumentException.class, () -> puits.setLargeur(4), "La largeur inférieure à 5 devrait lever une exception");
        assertThrows(IllegalArgumentException.class, () -> puits.setLargeur(16), "La largeur supérieure à 15 devrait lever une exception");
        puits.setLargeur(10);
        assertEquals(10, puits.getLargeur(), "La largeur devrait être mise à jour correctement");
    }

    @Test
    @DisplayName("Test des limites de profondeur")
    void testSetProfondeurLimits() {
        assertThrows(IllegalArgumentException.class, () -> puits.setProfondeur(14), "La profondeur inférieure à 15 devrait lever une exception");
        assertThrows(IllegalArgumentException.class, () -> puits.setProfondeur(26), "La profondeur supérieure à 25 devrait lever une exception");
        puits.setProfondeur(20);
        assertEquals(20, puits.getProfondeur(), "La profondeur devrait être mise à jour correctement");
    }

    @Test
    @DisplayName("Test de la méthode setPieceSuivante avec transition")
    void testSetPieceSuivanteWithTransition() {
        OTetromino piece1 = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        ITetromino piece2 = new ITetromino(new Coordonnees(0, 0), Couleur.BLEU);

        puits.setPieceSuivante(piece1);
        assertEquals(piece1, puits.getPieceSuivante(), "La pièce suivante devrait être mise à jour correctement");
        assertNull(puits.getPieceActuelle(), "La pièce actuelle devrait être null avant la transition");

        puits.setPieceSuivante(piece2);
        assertEquals(piece1, puits.getPieceActuelle(), "La pièce actuelle devrait être mise à jour après la transition");
        assertEquals(piece2, puits.getPieceSuivante(), "La nouvelle pièce suivante devrait être mise à jour correctement");
        assertEquals((puits.getLargeur() / 2), piece1.getElements().get(0).getCoordonnees().getAbscisse(), "La pièce actuelle devrait être centrée horizontalement");
        assertEquals(4, piece1.getElements().get(0).getCoordonnees().getOrdonnee(), "La pièce actuelle devrait être positionnée en haut du puits");
    }

    @Test
    @DisplayName("Test des événements PropertyChange pour les pièces")
    void testPropertyChangeEvents() {
        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
                    assertNotNull(evt.getNewValue(), "La nouvelle pièce actuelle ne devrait pas être null");
                } else if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_SUIVANTE)) {
                    assertNotNull(evt.getNewValue(), "La nouvelle pièce suivante ne devrait pas être null");
                }
            }
        };

        puits.addPropertyChangeListener(listener);

        Piece piece1 = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        Piece piece2 = new ITetromino(new Coordonnees(0, 0), Couleur.BLEU);

        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        puits.removePropertyChangeListener(listener);
    }

    @Test
    @DisplayName("Test de la méthode toString")
    void testToString() {
        String expected = "Puits : Dimension 10 x 20\nPiece Suivante : <aucune>\nPiece Actuelle : <aucune>\n";
        String expectedWithPieces = "Puits : Dimension 10 x 20\n" +
                "Piece Suivante : OTetromino\n" +
                "OTetromino :\n" +
                "\t(0, 0) - ROUGE\n" +
                "\t(1, 0) - ROUGE\n" +
                "\t(0, -1) - ROUGE\n" +
                "\t(1, -1) - ROUGE\n" +
                "Piece Actuelle : <aucune>\n";
        String expectedWithPieces2 = "Puits : Dimension 10 x 20\n" +
                "Piece Suivante : OTetromino\n" +
                "OTetromino :\n" +
                "\t(0, 0) - ROUGE\n" +
                "\t(1, 0) - ROUGE\n" +
                "\t(0, -1) - ROUGE\n" +
                "\t(1, -1) - ROUGE\n" +
                "Piece Actuelle : OTetromino\n"+
                "OTetromino :\n" +
                "\t(5, 4) - ROUGE\n" +
                "\t(6, 4) - ROUGE\n" +
                "\t(5, 3) - ROUGE\n" +
                "\t(6, 3) - ROUGE\n";
        

        OTetromino piece = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        OTetromino piece2 = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);

        assertEquals(expected, puits.toString(), "La méthode toString devrait retourner la chaîne attendue");

        
        puits.setPieceSuivante(piece);
        assertEquals(expectedWithPieces, puits.toString(), "La méthode toString devrait retourner la chaîne attendue avec les pièces");

        puits.setPieceSuivante(piece2);
        assertEquals(expectedWithPieces2, puits.toString(), "La méthode toString devrait retourner la chaîne attendue avec les pièces");
    }

    @Test
    @DisplayName("Test des getters et setters pour le tas")
    void testTas() {
        Tas tas = new Tas(puits); // Remplacez par une implémentation valide de Tas
        puits.setTas(tas);
        assertEquals(tas, puits.getTas(), "Le tas devrait être correctement défini et récupéré");
    }
}
