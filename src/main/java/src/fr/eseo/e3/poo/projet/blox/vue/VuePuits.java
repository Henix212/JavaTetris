package src.fr.eseo.e3.poo.projet.blox.vue;

import src.fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import src.fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Vue graphique du puits de Tetris.
 * Cette classe gère l'affichage du puits, des pièces en cours et du tas,
 * ainsi que les interactions utilisateur et la gravité.
 *
 * @author Hugo
 */
public class VuePuits extends JPanel implements PropertyChangeListener {
    /** Taille par défaut des cases du puits en pixels */
    static int TAILLE_PAR_DEFAUT = 30;
    /** Taille actuelle des cases du puits en pixels */
    private int taille;
    /** Le puits associé à cette vue */
    private Puits puits;
    /** Vue de la pièce actuelle */
    private VuePiece vuePiece;
    /** Contrôleur pour le déplacement des pièces */
    private PieceDeplacement pieceDeplacement;
    /** Contrôleur pour la rotation des pièces */
    private PieceRotation pieceRotation;
    /** Timer pour gérer la gravité des pièces */
    private Timer graviteTimer;

    /**
     * Constructeur avec taille par défaut.
     *
     * @param puits Le puits à afficher
     */
    public VuePuits(Puits puits) {
        this(puits,TAILLE_PAR_DEFAUT);
    }

    /**
     * Constructeur avec taille personnalisée.
     * Initialise les contrôleurs et configure les écouteurs d'événements.
     *
     * @param puits Le puits à afficher
     * @param taille La taille des cases en pixels
     */
    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.taille = taille;
        this.puits.addPropertyChangeListener(this);
        this.pieceDeplacement = new PieceDeplacement(this, puits);
        this.pieceRotation = new PieceRotation(this);
        this.addMouseListener(pieceDeplacement);
        this.addMouseMotionListener(pieceDeplacement);
        this.addMouseWheelListener(pieceDeplacement);
        this.addKeyListener(pieceDeplacement);
        this.addMouseListener(pieceRotation);
        this.addKeyListener(pieceRotation);
    }

    /**
     * Dessine le contenu du puits.
     * Affiche la grille, le tas et la pièce actuelle avec son fantôme.
     *
     * @param g Le contexte graphique pour le dessin
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        // Calcul des dimensions des cases
        float largeurCase = (float) this.getWidth() / puits.getLargeur();
        float hauteurCase = (float) this.getHeight() / puits.getProfondeur();

        // Dessin de la grille
        for (int i = 0; i <= puits.getProfondeur(); i++) {
            int y = Math.round(i * hauteurCase);
            g2D.drawLine(0, y, this.getWidth(), y);
        }

        for (int i = 0; i <= puits.getLargeur(); i++) {
            int x = Math.round(i * largeurCase);
            g2D.drawLine(x, 0, x, this.getHeight());
        }

        // Dessin du tas
        for (Element elt : puits.getTas().getElements()) {
            int x = Math.round(elt.getCoordonnees().getAbscisse() * largeurCase);
            int y = Math.round(elt.getCoordonnees().getOrdonnee() * hauteurCase);
            int w = Math.round(largeurCase);
            int h = Math.round(hauteurCase);

            g2D.setColor(elt.getCouleur().getCouleurPourAffichage());
            g2D.fill3DRect(x, y, w, h, false);

            g2D.setColor(Color.BLACK);
            g2D.drawRect(x, y, w, h);
        }

        // Dessin de la pièce actuelle et son fantôme
        Piece piece = puits.getPieceActuelle();
        if (piece != null) {
            VueGhost vueGhost = new VueGhost(piece, this.taille);
            vueGhost.afficherPiece(g2D, largeurCase, hauteurCase);

            VuePiece vuePiece = new VuePiece(piece, this.taille);
            vuePiece.afficherPiece(g2D, largeurCase, hauteurCase);
        }

        g2D.dispose();
    }

    /**
     * Change le puits associé à cette vue.
     * Réinitialise les écouteurs d'événements.
     *
     * @param puits Le nouveau puits
     */
    public void setPuits(Puits puits) {
        if (this.puits != null) {
            this.puits.removePropertyChangeListener(this);
            this.removeMouseMotionListener(pieceDeplacement);
            this.removeMouseWheelListener(pieceDeplacement);
            this.removeMouseListener(pieceRotation);
            this.removeKeyListener(pieceDeplacement);
            this.removeKeyListener(pieceRotation);
        }
        this.puits = puits;
        this.puits.addPropertyChangeListener(this);
        this.addMouseListener(pieceDeplacement);
        this.addMouseMotionListener(pieceDeplacement);
        this.addMouseWheelListener(pieceDeplacement);
        this.addMouseListener(pieceRotation);
        this.addKeyListener(pieceDeplacement);
        this.addKeyListener(pieceRotation);
    }

    /**
     * Modifie la taille des cases du puits.
     *
     * @param taille La nouvelle taille en pixels
     */
    public void setTaille(int taille) {
        this.taille = taille;
        setPreferredSize(new Dimension(taille * puits.getLargeur(),taille * puits.getProfondeur()));
    }

    /**
     * Retourne la taille actuelle des cases.
     *
     * @return La taille en pixels
     */
    public int getTaille(){
        return this.taille;
    }

    /**
     * Définit la vue de la pièce actuelle.
     *
     * @param vuePiece La nouvelle vue de pièce
     */
    public void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
    }

    /**
     * Retourne la vue de la pièce actuelle.
     *
     * @return La vue de pièce
     */
    public VuePiece getVuePiece() {
        return this.vuePiece;
    }

    /**
     * Retourne le puits associé à cette vue.
     *
     * @return Le puits
     */
    public Puits getPuits() {
        return puits;
    }

    /**
     * Gère les changements de propriété du puits.
     * Met à jour la vue quand la pièce actuelle change.
     *
     * @param event L'événement de changement
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("PIECE ACTUELLE".equals(event.getPropertyName())) {
            VuePiece vuePiece = new VuePiece(puits.getPieceActuelle(), 30);
            this.setVuePiece(vuePiece);
            repaint();
        }
    }

    /**
     * Retourne la taille préférée du panneau.
     *
     * @return La dimension préférée
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(puits.getLargeur() * taille, puits.getProfondeur() * taille);
    }

    /**
     * Démarre la gravité des pièces.
     * Crée un timer qui fait descendre la pièce actuelle toutes les 850ms.
     */
    public void demarrerGravite() {
        if (graviteTimer != null && graviteTimer.isRunning()) {
            graviteTimer.stop();
        }
        graviteTimer = new Timer(850, new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (puits.getPieceActuelle() != null) {
                        puits.getPieceActuelle().deplacerDe(0, 1);
                        repaint();
                    }
                } catch (Exception ex) {
                    puits.getTas().ajouterElements(puits.getPieceActuelle());
                    puits.getTas().supprimerLignesCompletes();
                    puits.setPieceSuivante(UsineDePiece.genererTetromino());
                }
            }
        });
        graviteTimer.start();
    }

    /**
     * Arrête la gravité des pièces.
     */
    public void arreterGravite() {
        if (graviteTimer != null) {
            graviteTimer.stop();
        }
    }
}
