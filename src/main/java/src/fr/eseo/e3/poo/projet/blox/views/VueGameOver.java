package src.fr.eseo.e3.poo.projet.blox.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.fr.eseo.e3.poo.projet.blox.controleur.Routeur;
import src.fr.eseo.e3.poo.projet.blox.utils.Globals;

public class VueGameOver extends JPanel{

    private JLabel labelScore;

    public VueGameOver(Routeur routeur) {
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);
        initUI();
    }

    private void initUI() {
        this.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(15, 20, 15, 20);

        JLabel labelTitre = new JLabel("Game Over");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 32));
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        this.add(labelTitre, gbc);

        labelScore = new JLabel();
        labelScore.setFont(new Font("Arial", Font.PLAIN, 24));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        this.add(labelScore, gbc);

        JButton btnRejouer = new JButton("Rejouer");
        btnRejouer.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRejouer.addActionListener(e -> {
            VueJeux vueJeux = (VueJeux) Globals.routeur.getVue("JEUX"); 
            vueJeux.gameReset(); 
            Globals.routeur.afficherVue("JEUX");
        });
        gbc.gridy = 2;
        this.add(btnRejouer, gbc);

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setFont(new Font("Arial", Font.PLAIN, 18));
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridy = 3;
        this.add(btnQuitter, gbc);
    }

    public void updateScore() {
        labelScore.setText("Score : " + Globals.score.getScore());
    }
}