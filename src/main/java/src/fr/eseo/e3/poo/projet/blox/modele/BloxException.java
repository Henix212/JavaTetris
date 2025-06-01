package src.fr.eseo.e3.poo.projet.blox.modele;

/**
 * Exception spécifique au jeu Tetris (Blox).
 * Cette exception est levée lorsqu'une action invalide est tentée dans le jeu,
 * comme une collision ou une sortie du puits.
 *
 * @author Hugo
 */
public class BloxException extends Exception {

    /** Type d'exception indiquant une collision avec un autre élément ou le fond du puits */
    public static final int BLOX_COLLISION = 0;
    
    /** Type d'exception indiquant une sortie des limites du puits */
    public static final int BLOX_SORTIE_PUITS = 1;

    /** Le type de l'exception (BLOX_COLLISION ou BLOX_SORTIE_PUITS) */
    private final int type;

    /**
     * Constructeur de la classe BloxException.
     * Crée une nouvelle exception avec un message descriptif et un type spécifique.
     *
     * @param message Le message décrivant l'exception
     * @param type Le type de l'exception (BLOX_COLLISION ou BLOX_SORTIE_PUITS)
     * @throws IllegalArgumentException Si le type n'est pas valide
     */
    public BloxException(String message, int type) {
        super(message);
        if (type != BLOX_COLLISION && type != BLOX_SORTIE_PUITS) {
            throw new IllegalArgumentException("Type d'exception invalide");
        }
        this.type = type;
    }

    /**
     * Retourne le type de l'exception.
     * Permet de distinguer entre une collision et une sortie du puits.
     *
     * @return Le type de l'exception (BLOX_COLLISION ou BLOX_SORTIE_PUITS)
     */
    public int getType() {
        return this.type;
    }
}

