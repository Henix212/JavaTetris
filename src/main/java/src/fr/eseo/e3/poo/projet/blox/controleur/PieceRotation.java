package src.fr.eseo.e3.poo.projet.blox.controleur;

import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class PieceRotation extends ControllAdaptateur {
    private VuePuits vuePuits;
    private Puits puits;

    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (puits.getPieceActuelle() != null) {
            if (SwingUtilities.isLeftMouseButton(e)){
                try {
                    puits.getPieceActuelle().tourner(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else if (SwingUtilities.isRightMouseButton(e)){
                try {
                    puits.getPieceActuelle().tourner(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }

        vuePuits.repaint();
    }
}
