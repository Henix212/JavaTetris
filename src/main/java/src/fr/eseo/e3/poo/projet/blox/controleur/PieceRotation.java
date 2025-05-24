package src.fr.eseo.e3.poo.projet.blox.controleur;

import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
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
        verifierEtTourner(e);
        vuePuits.repaint();
    }

    private void verifierEtTourner(MouseEvent e) {
        if (puits.getPieceActuelle() != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                try {
                    puits.getPieceActuelle().tourner(true);
                } catch (BloxException e1) {
                    if ("Sortie du puits après rotation".equals(e1.getMessage())) {
                        boolean rotationOk = false;
                        int abscisseInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse();
                        int ordonneeInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee();

                        // On tente de décaler la pièce à gauche ou à droite, 1 ou 2 cases max
                        for (int decalage = 1; decalage <= 2 && !rotationOk; decalage++) {
                            for (int sens : new int[]{-1, 1}) {
                                boolean deplacementPossible = true;
                                // Déplacement case par case
                                for (int i = 0; i < decalage; i++) {
                                    try {
                                        puits.getPieceActuelle().deplacerDe(sens, 0);
                                    } catch (Exception ex) {
                                        for (int j = 0; j < i; j++) {
                                            puits.getPieceActuelle().setPosition(abscisseInit, ordonneeInit);
                                        }
                                        deplacementPossible = false;
                                        break;
                                    }
                                }
                                if (deplacementPossible) {
                                    try {
                                        puits.getPieceActuelle().tourner(true);
                                        rotationOk = true;
                                        break;
                                    } catch (BloxException ex) {
                                        // Annule le déplacement si la rotation échoue
                                        for (int i = 0; i < decalage; i++) {
                                            puits.getPieceActuelle().setPosition(abscisseInit, ordonneeInit);
                                        }
                                    }
                                }
                            }
                        }
                        if (!rotationOk) {
                            // On remet la pièce à sa position initiale si tout a échoué
                            puits.getPieceActuelle().setPosition(abscisseInit, ordonneeInit);
                            System.out.println("Rotation impossible même après décalage.");
                        }
                    }
                }
            } else if (SwingUtilities.isRightMouseButton(e)) {
                try {
                    puits.getPieceActuelle().tourner(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
