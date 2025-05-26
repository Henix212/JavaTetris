package src.fr.eseo.e3.poo.projet.blox.IA;

import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TetrisEnv env = new TetrisEnv();
        float[] initState = env.getState();
        int stateDim = initState.length;
        int actionDim = 5;

        // --- Affichage du puits ---
        VuePuits vuePuits = new VuePuits(env.getPuits(), 30);
        JFrame frame = new JFrame("Tetris Entrainement");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vuePuits);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        NeuralNetwork network = new NeuralNetwork(stateDim, actionDim);
        PPOAgent agent = new PPOAgent(network, stateDim, actionDim, vuePuits);

        // Lance l'entraînement directement (pas besoin de SwingUtilities.invokeLater)
        new Thread(() -> {
            int maxTimesteps = 10000;
            int updateEveryN = 2048;
            float gamma = 0.99f;
            agent.train(maxTimesteps, updateEveryN, gamma, env);
        }).start();
    }
}

