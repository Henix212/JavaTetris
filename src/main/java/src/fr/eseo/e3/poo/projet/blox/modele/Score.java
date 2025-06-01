package src.fr.eseo.e3.poo.projet.blox.modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Gère le score du jeu Tetris.
 * Cette classe maintient le score actuel et notifie les observateurs
 * des changements de score via le pattern Observer.
 *
 * @author Hugo
 */
public class Score {
    /** Le score actuel du joueur */
    private int score;
    
    /** Support pour la gestion des événements de propriété */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructeur de la classe Score.
     * Initialise le score à 0.
     */
    public Score() {
        this.score = 0;
    }

    /**
     * Ajoute des points au score actuel.
     * Notifie les observateurs du changement de score.
     *
     * @param points Le nombre de points à ajouter au score
     */
    public void ajouter(int points) {
        int oldScore = this.score;
        this.score += points;
        pcs.firePropertyChange("SCORE", oldScore, this.score);
    }

    /**
     * Retourne le score actuel.
     *
     * @return Le score actuel du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Réinitialise le score à 0.
     * Notifie les observateurs du changement de score.
     */
    public void reset() {
        int oldScore = this.score;
        this.score = 0;
        pcs.firePropertyChange("SCORE", oldScore, this.score);
    }

    /**
     * Ajoute un écouteur pour les changements de score.
     *
     * @param listener L'écouteur à ajouter
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Retire un écouteur des changements de score.
     *
     * @param listener L'écouteur à retirer
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
