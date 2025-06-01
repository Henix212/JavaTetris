package src.fr.eseo.e3.poo.projet.blox.controleur;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import src.fr.eseo.e3.poo.projet.blox.utils.ControllAdaptateur;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Contrôleur responsable de la gestion des déplacements des pièces dans le jeu.
 * Cette classe hérite de ControllAdaptateur et implémente les mécanismes de déplacement
 * via la souris, la molette et le clavier.
 * 
 * <p>Fonctionnalités principales :
 * <ul>
 *     <li>Déplacement horizontal avec les flèches gauche/droite</li>
 *     <li>Déplacement vertical avec la flèche bas</li>
 *     <li>Déplacement rapide vers le bas avec la barre d'espace</li>
 *     <li>Déplacement vertical avec la molette de la souris</li>
 *     <li>Déplacement horizontal en suivant le curseur de la souris</li>
 *     <li>Gestion des collisions et du tas de pièces</li>
 * </ul>
 * </p>
 *
 * @author Hugo
 */
public class PieceDeplacement extends ControllAdaptateur  {
    /** La vue du puits de jeu associée à ce contrôleur */
    private VuePuits vuePuits;
    
    /** Le puits de jeu sur lequel les déplacements sont effectués */
    private Puits puits;

    /**
     * Constructeur de la classe PieceDeplacement.
     * Initialise le contrôleur avec la vue et le puits spécifiés.
     *
     * @param vuePuits La vue du puits de jeu à laquelle ce contrôleur est associé
     * @param puits Le puits de jeu sur lequel les déplacements seront effectués
     * @throws NullPointerException Si vuePuits ou puits est null
     */
    public PieceDeplacement(VuePuits vuePuits, Puits puits) {
        this.vuePuits = vuePuits;
        this.puits = puits;
    }

    /**
     * Gère les événements de la molette de la souris pour le déplacement vertical.
     * Un mouvement vers le bas de la molette fait descendre la pièce d'une case.
     * Si la pièce ne peut plus descendre, elle est ajoutée au tas et une nouvelle pièce est générée.
     *
     * @param event L'événement de la molette de la souris
     * @throws NullPointerException Si aucune pièce n'est actuellement en jeu
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent event){
        // Vérifie si une pièce est en jeu
        if (this.puits.getPieceActuelle() == null){
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }

        // Gère le déplacement vers le bas si la molette est tournée vers le bas
        if (event.getWheelRotation() > 0){
            try {
                this.puits.getPieceActuelle().deplacerDe(0,1);
            } catch (BloxException e) {
                // Si la pièce ne peut plus descendre, elle est ajoutée au tas
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
                return; 
            }
        }

        // Rafraîchit l'affichage
        this.vuePuits.repaint();
    }

    /**
     * Gère les mouvements de la souris pour le déplacement horizontal de la pièce.
     * La pièce suit horizontalement le curseur de la souris, en se déplaçant case par case
     * jusqu'à atteindre la colonne cible ou rencontrer un obstacle.
     *
     * @param event L'événement de mouvement de la souris
     * @throws NullPointerException Si aucune pièce n'est actuellement en jeu
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        // Vérifie si une pièce est en jeu
        if (this.puits.getPieceActuelle() == null) {
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }

        // Calcule la colonne cible et la différence avec la position actuelle
        int cibleCol = (int) (event.getX() / this.vuePuits.getTaille());
        int colActuelle = this.puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse();
        int delta = cibleCol - colActuelle;
        int direction = (delta > 0) ? 1 : -1;

        // Déplace la pièce case par case jusqu'à la colonne cible ou un obstacle
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

    /**
     * Gère les événements de pression de touche pour le déplacement des pièces.
     * <ul>
     *     <li>Flèche gauche : déplacement d'une case vers la gauche</li>
     *     <li>Flèche droite : déplacement d'une case vers la droite</li>
     *     <li>Flèche bas : déplacement d'une case vers le bas</li>
     *     <li>Barre d'espace : déplacement rapide vers le bas jusqu'à collision</li>
     * </ul>
     * 
     * <p>Lorsqu'une pièce ne peut plus descendre (collision), elle est :
     * <ol>
     *     <li>Ajoutée au tas de pièces</li>
     *     <li>Les lignes complètes sont supprimées</li>
     *     <li>Une nouvelle pièce est générée</li>
     * </ol>
     * </p>
     *
     * @param e L'événement de clavier déclenché
     * @throws NullPointerException Si aucune pièce n'est actuellement en jeu
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        // Vérifie si une pièce est en jeu
        if (this.puits.getPieceActuelle() == null) {
            throw new NullPointerException("Aucune pièce actuelle à déplacer.");
        }
        
        // Gère les déplacements selon la touche pressée
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
                // Si la pièce ne peut plus descendre, elle est ajoutée au tas
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
            }
            vuePuits.repaint();
        }
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            try {
                // Déplacement rapide vers le bas jusqu'à collision
                while (true) {
                    puits.getPieceActuelle().deplacerDe(0, 1);
                }
            } catch (Exception ex) {
                // Si la pièce ne peut plus descendre, elle est ajoutée au tas
                puits.getTas().ajouterElements(puits.getPieceActuelle());
                puits.getTas().supprimerLignesCompletes();
                this.puits.setPieceSuivante(UsineDePiece.genererTetromino());
            }
            vuePuits.repaint();
        }
    }
}
