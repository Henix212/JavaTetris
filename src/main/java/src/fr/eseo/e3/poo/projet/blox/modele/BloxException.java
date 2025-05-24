package src.fr.eseo.e3.poo.projet.blox.modele;

public class BloxException extends Exception {

    public static final int BLOX_COLLISION = 0;
    public static final int BLOX_SORTIE_PUITS = 1;

    private final int type;

    /**
     * Constructeur de la classe BloxException.
     *
     * @param message Le message décrivant l'exception.
     * @param type Le type de l'exception (BLOX_COLLISION ou BLOX_SORTIE_PUITS).
     */
    public BloxException(String message, int type) {
        super(message);
        this.type = type;
    }

    /**
     * Accesseur pour obtenir le type de l'exception.
     *
     * @return Le type de l'exception.
     */
    public int getType() {
        return this.type;
    }
}

