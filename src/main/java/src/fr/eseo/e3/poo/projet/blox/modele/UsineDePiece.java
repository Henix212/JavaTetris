package src.fr.eseo.e3.poo.projet.blox.modele;

import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.LTetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.JTetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.TTetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.STetromino;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ZTetromino;

import java.util.ArrayList;
import java.util.Random;

/**
 * Usine de génération de pièces pour le jeu Tetris.
 * Cette classe gère la création des différentes pièces du jeu selon différents modes :
 * - Aléatoire : génère des pièces avec des couleurs prédéfinies
 * - Cyclique : génère les pièces dans un ordre fixe
 * - Aléatoire complet : génère des pièces avec des couleurs aléatoires
 *
 * @author Hugo
 */
public class UsineDePiece {
    /** Indice de la pièce actuelle en mode cyclique */
    static int ACUTALPIECE = 0;
    
    /** Mode de génération aléatoire des pièces */
    static int ALEATOIRE_PIECE = 1;
    
    /** Mode de génération aléatoire complète (pièces et couleurs) */
    static int ALEATOIRE_COMPLET = 0;
    
    /** Mode de génération cyclique des pièces */
    static int CYCLIC = 0;
    
    /** Générateur de nombres aléatoires */
    static Random rand = new Random();

    /**
     * Constructeur privé pour empêcher l'instanciation.
     * Cette classe utilise uniquement des méthodes statiques.
     */
    private UsineDePiece(){}

    /**
     * Définit le mode de génération des pièces.
     * Trois modes sont disponibles :
     * - 0 : Aléatoire (pièces avec couleurs prédéfinies)
     * - 1 : Cyclique (pièces dans un ordre fixe)
     * - 2 : Aléatoire complet (pièces et couleurs aléatoires)
     *
     * @param mode Le mode de génération à utiliser
     * @throws IllegalArgumentException Si le mode n'est pas valide
     */
    public static void setMode(int mode) throws IllegalArgumentException {
        switch (mode){
            case 0:
                ALEATOIRE_PIECE = 1;
                CYCLIC = 0;
                ALEATOIRE_COMPLET = 0;
                break;
            case 1:
                ALEATOIRE_PIECE = 0;
                CYCLIC = 1;
                ALEATOIRE_COMPLET = 0;
                break;
            case 2:
                ALEATOIRE_PIECE = 0;
                CYCLIC = 0;
                ALEATOIRE_COMPLET = 1;
                break;
            default:
                throw new IllegalArgumentException("Illegal mode");
        }
    }

    /**
     * Génère une nouvelle pièce selon le mode actuel.
     * Les pièces sont créées avec des coordonnées initiales (2,3) et
     * des couleurs selon le mode de génération.
     *
     * @return Une nouvelle pièce Tetromino
     */
    public static Tetromino genererTetromino(){
        Tetromino piece = null;
        ArrayList<Tetromino> tetrominos = new ArrayList<>();
        
        // Mode aléatoire : pièces avec couleurs prédéfinies
        if(ALEATOIRE_PIECE == 1){
            tetrominos.add(new OTetromino(new Coordonnees(2,3),Couleur.ROUGE));
            tetrominos.add(new ITetromino(new Coordonnees(2,3),Couleur.ORANGE));
            tetrominos.add(new LTetromino(new Coordonnees(2, 3), Couleur.BLEU));
            tetrominos.add(new JTetromino(new Coordonnees(2, 3), Couleur.VERT));
            tetrominos.add(new TTetromino(new Coordonnees(2, 3), Couleur.JAUNE));
            tetrominos.add(new STetromino(new Coordonnees(2, 3), Couleur.VIOLET));
            tetrominos.add(new ZTetromino(new Coordonnees(2, 3), Couleur.CYAN));

            piece = tetrominos.get(rand.nextInt(tetrominos.size()));
        }
        
        // Mode cyclique : pièces dans un ordre fixe
        if(CYCLIC == 1){
            tetrominos.add(new OTetromino(new Coordonnees(2,3),Couleur.ROUGE));
            tetrominos.add(new ITetromino(new Coordonnees(2,3),Couleur.ORANGE));
            tetrominos.add(new LTetromino(new Coordonnees(2, 3), Couleur.BLEU));
            tetrominos.add(new JTetromino(new Coordonnees(2, 3), Couleur.VERT));
            tetrominos.add(new TTetromino(new Coordonnees(2, 3), Couleur.JAUNE));
            tetrominos.add(new STetromino(new Coordonnees(2, 3), Couleur.VIOLET));
            tetrominos.add(new ZTetromino(new Coordonnees(2, 3), Couleur.CYAN));
            
            if(ACUTALPIECE > tetrominos.size()){
                ACUTALPIECE = 0;
            } 
            
            piece = tetrominos.get(ACUTALPIECE);
            ACUTALPIECE++;
                        
        }

        // Mode aléatoire complet : pièces et couleurs aléatoires
        if(ALEATOIRE_COMPLET == 1){
            tetrominos.add(new OTetromino(new Coordonnees(2,3),Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new ITetromino(new Coordonnees(2,3),Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new LTetromino(new Coordonnees(2,3),Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new JTetromino(new Coordonnees(2,3),Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new TTetromino(new Coordonnees(2,3),Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new STetromino(new Coordonnees(2, 3), Couleur.values()[rand.nextInt(Couleur.values().length)]));
            tetrominos.add(new ZTetromino(new Coordonnees(2, 3), Couleur.values()[rand.nextInt(Couleur.values().length)]));

            piece = tetrominos.get(rand.nextInt(tetrominos.size()));
        }
        
        return piece;
    }
}
