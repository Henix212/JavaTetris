package src.fr.eseo.e3.poo.projet.blox.IA;

public class Main {
    public static void main(String[] args) {
        
        TetrisEnv env = new TetrisEnv();
        float[] initState = env.getState();
        int stateDim = initState.length;
        int actionDim = 5;

        NeuralNetwork network = new NeuralNetwork(stateDim, actionDim);
        PPOAgent agent = new PPOAgent(network, stateDim, actionDim);

        int maxTimesteps = 100_000;
        int updateEveryN = 2048;
        float gamma = 0.99f;

        agent.train(maxTimesteps, updateEveryN, gamma);
    }
}

