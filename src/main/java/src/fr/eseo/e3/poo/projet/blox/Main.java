package src.fr.eseo.e3.poo.projet.blox;
import javax.swing.JFrame;
import src.fr.eseo.e3.poo.projet.blox.views.VueJeux;
import src.fr.eseo.e3.poo.projet.blox.views.VueMenu;
import src.fr.eseo.e3.poo.projet.blox.views.VueGameOver;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

public class Main {
    public static void main(String[] args) {        
        Globals.routeur.ajouterVue("MENU", new VueMenu(Globals.routeur));
        Globals.routeur.ajouterVue("JEUX", new VueJeux());
        Globals.routeur.ajouterVue("GAMEOVER", new VueGameOver(Globals.routeur));
        Globals.routeur.afficherVue("MENU");
        Globals.frame.setDefaultCloseOperation(3);
        Globals.frame.setVisible(true);
        Globals.frame.pack();
        Globals.frame.setResizable(false);
        Globals.frame.setLocationRelativeTo(null);
        Globals.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    }
}
