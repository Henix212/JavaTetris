package src.fr.eseo.e3.poo.projet.blox.IA;

import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class NeuralNetwork {
    private final ComputationGraph model;
    
    public NeuralNetwork(int inputSize, int actionSize) {
        // Configuration du réseau avec deux sorties (policy et value)
        ComputationGraphConfiguration config = new NeuralNetConfiguration.Builder()
            .updater(new Adam(0.0003))
            .graphBuilder()
            .addInputs("input")
            .addLayer("hidden1", new DenseLayer.Builder()
                .nIn(inputSize)
                .nOut(64)
                .activation(Activation.RELU)
                .build(), "input")
            // Policy head (distribution de probabilités sur les actions)
            .addLayer("policy", new OutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                .nIn(64)
                .nOut(actionSize)
                .activation(Activation.SOFTMAX)
                .build(), "hidden1")
            // Value head (estimation de la valeur V(s))
            .addLayer("value", new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                .nIn(64)
                .nOut(1)
                .activation(Activation.IDENTITY)
                .build(), "hidden1")
            .setOutputs("policy", "value")
            .setInputTypes(InputType.feedForward(inputSize))
            .build();

        this.model = new ComputationGraph(config);
        this.model.init();
    }

    // Obtenir la distribution de probabilités des actions
    public INDArray getPolicy(INDArray state) {
        INDArray[] outputs = model.output(state);
        return outputs[0]; // premier output = policy
    }

    // Obtenir la valeur estimée de l'état
    public INDArray getValue(INDArray state) {
        INDArray[] outputs = model.output(state);
        return outputs[1]; // second output = value
    }

    // Mise à jour du réseau avec PPO
    public void update(INDArray states, INDArray actions, INDArray advantages, 
                      INDArray returns, INDArray oldProbs) {
        // Calcul des gradients et mise à jour des poids
        model.fit(new INDArray[]{states}, 
                 new INDArray[]{actions, returns}); // policy loss et value loss
    }

    public ComputationGraph getModel() {
        return model;
    }
}
