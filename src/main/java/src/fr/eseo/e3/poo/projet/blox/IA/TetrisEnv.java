package src.fr.eseo.e3.poo.projet.blox.IA;

import src.fr.eseo.e3.poo.projet.blox.modele.BloxException;
import src.fr.eseo.e3.poo.projet.blox.modele.Puits;
import src.fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import src.fr.eseo.e3.poo.projet.blox.modele.pieces.Tas;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;
import src.fr.eseo.e3.poo.projet.blox.modele.Element;
import java.util.List;

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
        float[] state = new float[208];
        int idx = 0;
        
        // État du tas (grille)
        List<Element> elements = puits.getTas().getElements();
        boolean[][] grid = new boolean[20][10];
        
        // Remplir la grille avec les éléments du tas
        for (Element e : elements) {
            int x = e.getCoordonnees().getAbscisse();
            int y = e.getCoordonnees().getOrdonnee();
            if (x >= 0 && x < 10 && y >= 0 && y < 20) {
                grid[y][x] = true;
            }
        }
        
        // Convertir la grille en state
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                state[idx++] = grid[i][j] ? 1.0f : 0.0f;
            }
        }
        
        // Information sur la pièce actuelle
        Piece piece = puits.getPieceActuelle();
        if (piece != null) {
            state[200] = piece.getElements().get(0).getCoordonnees().getAbscisse() / 10.0f;
            state[201] = piece.getElements().get(0).getCoordonnees().getOrdonnee() / 20.0f;
            // Type de pièce (one-hot encoding)
            String pieceName = piece.getClass().getSimpleName();
            int pieceType = pieceName.charAt(0) - 'A';
            if (pieceType >= 0 && pieceType < 7) {
                state[203 + pieceType] = 1.0f;
            }
        }
        
        return state;
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
        float reward = 0;
        Piece piece = puits.getPieceActuelle();
        if (piece == null) {
            puits.setPieceSuivante(UsineDePiece.genererTetromino());
            return -1;  // Pénalité pour pas de pièce active
        }

        int oldHeight = getTasHeight();
        int oldHoles = getHoles();
        
        try {
            switch (action) {
                case 0:  // gauche
                    piece.deplacerDe(-1, 0);
                    reward += 0.1f;  // petit bonus pour mouvement
                    break;
                case 1:  // droite
                    piece.deplacerDe(1, 0);
                    reward += 0.1f;
                    break;
                case 2:  // rotation
                    piece.tourner(true);
                    reward += 0.2f;
                    break;
                case 3:  // descendre
                    piece.deplacerDe(0, 1);
                    reward += 0.5f;
                    break;
                case 4:  // drop
                    int dropHeight = 0;
                    while (true) {
                        try {
                            piece.deplacerDe(0, 1);
                            dropHeight++;
                        } catch (BloxException e) {
                            break;
                        }
                    }
                    puits.getTas().ajouterElements(piece);
                    puits.getTas().supprimerLignesCompletes();
                    reward += dropHeight * 0.5f;  // bonus pour la hauteur du drop
                    puits.setPieceSuivante(UsineDePiece.genererTetromino());
                    break;
            }
        } catch (BloxException e) {
            // Collision détectée
            if (action == 3) {  // Si on descendait
                puits.getTas().ajouterElements(piece);
                puits.getTas().supprimerLignesCompletes();
                // reward += lignesSupprimees * 100;
                puits.setPieceSuivante(UsineDePiece.genererTetromino());
            } else {
                reward -= 0.5f;  // Pénalité pour collision inutile
            }
        }

        // Pénalités basées sur l'état du jeu
        int newHeight = getTasHeight();
        int newHoles = getHoles();
        reward -= (newHeight - oldHeight) * 2;  // Pénalise l'augmentation de hauteur
        reward -= (newHoles - oldHoles) * 5;    // Pénalise la création de trous

        if (puits.getTas().isGameOver()) {
            reward -= 500;  // Grosse pénalité pour game over
        }

        return reward;
    }

    public boolean isGameOver() {
        return puits.getTas().isGameOver();
    }

    public Puits getPuits() {
        return puits;
    }
    
    private int getTasHeight() {
        List<Element> elements = puits.getTas().getElements();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                boolean found = false;
                for (Element e : elements) {
                    if (e.getCoordonnees().getOrdonnee() == i && e.getCoordonnees().getAbscisse() == j) {
                        found = true;
                        break;
                    }
                }
    private int getHoles() {
        int holes = 0;
        List<Element> elements = puits.getTas().getElements();
        for (int j = 0; j < 10; j++) {
            boolean blockFound = false;
            for (int i = 0; i < 20; i++) {
                boolean occupied = false;
                for (Element e : elements) {
                    if (e.getCoordonnees().getOrdonnee() == i && e.getCoordonnees().getAbscisse() == j) {
                        occupied = true;
                        break;
                    }
                }
                if (occupied) {
                    blockFound = true;
                } else if (blockFound) {
                    holes++;
                }
            }
        }
        return holes;
    }
                    blockFound = true;
                } else if (blockFound) {
                    holes++;
                }
            }
        }
        return holes;
    }
}
