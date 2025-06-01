package src.fr.eseo.e3.poo.projet.blox.views;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vue du menu principal du jeu Tetris.
 * Cette vue présente :
 * - Le titre du jeu
 * - Un bouton pour démarrer une nouvelle partie
 * - Un bouton pour quitter le jeu
 * Le menu utilise un fond sombre pour un style moderne.
 *
 * @author Hugo
 */
public class VueMenu extends JPanel {

    /**
     * Constructeur de la vue du menu principal.
     * Initialise l'interface et configure les composants.
     *
     * @param routeur Le routeur pour la navigation entre les vues
     */
    public VueMenu(Routeur routeur) {
        // Configuration du layout et du fond
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); // Fond sombre stylé

        // Création du titre du jeu
        JLabel titre = new JLabel("FallingBlox");
        titre.setFont(new Font("Arial", Font.BOLD, 48));
        titre.setForeground(Color.WHITE);

        // Création du bouton "Jouer"
        JButton jouer = new JButton("Jouer");
        jouer.setFont(new Font("Arial", Font.BOLD, 24));
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigation vers la vue de jeu
                routeur.afficherVue("JEUX");
                SwingUtilities.invokeLater(() -> {
                    VueJeux vueJeux = (VueJeux) routeur.getVue("JEUX");
                    vueJeux.getVuePuits().demarrerGravite(); // Démarre la gravité
                    vueJeux.getVuePuits().requestFocusInWindow();
                });
            }
        });

        // Création du bouton "Quitter"
        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Arial", Font.BOLD, 24));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Demande de confirmation avant de quitter
                int confirmation = JOptionPane.showConfirmDialog(null, 
                    "Êtes-vous sûr de vouloir quitter ?", 
                    "Quitter", 
                    JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                }
            }
        });

        // Configuration du layout avec GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Ajout des composants au panneau
        add(titre, gbc);

        gbc.gridy++;
        add(jouer, gbc);

        gbc.gridy++;
        add(quit, gbc);
    }
}
