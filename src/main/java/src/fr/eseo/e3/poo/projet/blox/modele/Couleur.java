package src.fr.eseo.e3.poo.projet.blox.modele;

import java.awt.Color;

/**
 * Énumération représentant les différentes couleurs utilisées dans le jeu Tetris.
 * Chaque constante représente une couleur spécifique utilisée pour l'affichage
 * des pièces et des éléments du jeu.
 *
 * @author Hugo
 */
public enum Couleur {
    /** Couleur rouge pour les pièces */
    ROUGE(Color.RED),
    
    /** Couleur orange pour les pièces */
    ORANGE(Color.ORANGE),
    
    /** Couleur bleue pour les pièces */
    BLEU(Color.BLUE),
    
    /** Couleur verte pour les pièces */
    VERT(Color.GREEN),
    
    /** Couleur jaune pour les pièces */
    JAUNE(Color.YELLOW),
    
    /** Couleur cyan pour les pièces */
    CYAN(Color.CYAN),
    
    /** Couleur violette pour les pièces */
    VIOLET(new Color(128,0,128));

    /** La couleur AWT correspondante utilisée pour l'affichage */
    private Color couleurPourAffichage;

    /**
     * Constructeur de l'énumération Couleur.
     * Initialise la couleur avec sa valeur AWT correspondante.
     *
     * @param couleurPourAffichage La couleur AWT à utiliser pour l'affichage
     */
    private Couleur(Color couleurPourAffichage){
        this.couleurPourAffichage = couleurPourAffichage;
    }

    /**
     * Retourne la couleur AWT correspondante.
     * Cette couleur est utilisée pour l'affichage graphique des éléments.
     *
     * @return La couleur AWT associée à cette constante
     */
    public Color getCouleurPourAffichage() {
        return this.couleurPourAffichage;
    }
}
