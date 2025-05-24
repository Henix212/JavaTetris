package src.fr.eseo.e3.poo.projet.blox.modele;

import java.awt.Color;

/**
 * Enum representing different colors.
 * Each constant represents a specific color.
 */
public enum Couleur {
    ROUGE(Color.RED),
    ORANGE(Color.ORANGE),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    CYAN(Color.CYAN),
    VIOLET(new Color(128,0,128));

    private Color couleurPourAffichage;

    private Couleur(Color couleurPourAffichage){
        this.couleurPourAffichage = couleurPourAffichage;
    }

    public Color getCouleurPourAffichage() {
        return this.couleurPourAffichage;
    }
}
