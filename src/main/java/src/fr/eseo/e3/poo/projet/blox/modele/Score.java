package src.fr.eseo.e3.poo.projet.blox.modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Score {
    private int score;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Score() {
        this.score = 0;
    }

    public void ajouter(int points) {
        int oldScore = this.score;
        this.score += points;
        pcs.firePropertyChange("SCORE", oldScore, this.score);
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        int oldScore = this.score;
        this.score = 0;
        pcs.firePropertyChange("SCORE", oldScore, this.score);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
