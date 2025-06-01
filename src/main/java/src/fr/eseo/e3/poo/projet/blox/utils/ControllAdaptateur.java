package src.fr.eseo.e3.poo.projet.blox.utils;

import java.awt.event.*;

/**
 * Classe adaptateur pour la gestion des événements de contrôle du jeu Tetris.
 * Cette classe abstraite implémente toutes les interfaces de gestion d'événements
 * (MouseListener, MouseWheelListener, MouseMotionListener, KeyListener) et fournit
 * des implémentations vides par défaut. Les classes filles peuvent surcharger
 * uniquement les méthodes dont elles ont besoin.
 *
 * @author Hugo
 */
public abstract class ControllAdaptateur implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {
    /**
     * {@inheritDoc}
     * Appelé lorsqu'un clic de souris est effectué (appui et relâchement).
     */
    public void mouseClicked(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsqu'un bouton de la souris est enfoncé.
     */
    public void mousePressed(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsqu'un bouton de la souris est relâché.
     */
    public void mouseReleased(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsque le curseur de la souris entre dans la zone de l'élément.
     */
    public void mouseEntered(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsque le curseur de la souris sort de la zone de l'élément.
     */
    public void mouseExited(MouseEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsque la molette de la souris est utilisée.
     * @since 1.6
     */
    public void mouseWheelMoved(MouseWheelEvent e){}

    /**
     * {@inheritDoc}
     * Appelé lorsque la souris est déplacée avec un bouton enfoncé.
     * @since 1.6
     */
    public void mouseDragged(MouseEvent e){}

    /**
     * {@inheritDoc}
     * Appelé lorsque la souris est déplacée sans bouton enfoncé.
     * @since 1.6
     */
    public void mouseMoved(MouseEvent e){}

    /**
     * {@inheritDoc}
     * Appelé lorsqu'une touche est pressée et relâchée.
     * @since 1.6
     */
    public void keyTyped(KeyEvent e) {}

    /**
     * {@inheritDoc}
     * Appelé lorsqu'une touche est enfoncée.
     * @since 1.6
     */
    public void keyPressed(KeyEvent e){}

    /**
     * {@inheritDoc}
     * Appelé lorsqu'une touche est relâchée.
     * @since 1.6
     */
    public void keyReleased(KeyEvent e){}
}
