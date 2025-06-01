package src.fr.eseo.e3.poo.projet.blox.modele;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Représente le puits du jeu Tetris.
 * Le puits est la zone de jeu principale où les pièces tombent et s'empilent.
 * Il gère les dimensions du jeu, les pièces actuelles et suivantes, ainsi que le tas d'éléments.
 *
 * @author Hugo
 */
public class Puits  {

    /** Largeur par défaut du puits */
    public static final int LARGEUR_PAR_DEFAUT = 10;
    
    /** Profondeur par défaut du puits */
    public static final int PROFONDEUR_PAR_DEFAUT = 20;
    
    /** Nom de la propriété pour la modification de la pièce actuelle */
    public static final String MODIFICATION_PIECE_ACTUELLE = "PIECE ACTUELLE";
    
    /** Nom de la propriété pour la modification de la pièce suivante */
    public static final String MODIFICATION_PIECE_SUIVANTE = "PIECE SUIVANTE";
    
    /** Nom de la propriété pour la modification de la pièce en réserve */
    public static final String MODIFICATION_PIECE_RESERVE = "PIECE RESERVE";
    
    /** Largeur actuelle du puits */
    private int largeur;
    
    /** Profondeur actuelle du puits */
    private int profondeur;
    
    /** Pièce actuellement en jeu */
    private Piece pieceActuelle;
    
    /** Prochaine pièce à jouer */
    private Piece pieceSuivante;
    
    /** Pièce en réserve */
    private Piece pieceReserve;
    
    /** Indique si la pièce actuelle a déjà été mise en réserve */
    private boolean pieceActuelleDejaReservee;
    
    /** Support pour la gestion des événements de propriété */
    private final PropertyChangeSupport pcs;
    
    /** Tas d'éléments empilés dans le puits */
    private Tas tas;

    /**
     * Constructeur par défaut du puits.
     * Initialise le puits avec les dimensions par défaut.
     */
    public Puits() {
        setLargeur(LARGEUR_PAR_DEFAUT);
        setProfondeur(PROFONDEUR_PAR_DEFAUT);
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Constructeur du puits avec dimensions personnalisées.
     *
     * @param largeur La largeur du puits (entre 5 et 15)
     * @param profondeur La profondeur du puits (entre 15 et 25)
     * @throws IllegalArgumentException Si les dimensions sont invalides
     */
    public Puits(int largeur, int profondeur) {
        setLargeur(largeur);
        setProfondeur(profondeur);
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Ajoute un écouteur pour les changements de propriétés.
     *
     * @param listener L'écouteur à ajouter
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Retire un écouteur des changements de propriétés.
     *
     * @param listener L'écouteur à retirer
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Retourne une représentation textuelle du puits.
     * Inclut les dimensions et l'état des pièces actuelles et suivantes.
     *
     * @return Une chaîne de caractères décrivant l'état du puits
     */
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

    /**
     * Retourne le tas d'éléments du puits.
     *
     * @return Le tas d'éléments
     */
    public Tas getTas(){
        return this.tas;
    }

    /**
     * Définit le tas d'éléments du puits.
     *
     * @param tas Le nouveau tas d'éléments
     */
    public void setTas(Tas tas) {
        this.tas = tas;
    }

    /**
     * Retourne la largeur actuelle du puits.
     *
     * @return La largeur du puits
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Retourne la profondeur actuelle du puits.
     *
     * @return La profondeur du puits
     */
    public int getProfondeur() {
        return this.profondeur;
    }

    /**
     * Retourne la pièce actuellement en jeu.
     *
     * @return La pièce actuelle
     */
    public Piece getPieceActuelle() {
        return this.pieceActuelle;
    }

    /**
     * Retourne la prochaine pièce à jouer.
     *
     * @return La pièce suivante
     */
    public Piece getPieceSuivante() {
        return this.pieceSuivante;
    }

    /**
     * Retourne la pièce en réserve.
     *
     * @return La pièce en réserve
     */
    public Piece getPieceReserve() {
        return this.pieceReserve;
    }

    /**
     * Définit la largeur du puits.
     *
     * @param largeur La nouvelle largeur (entre 5 et 15)
     * @throws IllegalArgumentException Si la largeur est invalide
     */
    public void setLargeur(int largeur) {
        if (largeur < 5 || largeur > 15) {
            throw new IllegalArgumentException("La largeur doit être comprise entre 5 et 15.");
        }
        this.largeur = largeur;
    }

    /**
     * Définit la profondeur du puits.
     *
     * @param profondeur La nouvelle profondeur (entre 15 et 25)
     * @throws IllegalArgumentException Si la profondeur est invalide
     */
    public void setProfondeur(int profondeur) {
        if (profondeur < 15 || profondeur > 25) {
            throw new IllegalArgumentException("La profondeur doit être comprise entre 15 et 25.");
        }
        this.profondeur = profondeur;
    }

    /**
     * Définit la prochaine pièce à jouer.
     * Si une pièce suivante existe déjà, elle devient la pièce actuelle
     * et est positionnée en haut du puits.
     *
     * @param pieceSuivante La nouvelle pièce suivante
     */
    public void setPieceSuivante(Piece pieceSuivante) {
        if (this.pieceSuivante != null) {
            // Place la nouvelle pièce actuelle en haut du puits
            this.pieceSuivante.setPosition((this.largeur / 2) - 1, 0);
            this.pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, this.pieceActuelle, this.pieceSuivante);
            this.pieceActuelle = this.pieceSuivante;
            // Réinitialise le flag de réserve pour la nouvelle pièce
            this.pieceActuelleDejaReservee = false;
        }
        pieceSuivante.setPuits(this);
        this.pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, this.pieceSuivante, pieceSuivante);
        this.pieceSuivante = pieceSuivante;
    }

