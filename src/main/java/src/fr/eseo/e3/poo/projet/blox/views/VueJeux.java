package src.fr.eseo.e3.poo.projet.blox.views;

import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePieceSuivante;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

import javax.swing.*;
import java.awt.*;

/**
 * Vue principale du jeu Tetris.
 * Cette classe gère l'interface graphique du jeu, incluant :
 * - Le puits de jeu
 * - L'affichage de la pièce suivante
 * - Le score
 * - La gestion des événements et mises à jour
 *
 * @author Hugo
 */
public class VueJeux extends JPanel {

    /** Le puits de jeu contenant les pièces et le tas */
    private Puits puits;
    
    /** Le tas d'éléments empilés dans le puits */
    private Tas tas;
    
    /** La vue du puits de jeu */
    private VuePuits vuePuits;
    
    /** Label affichant le score actuel */
    private final JLabel scoreLabel;
    
    /** Vue de la prochaine pièce à jouer */
    private VuePieceSuivante vuePieceSuivante;

    /**
     * Constructeur de la vue de jeu.
     * Initialise tous les composants du jeu et configure l'interface.
     */
    public VueJeux() {
        // Initialisation du puits et du tas
        this.puits = new Puits();
        this.tas = new Tas(puits);
        puits.setTas(tas);

        // Configuration de la génération des pièces et création des premières pièces
        UsineDePiece.setMode(0);
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);

        // Création de la vue du puits
        this.vuePuits = new VuePuits(puits, 30);

        // Configuration du label de score
        scoreLabel = new JLabel("Score : " + Globals.score.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);

        // Configuration du layout
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        // Création et configuration de la vue de la pièce suivante
        this.vuePieceSuivante = new VuePieceSuivante(puits);

        JPanel panneauPieceSuivante = new JPanel(new BorderLayout());
        panneauPieceSuivante.setOpaque(false);
        panneauPieceSuivante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panneauPieceSuivante.add(vuePieceSuivante, BorderLayout.CENTER);

        // Ajout des composants à l'interface
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(panneauPieceSuivante, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scoreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(vuePuits, gbc);

        // Configuration du focus
        vuePuits.setFocusable(true);
        vuePuits.requestFocusInWindow();

        // Ajout des écouteurs d'événements
        Globals.score.addPropertyChangeListener(event -> {
            if ("SCORE".equals(event.getPropertyName())) {
                updateScore();
            }
        });

        vuePieceSuivante.addPropertyChangeListener(event -> {
            if ("PIECE SUIVANTE".equals(event.getPropertyName())) {
                updateNextPiece();
            }
        });
    }

    /**
     * Met à jour l'affichage du score.
     */
    public void updateScore() {
        scoreLabel.setText("Score : " + Globals.score.getScore());
    }

    /**
     * Retourne le puits de jeu.
     *
     * @return Le puits de jeu
     */
    public Puits getPuits() {
        return puits;
    }

    /**
     * Retourne le tas d'éléments.
     *
     * @return Le tas d'éléments
     */
    public Tas getTas() {
        return tas;
    }

    /**
     * Retourne la vue du puits.
     *
     * @return La vue du puits
     */
    public VuePuits getVuePuits() {
        return vuePuits;
    }

    /**
     * Met à jour l'affichage de la prochaine pièce.
     */
    public void updateNextPiece() {
        vuePieceSuivante.repaint();
    }

    /**
     * Réinitialise le jeu.
     * Cette méthode :
     * - Réinitialise le score
     * - Réinitialise le puits
     * - Génère de nouvelles pièces
     * - Recrée la vue du puits
     * - Met à jour l'interface
     */
    public void gameReset() {
        // Réinitialisation du score
        Globals.score.reset();
        
        // Réinitialisation du puits
        puits.reset();

        // Génération de nouvelles pièces
        UsineDePiece.setMode(0);
        Piece piece1 = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        // Mise à jour du tas
        this.tas = puits.getTas();

        // Recréation de la vue du puits
        vuePuits.arreterGravite();
        this.vuePuits = new VuePuits(puits, 30);

        // Réorganisation de l'interface
        this.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();

        // Recréation du panneau de la pièce suivante
        JPanel panneauPieceSuivante = new JPanel(new BorderLayout());
        panneauPieceSuivante.setOpaque(false);
        panneauPieceSuivante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panneauPieceSuivante.add(vuePieceSuivante, BorderLayout.CENTER);

        // Réajout des composants
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(panneauPieceSuivante, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(scoreLabel, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        this.add(vuePuits, gbc);

        // Redémarrage de la gravité et configuration du focus
        vuePuits.demarrerGravite();
        vuePuits.setFocusable(true);
        SwingUtilities.invokeLater(() -> vuePuits.requestFocusInWindow());

        // Mise à jour des affichages
        updateScore();
        updateNextPiece();

        // Rafraîchissement de l'interface
        this.revalidate();
        this.repaint();

        vuePieceSuivante.repaint();
    }
}
