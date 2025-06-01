package src.fr.eseo.e3.poo.projet.blox;
import javax.swing.JFrame;
import src.fr.eseo.e3.poo.projet.blox.views.VueJeux;
import src.fr.eseo.e3.poo.projet.blox.views.VueMenu;
import src.fr.eseo.e3.poo.projet.blox.views.VueGameOver;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

/**
 * Point d'entrée principal du jeu Tetris.
 * Cette classe initialise l'application et configure la fenêtre principale.
 * Elle gère également l'initialisation des différentes vues du jeu et leur
 * enregistrement dans le routeur.
 *
 * @author Hugo
 */
public class Main {
    /**
     * Point d'entrée de l'application.
     * Initialise et configure :
     * - Les différentes vues du jeu
     * - Le routeur pour la navigation
     * - La fenêtre principale
     *
     * @param args Les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {        
        // Enregistrement des vues dans le routeur
        Globals.routeur.ajouterVue("MENU", new VueMenu(Globals.routeur));
        Globals.routeur.ajouterVue("JEUX", new VueJeux());
        Globals.routeur.ajouterVue("GAMEOVER", new VueGameOver(Globals.routeur));

        // Affichage de la vue du menu par défaut
        Globals.routeur.afficherVue("MENU");

        // Configuration de la fenêtre principale
        Globals.frame.setDefaultCloseOperation(3); // JFrame.EXIT_ON_CLOSE
        Globals.frame.setVisible(true);
        Globals.frame.pack();
        Globals.frame.setResizable(false);
        Globals.frame.setLocationRelativeTo(null); // Centre la fenêtre
        Globals.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximise la fenêtre
    }
}
