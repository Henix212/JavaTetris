package src.fr.eseo.e3.poo.projet.blox.utils;

import javax.swing.JFrame;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

public class Globals {
    public static final JFrame frame = new JFrame("FallingBlox");
    public static Routeur routeur = new Routeur(frame);
}
