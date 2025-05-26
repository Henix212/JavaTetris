package src.fr.eseo.e3.poo.projet.blox.utils;

import javax.swing.JFrame;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.modele.Score;

public class Globals {
    public static final JFrame frame = new JFrame("FallingBlox");
    public static Routeur routeur = new Routeur(frame);
    public static final  Score score = new Score();
}
