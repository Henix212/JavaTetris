package src.fr.eseo.e3.poo.projet.blox.utils;

import javax.swing.JFrame;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.modele.Score;

/**
 * Classe contenant les variables globales du jeu Tetris.
 * Cette classe fournit un accès centralisé aux composants principaux du jeu :
 * - La fenêtre principale
 * - Le routeur pour la gestion des vues
 * - Le système de score
 *
 * @author Hugo
 */
public class Globals {
    /** Fenêtre principale du jeu */
    public static final JFrame frame = new JFrame("FallingBlox");
    
    /** Routeur gérant la navigation entre les différentes vues du jeu */
    public static Routeur routeur = new Routeur(frame);
    
    /** Système de score du jeu */
    public static final Score score = new Score();
}
