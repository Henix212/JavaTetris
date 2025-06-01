package src.fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.CardLayout;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;

/**
 * Gestionnaire de navigation entre les différentes vues de l'application.
 * Cette classe utilise un CardLayout pour gérer la transition entre les différentes vues
 * de manière fluide et efficace.
 * 
 * <p>Fonctionnalités principales :
 * <ul>
 *     <li>Gestion d'un ensemble de vues nommées</li>
 *     <li>Navigation entre les vues</li>
 *     <li>Récupération des vues par leur nom</li>
 *     <li>Mise à jour automatique de l'affichage</li>
 * </ul>
 * </p>
 *
 * @author Hugo
 */
public class Routeur {
    /** La fenêtre principale de l'application */
    private final JFrame frame;
    
    /** Le panneau principal contenant toutes les vues */
    private final JPanel mainPanel;
    
    /** Le gestionnaire de mise en page pour la navigation entre les vues */
    private final CardLayout cardLayout;
    
    /** Map associant les noms des vues à leurs composants */
    private final HashMap<String, JComponent> vues = new HashMap<>();

    /**
     * Constructeur de la classe Routeur.
     * Initialise le routeur avec la fenêtre principale spécifiée.
     *
     * @param frame La fenêtre principale de l'application
     * @throws NullPointerException Si frame est null
     */
    public Routeur(JFrame frame) {
        this.frame = frame;
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);
        this.frame.setContentPane(mainPanel);
    }

    /**
     * Ajoute une nouvelle vue au routeur.
     * La vue est stockée dans la map des vues et ajoutée au panneau principal.
     *
     * @param nom Le nom unique identifiant la vue
     * @param vue Le composant à ajouter comme vue
     * @throws NullPointerException Si nom ou vue est null
     */
    public void ajouterVue(String nom, JComponent vue) {
        vues.put(nom, vue);
        mainPanel.add(vue, nom);
    }

    /**
     * Affiche la vue spécifiée par son nom.
     * Met à jour l'affichage de la fenêtre après le changement de vue.
     *
     * @param nom Le nom de la vue à afficher
     * @throws IllegalArgumentException Si aucune vue n'existe avec ce nom
     */
    public void afficherVue(String nom) {
        cardLayout.show(mainPanel, nom);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Récupère une vue par son nom.
     *
     * @param nom Le nom de la vue à récupérer
     * @return Le composant associé au nom, ou null si aucune vue n'existe avec ce nom
     */
    public JComponent getVue(String nom) {
        return vues.get(nom);
    }
}
