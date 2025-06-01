package src.fr.eseo.e3.poo.projet.blox.controleur;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PieceDeplacement extends ControllAdaptateur  {
    private VuePuits vuePuits;
    private Puits puits;

    public PieceDeplacement(VuePuits vuePuits, Puits puits) {
        this.vuePuits = vuePuits;
        this.puits = puits;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event){
        if (this.puits.getPieceActuelle() == null){
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }

        if (event.getWheelRotation() > 0){
            try {
                this.puits.getPieceActuelle().deplacerDe(0,1);
            } catch (BloxException e) {
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
                return; 
            }
        }

        this.vuePuits.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        if (this.puits.getPieceActuelle() == null) {
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }

        int cibleCol = (int) (event.getX() / this.vuePuits.getTaille());
        int colActuelle = this.puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse();

        int delta = cibleCol - colActuelle;
        int direction = (delta > 0) ? 1 : -1;

        while (delta != 0) {
            try {
                this.puits.getPieceActuelle().deplacerDe(direction, 0);
                delta -= direction;
            } catch (BloxException e) {
                break; 
            }
        }
        this.vuePuits.repaint();
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (this.puits.getPieceActuelle() == null) {
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }
        
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) {
            try { puits.getPieceActuelle().deplacerDe(-1, 0); } catch (Exception ex) {}
            vuePuits.repaint();
        }
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) {
            try { puits.getPieceActuelle().deplacerDe(1, 0); } catch (Exception ex) {}
            vuePuits.repaint();
        }
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            try { 
                puits.getPieceActuelle().deplacerDe(0, 1); 
            } catch (Exception ex) {
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
            }
            vuePuits.repaint();
        }
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            try {
                while (true) {
                    puits.getPieceActuelle().deplacerDe(0, 1);
                }
            } catch (Exception ex) {
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
            }
            vuePuits.repaint();
        }
    }
}
