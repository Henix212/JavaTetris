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

public class VuePuits extends JPanel implements PropertyChangeListener {
    static int TAILLE_PAR_DEFAUT = 30;
    private int taille;
    private Puits puits;
    private VuePiece vuePiece;
    private PieceDeplacement pieceDeplacement;
    private PieceRotation pieceRotation;
    private Timer graviteTimer;

    public VuePuits(Puits puits) {
        this(puits,TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        this.puits = puits;
        this.taille = taille;
        this.puits.addPropertyChangeListener(this);
        this.pieceDeplacement = new PieceDeplacement(this, puits);
        this.pieceRotation = new PieceRotation(this); // Initialisation de pieceRotation
        this.addMouseListener(pieceDeplacement);
        this.addMouseMotionListener(pieceDeplacement);
        this.addMouseWheelListener(pieceDeplacement);
        this.addKeyListener(pieceDeplacement);
        this.addMouseListener(pieceRotation);
        this.addKeyListener(pieceRotation);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        float largeurCase = (float) this.getWidth() / puits.getLargeur();
        float hauteurCase = (float) this.getHeight() / puits.getProfondeur();

        for (int i = 0; i <= puits.getProfondeur(); i++) {
            int y = Math.round(i * hauteurCase);
            g2D.drawLine(0, y, this.getWidth(), y);
        }

        for (int i = 0; i <= puits.getLargeur(); i++) {
            int x = Math.round(i * largeurCase);
            g2D.drawLine(x, 0, x, this.getHeight());
        }

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

        Piece piece = puits.getPieceActuelle();
        if (piece != null) {
            VueGhost vueGhost = new VueGhost(piece, this.taille);
            vueGhost.afficherPiece(g2D, largeurCase, hauteurCase);

            VuePiece vuePiece = new VuePiece(piece, this.taille);
            vuePiece.afficherPiece(g2D, largeurCase, hauteurCase);
        }

        g2D.dispose();
    }

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

    public void setTaille(int taille) {
        this.taille = taille;
        setPreferredSize(new Dimension(taille * puits.getLargeur(),taille * puits.getProfondeur()));
    }

    public int getTaille(){
        return this.taille;
    }

    public void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
    }

    public VuePiece getVuePiece() {
        return this.vuePiece;
    }

    public Puits getPuits() {
        return puits;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if ("PIECE ACTUELLE".equals(event.getPropertyName())) {
            VuePiece vuePiece = new VuePiece(puits.getPieceActuelle(), 30);
            this.setVuePiece(vuePiece);
            repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // largeur = nbColonnes * taille, hauteur = nbLignes * taille
        return new Dimension(puits.getLargeur() * taille, puits.getProfondeur() * taille);
    }

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

    public void arreterGravite() {
        if (graviteTimer != null) {
            graviteTimer.stop();
        }
    }
}
