package src.fr.eseo.e3.poo.projet.blox.IA;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

public class TetrisEnv {
    private final Puits puits;
    private final Tas tas;
    private int lastScore;

    public TetrisEnv() {
        this.puits = new Puits();
        this.tas = new Tas(puits);
        puits.setTas(tas);
        reset();
    }

    // Retourne l'état courant du jeu (pour l'IA)
    public float[] getState() {
        Env env = new Env(puits);
        return env.getState();
    }

    public float[] reset() {
        tas.getElements().clear();
        Globals.score.reset();
        UsineDePiece.setMode(0);
        Piece piece = UsineDePiece.genererTetromino();
        Piece piece2 = UsineDePiece.genererTetromino();
        puits.setPieceSuivante(piece);
        puits.setPieceSuivante(piece2);
        lastScore = 0;
        return getState();
    }

    public float step(int action) {
        boolean done = false;
        float reward = 0f;

        Piece piece = puits.getPieceActuelle();
        if (piece == null) {
            return 0f;
        }

        try {
            switch (action) {
                case 0: // gauche
                    piece.deplacerDe(-1, 0);
                    break;
                case 1: // droite
                    piece.deplacerDe(1, 0);
                    break;
                case 2: // rotation
                    piece.tourner(true);
                    break;
                case 3: // descendre
                    piece.deplacerDe(0, 1);
                    break;
                case 4: // drop
                    while (true) {
                        piece.deplacerDe(0, 1);
                    }
                default:
                    break;
            }
        } catch (BloxException e) {
            // On ne pose la pièce que si l'action était descendre (3) ou drop (4)
            if (action == 3 || action == 4) {
                puits.getTas().ajouterElements(piece);
                puits.getTas().supprimerLignesCompletes();
                puits.setPieceSuivante(UsineDePiece.genererTetromino());
            }
            // Sinon, on ignore l'exception (pour les mouvements latéraux/rotation)
        }

        int currentScore = Globals.score.getScore();
        reward = currentScore - lastScore;
        lastScore = currentScore;

        return reward;
    }

    public boolean isGameOver() {
        return puits.getTas().isGameOver();
    }

    
}
