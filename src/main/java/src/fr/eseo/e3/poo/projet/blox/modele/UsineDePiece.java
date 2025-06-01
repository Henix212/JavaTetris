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

public class UsineDePiece {
    static int ACUTALPIECE = 0;
    static int ALEATOIRE_PIECE = 1;
    static int ALEATOIRE_COMPLET = 0;
    static int CYCLIC = 0;
    static Random rand = new Random();

    private UsineDePiece(){}

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

    public static Tetromino genererTetromino(){
        Tetromino piece = null;
        ArrayList<Tetromino> tetrominos = new ArrayList<>();
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