    /**
     * Échange la pièce actuelle avec la pièce en réserve.
     * Si aucune pièce n'est en réserve, la pièce actuelle est mise en réserve
     * et la pièce suivante devient la pièce actuelle.
     * Ne peut être utilisé qu'une seule fois par pièce.
     */
    public void echangerPieceReserve() {
        if (this.pieceActuelle == null || this.pieceActuelleDejaReservee) {
            return;
        }

        Piece anciennePieceActuelle = this.pieceActuelle;
        
        if (this.pieceReserve == null) {
            // Si pas de pièce en réserve, on met la pièce actuelle en réserve
            this.pieceReserve = this.pieceActuelle;
            // La pièce suivante devient la pièce actuelle
            this.pieceActuelle = this.pieceSuivante;
            // On génère une nouvelle pièce suivante
            this.pieceSuivante = null; // À remplacer par la génération d'une nouvelle pièce
        } else {
            // Échange entre la pièce actuelle et la pièce en réserve
            this.pieceActuelle = this.pieceReserve;
            this.pieceReserve = anciennePieceActuelle;
        }

        // Positionne la nouvelle pièce actuelle en haut du puits
        if (this.pieceActuelle != null) {
            this.pieceActuelle.setPosition((this.largeur / 2) - 1, 0);
        }

        // Marque la nouvelle pièce actuelle comme pouvant être mise en réserve
        this.pieceActuelleDejaReservee = false;

        // Notifie les changements
        this.pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePieceActuelle, this.pieceActuelle);
        this.pcs.firePropertyChange(MODIFICATION_PIECE_RESERVE, null, this.pieceReserve);
    }

    /**
     * Réinitialise l'état du puits.
     * Efface les pièces actuelles et suivantes, ainsi que le tas d'éléments.
     */
    public void reset() {
        this.pieceActuelle = null;
        this.pieceSuivante = null;
        if (this.tas != null) {
            this.tas.clear();
        }
    }
}
