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

public class VueJeux extends JPanel {

    private Puits puits;
    private Tas tas;
    private VuePuits vuePuits;
    private final JLabel scoreLabel;
    private VuePieceSuivante vuePieceSuivante;

    public VueJeux() {
        this.puits = new Puits();
        this.tas = new Tas(puits);
        puits.setTas(tas);

        UsineDePiece.setMode(0);
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);

        this.vuePuits = new VuePuits(puits, 30);

        scoreLabel = new JLabel("Score : " + Globals.score.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        this.vuePieceSuivante = new VuePieceSuivante(puits);

        JPanel panneauPieceSuivante = new JPanel(new BorderLayout());
        panneauPieceSuivante.setOpaque(false);
        panneauPieceSuivante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panneauPieceSuivante.add(vuePieceSuivante, BorderLayout.CENTER);

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

        vuePuits.setFocusable(true);
        vuePuits.requestFocusInWindow();

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

    public void updateScore() {
        scoreLabel.setText("Score : " + Globals.score.getScore());
    }

    public Puits getPuits() {
        return puits;
    }

    public Tas getTas() {
        return tas;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public void updateNextPiece() {
        vuePieceSuivante.repaint();
    }

    public void gameReset() {
        Globals.score.reset();
        
        puits.reset();

        UsineDePiece.setMode(0);
        Piece piece1 = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        this.tas = puits.getTas();

        vuePuits.arreterGravite();
        this.vuePuits = new VuePuits(puits, 30);

        this.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panneauPieceSuivante = new JPanel(new BorderLayout());
        panneauPieceSuivante.setOpaque(false);
        panneauPieceSuivante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panneauPieceSuivante.add(vuePieceSuivante, BorderLayout.CENTER);

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

        vuePuits.demarrerGravite();
        vuePuits.setFocusable(true);
        SwingUtilities.invokeLater(() -> vuePuits.requestFocusInWindow());

        updateScore();
        updateNextPiece();

        this.revalidate();
        this.repaint();

        vuePieceSuivante.repaint();
    }
}
