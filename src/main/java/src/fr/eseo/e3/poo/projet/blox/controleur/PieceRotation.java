package src.fr.eseo.e3.poo.projet.blox.controleur;

import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Contrôleur responsable de la gestion des rotations des pièces dans le jeu.
 * Cette classe hérite de ControllAdaptateur et implémente les mécanismes de rotation
 * via la souris et le clavier.
 * 
 * <p>Fonctionnalités principales :
 * <ul>
 *     <li>Rotation horaire avec clic gauche ou flèche haut</li>
 *     <li>Rotation anti-horaire avec clic droit</li>
 *     <li>Gestion intelligente des collisions lors de la rotation</li>
 *     <li>Tentative de déplacement automatique si la rotation directe n'est pas possible</li>
 * </ul>
 * </p>
 *
 * @author Hugo
 */
public class PieceRotation extends ControllAdaptateur {
    /** La vue du puits de jeu associée à ce contrôleur */
    private VuePuits vuePuits;
    
    /** Le puits de jeu sur lequel les rotations sont effectuées */
    private Puits puits;

    /**
     * Constructeur de la classe PieceRotation.
     * Initialise le contrôleur avec la vue du puits spécifiée.
     *
     * @param vuePuits La vue du puits de jeu à laquelle ce contrôleur est associé
     * @throws NullPointerException Si vuePuits est null
     */
    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    /**
     * Gère les événements de clic de souris pour la rotation des pièces.
     * <ul>
     *     <li>Clic gauche : rotation horaire</li>
     *     <li>Clic droit : rotation anti-horaire</li>
     * </ul>
     * La vue est rafraîchie après chaque rotation.
     *
     * @param e L'événement de souris déclenché
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        verifierEtTournerMouse(e);
        vuePuits.repaint();
    }

    /**
     * Gère les événements de pression de touche pour la rotation des pièces.
     * La flèche haut déclenche une rotation horaire.
     * La touche C échange la pièce actuelle avec la pièce en réserve.
     * La vue est rafraîchie après chaque rotation.
     *
     * @param e L'événement de clavier déclenché
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            verifierEtTournerKey(e);
            vuePuits.repaint();
        } else if (e.getKeyCode() == java.awt.event.KeyEvent.VK_C) {
            puits.echangerPieceReserve();
            vuePuits.repaint();
        }
    }

    /**
     * Vérifie et effectue la rotation de la pièce suite à un clic de souris.
     * Si la rotation directe n'est pas possible, tente de déplacer la pièce
     * jusqu'à 2 cases à gauche ou à droite pour permettre la rotation.
     *
     * <p>Processus de rotation :
     * <ol>
     *     <li>Tente une rotation directe</li>
     *     <li>Si échec, essaie de déplacer la pièce jusqu'à 2 cases</li>
     *     <li>Annule les déplacements si la rotation reste impossible</li>
     * </ol>
     * </p>
     *
     * @param e L'événement de souris déclenché
     */
    private void verifierEtTournerMouse(MouseEvent e) {
        if (puits.getPieceActuelle() != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                try {
                    puits.getPieceActuelle().tourner(true);
                } catch (BloxException e1) {
                    if ("Sortie du puits après rotation".equals(e1.getMessage())) {
                        boolean rotationOk = false;
                        int abscisseInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse();
                        int ordonneeInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee();

                        // Tente de déplacer la pièce jusqu'à 2 cases à gauche ou à droite pour permettre la rotation
                        for (int decalage = 1; decalage <= 2 && !rotationOk; decalage++) {
                            for (int sens : new int[] { -1, 1 }) {
                                boolean deplacementPossible = true;
                                // Déplace la pièce case par case
                                for (int i = 0; i < decalage; i++) {
                                    try {
                                        puits.getPieceActuelle().deplacerDe(sens, 0);
                                    } catch (Exception ex) {
                                        // Annule les déplacements si une erreur survient
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
                            // Retourne la pièce à sa position initiale si aucune rotation n'est possible
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

    /**
     * Vérifie et effectue la rotation de la pièce suite à l'appui sur la flèche haut.
     * Utilise la même logique que verifierEtTournerMouse pour la gestion des rotations
     * et des déplacements compensatoires.
     *
     * <p>Cette méthode implémente le même comportement que verifierEtTournerMouse
     * mais est déclenchée par un événement clavier plutôt que par un clic de souris.</p>
     *
     * @param e L'événement de clavier déclenché
     */
    private void verifierEtTournerKey(java.awt.event.KeyEvent e) {
        if (puits.getPieceActuelle() != null) {
            try {
                puits.getPieceActuelle().tourner(true);
            } catch (BloxException e1) {
                if ("Sortie du puits après rotation".equals(e1.getMessage())) {
                    boolean rotationOk = false;
                    int abscisseInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse();
                    int ordonneeInit = puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee();

                    // Tente de déplacer la pièce jusqu'à 2 cases à gauche ou à droite pour permettre la rotation
                    for (int decalage = 1; decalage <= 2 && !rotationOk; decalage++) {
                        for (int sens : new int[] { -1, 1 }) {
                            boolean deplacementPossible = true;
                            // Déplace la pièce case par case
                            for (int i = 0; i < decalage; i++) {
                                try {
                                    puits.getPieceActuelle().deplacerDe(sens, 0);
                                } catch (Exception ex) {
                                    // Annule les déplacements si une erreur survient
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
                        // Retourne la pièce à sa position initiale si aucune rotation n'est possible
                        puits.getPieceActuelle().setPosition(abscisseInit, ordonneeInit);
                        System.out.println("Rotation impossible même après décalage.");
                    }
                }
            }
        }
    }
}
