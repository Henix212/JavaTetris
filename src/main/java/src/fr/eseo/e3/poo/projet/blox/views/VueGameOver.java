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


    public VueGameOver(Routeur routeur) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);

        initUI();
    }

    private void initUI() {
    
        JLabel labelTitre = new JLabel("Game Over");
        labelTitre.setFont(new Font("Arial", Font.BOLD, 32));
        labelTitre.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitre.setBounds(100, 30, 300, 40);
        add(labelTitre);

        JLabel labelScore = new JLabel("Score : " + String.valueOf(Globals.score.getScore()));
        labelScore.setFont(new Font("Arial", Font.PLAIN, 24));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);
        labelScore.setBounds(100, 80, 300, 30);
        add(labelScore);

        JButton btnRejouer = new JButton("Rejouer");
        btnRejouer.setBounds(150, 140, 200, 40);
        btnRejouer.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRejouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Globals.score.reset();
                Globals.routeur.afficherVue("jeu");
            }
        });
        add(btnRejouer);

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.setBounds(150, 200, 200, 40);
        btnQuitter.setFont(new Font("Arial", Font.PLAIN, 18));
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(btnQuitter);
    }
}