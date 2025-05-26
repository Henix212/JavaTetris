package src.fr.eseo.e3.poo.projet.blox.views;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenu extends JPanel {

    public VueMenu(Routeur routeur) {
        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); // Fond sombre stylé

        JLabel titre = new JLabel("FallingBlox");
        titre.setFont(new Font("Arial", Font.BOLD, 48));
        titre.setForeground(Color.WHITE);

        JButton jouer = new JButton("Jouer");
        jouer.setFont(new Font("Arial", Font.BOLD, 24));
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                routeur.afficherVue("JEUX");
                SwingUtilities.invokeLater(() -> {
                    VueJeux vueJeux = (VueJeux) routeur.getVue("JEUX");
                    vueJeux.getVuePuits().demarrerGravite(); // <-- Démarre la gravité ici
                    vueJeux.getVuePuits().requestFocusInWindow();
                });
            }
        });

        JButton iaButton = new JButton("AI Mode");
        iaButton.setFont(new Font("Arial", Font.BOLD, 24));
        iaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                    VueMenu.this,
                    "Work in progress",
                    "AI Mode",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Arial", Font.BOLD, 24));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter ?", "Quitter", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titre, gbc);

        gbc.gridy++;
        add(jouer, gbc);

        gbc.gridy++;
        add(iaButton, gbc);

        gbc.gridy++;
        add(quit, gbc);
    }
}
