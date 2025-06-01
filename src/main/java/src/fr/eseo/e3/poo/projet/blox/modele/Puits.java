package src.fr.eseo.e3.poo.projet.blox.modele;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Puits  {

    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 20;
    public static final String MODIFICATION_PIECE_ACTUELLE = "PIECE ACTUELLE";
    public static final String MODIFICATION_PIECE_SUIVANTE = "PIECE SUIVANTE";
    
    private int largeur;
    private int profondeur;
    private Piece pieceActuelle;
    private Piece pieceSuivante;
    private final PropertyChangeSupport pcs;
    private Tas tas;

    public Puits() {
        setLargeur(LARGEUR_PAR_DEFAUT);
        setProfondeur(PROFONDEUR_PAR_DEFAUT);
        pcs = new PropertyChangeSupport(this);
    }

    public Puits(int largeur, int profondeur) {
        setLargeur(largeur);
        setProfondeur(profondeur);
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getClass().getSimpleName() + " : Dimension " + getLargeur() + " x " +getProfondeur() +"\n");
        if(this.pieceSuivante != null) {
            result.append("Piece Suivante : ").append(this.pieceSuivante.getClass().getSimpleName()).append("\n");
            result.append(pieceSuivante);
        } else {
            result.append("Piece Suivante : <aucune>\n");
        }
        if(this.pieceActuelle != null) {
            result.append("Piece Actuelle : ").append(this.pieceActuelle.getClass().getSimpleName()).append("\n");
            result.append(pieceActuelle);
        } else {
            result.append("Piece Actuelle : <aucune>\n");
        }
        return result.toString();
    }

    public Tas getTas(){
        return this.tas;
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public int getProfondeur() {
        return this.profondeur;
    }

    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    public void setLargeur(int largeur) {
        if (largeur < 5 || largeur > 15) {
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");
        }
        this.largeur = largeur;
    }

    public void setProfondeur(int profondeur) {
        if (profondeur < 15 || profondeur > 25) {
            throw new IllegalArgumentException("La profondeur doit être comprise entre 15 et 25.");
        }
        this.profondeur = profondeur;
    }

    public void setPieceSuivante(Piece pieceSuivante) {
        if (this.pieceSuivante != null) {
            // Place la nouvelle pièce actuelle en haut du puits
            this.pieceSuivante.setPosition((this.largeur / 2) - 1, 0);
            this.pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, this.pieceActuelle, this.pieceSuivante);
            this.pieceActuelle = this.pieceSuivante;
        }
        pieceSuivante.setPuits(this);
        this.pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, this.pieceSuivante, pieceSuivante);
        this.pieceSuivante = pieceSuivante;
    }

    public void reset() {
        this.pieceActuelle = null;
        this.pieceSuivante = null;
        if (this.tas != null) {
            this.tas.clear();
        }
    }
}
