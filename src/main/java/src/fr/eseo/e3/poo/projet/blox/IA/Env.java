package src.fr.eseo.e3.poo.projet.blox.IA;

import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

import java.util.List;

public class Env {
    private final Puits puits;

    public Env(Puits puits) {
        this.puits = puits;
    }

    public float[] getState() {
        int largeur = puits.getLargeur();
        int profondeur = puits.getProfondeur();
        float[] state = new float[largeur * profondeur + 8]; 

        List<Element> tasElements = puits.getTas().getElements();
        for (Element elt : tasElements) {
            int x = elt.getCoordonnees().getAbscisse();
            int y = elt.getCoordonnees().getOrdonnee();
            if (x >= 0 && x < largeur && y >= 0 && y < profondeur) {
                state[y * largeur + x] = 1.0f;
            }
        }

        Piece piece = puits.getPieceActuelle();
        if (piece != null) {
            for (Element elt : piece.getElements()) {
                int x = elt.getCoordonnees().getAbscisse();
                int y = elt.getCoordonnees().getOrdonnee();
                if (x >= 0 && x < largeur && y >= 0 && y < profondeur) {
                    state[y * largeur + x] = 2.0f;
                }
            }

            state[largeur * profondeur + 0] = piece.getElements().get(0).getCoordonnees().getAbscisse();
            state[largeur * profondeur + 1] = piece.getElements().get(0).getCoordonnees().getOrdonnee();

            state[largeur * profondeur + 2] = piece.getClass().getSimpleName().hashCode();
        }

        Piece next = puits.getPieceSuivante();
        if (next != null) {
            state[largeur * profondeur + 3] = next.getClass().getSimpleName().hashCode();
        }

        state[largeur * profondeur + 4] = Globals.score.getScore();

        return state;
    }
}

// TODO : 

// public class TetrisEnv {

//     public StepResult step(int action) { ... }

//     public void reset() { ... }

//     public boolean isDone() { ... }

//     public static class StepResult {
//         public float[] nextState;
//         public float reward;
//         public boolean done;