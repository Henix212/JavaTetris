package src.fr.eseo.e3.poo.projet.blox.IA;

import src.fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.*;
import javax.swing.SwingUtilities;

public class PPOAgent {
    private final NeuralNetwork network;
    private final int stateDim;
    private final int actionDim;
    private final VuePuits vuePuits;
    private final Random random;

    public PPOAgent(NeuralNetwork network, int stateDim, int actionDim, VuePuits vuePuits) {
        this.network = network;
        this.stateDim = stateDim;
        this.actionDim = actionDim;
        this.vuePuits = vuePuits;
        this.random = new Random();
    }

    public void train(int maxTimesteps, int updateEveryN, float gamma, TetrisEnv env) {
        List<float[]> states = new ArrayList<>();
        List<Integer> actions = new ArrayList<>();
        List<Float> rewards = new ArrayList<>();
        List<Float> oldProbs = new ArrayList<>();
        List<Float> values = new ArrayList<>();

        float[] state = env.reset();  // Reset initial de l'environnement
        int episodeCount = 0;
        float totalReward = 0;

        for (int t = 0; t < maxTimesteps; t++) {
            // 1. Prédire l'action avec le réseau
            INDArray stateND = Nd4j.create(state).reshape(1, stateDim);
            INDArray[] outputArr = network.getModel().output(stateND);
            INDArray output = outputArr[0];
            float[] probs = output.toFloatVector();

            // 2. Choisir une action
            int action = sampleFromDistribution(probs);
            
            // 3. Exécuter l'action dans l'environnement
            float reward = env.step(action);
            float[] nextState = env.getState();
            boolean done = env.isGameOver();

            totalReward += reward;

            // 4. Stocker la transition
            states.add(state.clone());
            actions.add(action);
            rewards.add(reward);
            oldProbs.add(probs[action]);
            
            // Estimation de la valeur (si tu as une tête value)
            INDArray valueND = network.getValue(stateND);
            float value = valueND.getFloat(0);
            values.add(value);

            // 5. Mise à jour de l'état
            state = nextState;

            // 6. Affichage de l'environnement
            if (vuePuits != null) {
                SwingUtilities.invokeLater(() -> vuePuits.repaint());
                try {
                    Thread.sleep(100); // Augmente le délai pour mieux voir le jeu
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // 7. Si épisode terminé
            if (done) {
                episodeCount++;
                System.out.println("Episode " + episodeCount + " terminé avec reward total: " + totalReward);
                state = env.reset();
                totalReward = 0;
            }

            // 8. Mise à jour du réseau (PPO update)
            if ((t + 1) % updateEveryN == 0) {
                float[] advantages = computeAdvantages(rewards, values, gamma);
                float[] returns = new float[advantages.length];
                for (int i = 0; i < advantages.length; i++) {
                    returns[i] = advantages[i] + values.get(i);
                }

                // Conversion en tenseurs ND4J pour l'entraînement
                INDArray statesND = Nd4j.create(states.size(), stateDim);
                for (int i = 0; i < states.size(); i++) {
                    statesND.putRow(i, Nd4j.create(states.get(i)));
                }

                INDArray actionsND = Nd4j.create(actions.stream().mapToInt(i -> i).toArray());
                INDArray advantagesND = Nd4j.create(advantages);
                INDArray returnsND = Nd4j.create(returns);
                INDArray oldProbsND = Nd4j.create(oldProbs.stream().mapToDouble(f -> f).toArray());

                // Mise à jour du réseau avec PPO
                network.update(statesND, actionsND, advantagesND, returnsND, oldProbsND);

                // Vider les buffers
                states.clear();
                actions.clear();
                rewards.clear();
                oldProbs.clear();
                values.clear();
            }
        }
    }

    private int sampleFromDistribution(float[] probs) {
        float r = random.nextFloat();
        float cumSum = 0;
        for (int i = 0; i < probs.length; i++) {
            cumSum += probs[i];
            if (r < cumSum) {
                return i;
            }
        }
        return probs.length - 1;
    }

    private float[] computeAdvantages(List<Float> rewards, List<Float> values, float gamma) {
        int n = rewards.size();
        float[] advantages = new float[n];
        float nextValue = 0;  // Pour le dernier état
        
        for (int t = n - 1; t >= 0; t--) {
            float delta = rewards.get(t) + gamma * nextValue - values.get(t);
            advantages[t] = delta;
            nextValue = values.get(t);
        }
        
        return advantages;
    }
}


// import org.tensorflow.Graph;
// import org.tensorflow.Session;
// import org.tensorflow.Tensor;
// import org.tensorflow.ndarray.FloatNdArray;
// import org.tensorflow.ndarray.NdArrays;
// import org.tensorflow.ndarray.Shape;
// import org.tensorflow.op.Ops;
// import org.tensorflow.types.TFloat32;
// import org.tensorflow.types.TInt32;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;

// public class PPOAgent {
//     private final NeuralNetwork network;
//     private final Session session;
//     private final Random random;
//     private final int stateDim;
//     private final int actionDim;

//     /** Constructeur : construit la session et initialise les variables du graphe. */
//     public PPOAgent(NeuralNetwork network, int stateDim, int actionDim) {
//         this.network = network;
//         this.stateDim = stateDim;
//         this.actionDim = actionDim;
//         this.random = new Random();
//         Graph graph = network.getGraph();          // Graphe créé par NeuralNetwork
//         this.session = new Session(graph);
//         Ops tf = Ops.create(graph);
//         // Initialiser les variables
//         session.runner().addTarget(tf.init()).run();
//     }

//     /**
//      * Entraîne l'agent en collectant jusqu'à maxTimesteps transitions,
//      * et en effectuant une mise à jour PPO toutes les updateEveryN transitions.
//      */
//     public void train(int maxTimesteps, int updateEveryN, float gamma) {
//         List<float[]> states = new ArrayList<>();
//         List<Integer> actions = new ArrayList<>();
//         List<Float> rewards = new ArrayList<>();
//         List<Float> oldProbs = new ArrayList<>();
//         List<Float> values = new ArrayList<>();

//         // TODO : obtenir l'état initial de l'environnement (par ex. new float[stateDim] ou via un reset)
//         float[] state = new float[stateDim];

//         for (int t = 0; t < maxTimesteps; t++) {
//             // --- Collecte d'une transition via le réseau ---
//             // Convertir l'état Java en Tensor TFloat32 shape=(1, stateDim)
//             try (TFloat32 stateTensor = TFloat32.tensorOf(Shape.of(1, stateDim), data -> {
//                     for (int i = 0; i < stateDim; i++) {
//                         data.setFloat(state[i], 0, i);
//                     }
//                 })) {
//                 // Exécuter le réseau : on fetch la distribution et la valeur
//                 List<Tensor<?>> output = session.runner()
//                     .feed("state", stateTensor)
//                     .fetch("policy")  // distribution de probas des actions
//                     .fetch("value")   // estimation de la valeur V(s)
//                     .run();
//                 TFloat32 probsTensor = (TFloat32) output.get(0);
//                 TFloat32 valueTensor = (TFloat32) output.get(1);

//                 // Extraire les données de probsTensor dans un tableau Java
//                 int actionCount = (int) probsTensor.shape().size(1);
//                 FloatNdArray probsNd = NdArrays.ofFloats(Shape.of(1, actionCount));
//                 probsTensor.copyTo(probsNd);
//                 float[] probs = new float[actionCount];
//                 for (int i = 0; i < actionCount; i++) {
//                     probs[i] = probsNd.getFloat(0, i);
//                 }
//                 float value = valueTensor.getFloat(0);

//                 // Libérer les ressources TensorFlow temporaires
//                 output.forEach(Tensor::close);

//                 // Échantillonnage d'une action selon la distribution
//                 int action = sampleFromDistribution(probs);

//                 // Stocker la transition
//                 states.add(state.clone());
//                 actions.add(action);
//                 oldProbs.add(probs[action]);
//                 values.add(value);

//                 // TODO : appliquer l'action dans l'environnement et obtenir reward et nouvel état
//                 float reward = 0f;            // remplacer par l'appel à l'environnement
//                 rewards.add(reward);
//                 // TODO : mettre à jour 'state' avec le nouvel état retourné
//                 // state = ...

//             }

//             // --- Mise à jour PPO si on a collecté assez d'étapes ---
//             if ((t + 1) % updateEveryN == 0) {
//                 int batchSize = states.size();
//                 if (batchSize == 0) break;

//                 // Calculer les avantages et retours
//                 float[] advantages = computeAdvantages(rewards, values, gamma);
//                 float[] returns = new float[advantages.length];
//                 for (int i = 0; i < advantages.length; i++) {
//                     returns[i] = advantages[i] + values.get(i);
//                 }

//                 // Créer les tenseurs batch pour l'entraînement
//                 try (TFloat32 stateBatch = TFloat32.tensorOf(Shape.of(batchSize, stateDim), stData -> {
//                          for (int i = 0; i < batchSize; i++) {
//                              for (int j = 0; j < stateDim; j++) {
//                                  stData.setFloat(states.get(i)[j], i, j);
//                              }
//                          }
//                      });
//                      TInt32 actionBatch = TInt32.tensorOf(Shape.of(batchSize), acData -> {
//                          for (int i = 0; i < batchSize; i++) {
//                              acData.setInt(actions.get(i), i);
//                          }
//                      });
//                      TFloat32 advantageBatch = TFloat32.tensorOf(Shape.of(batchSize), advData -> {
//                          for (int i = 0; i < batchSize; i++) {
//                              advData.setFloat(advantages[i], i);
//                          }
//                      });
//                      TFloat32 oldProbBatch = TFloat32.tensorOf(Shape.of(batchSize), opData -> {
//                          for (int i = 0; i < batchSize; i++) {
//                              opData.setFloat(oldProbs.get(i), i);
//                          }
//                      });
//                      TFloat32 returnBatch = TFloat32.tensorOf(Shape.of(batchSize), retData -> {
//                          for (int i = 0; i < batchSize; i++) {
//                              retData.setFloat(returns[i], i);
//                          }
//                      })) {
//                     // Exécuter une étape d'optimisation PPO
//                     session.runner()
//                         .addTarget("train")   // op d'entraînement du graphe PPO
//                         .feed("state", stateBatch)
//                         .feed("action", actionBatch)
//                         .feed("advantage", advantageBatch)
//                         .feed("oldProbs", oldProbBatch)
//                         .feed("returns", returnBatch)
//                         .run();
//                 }

//                 // Nettoyer les listes pour la prochaine collecte
//                 states.clear();
//                 actions.clear();
//                 rewards.clear();
//                 oldProbs.clear();
//                 values.clear();
//             }
//         }
//     }

//     /** Tire un indice selon une distribution de probabilités discrète. */
//     private int sampleFromDistribution(float[] distribution) {
//         float r = random.nextFloat();
//         float cumProb = 0f;
//         for (int i = 0; i < distribution.length; i++) {
//             cumProb += distribution[i];
//             if (r < cumProb) {
//                 return i;
//             }
//         }
//         return distribution.length - 1;
//     }

//     /**
//      * Calcule l'avantage en faisant la somme actualisée des récompenses
//      * moins les valeurs de base. Le calcul part de la fin de la trajectoire.
//      */
//     private float[] computeAdvantages(List<Float> rewards, List<Float> values, float gamma) {
//         int n = rewards.size();
//         float[] advantages = new float[n];
//         float[] returns = new float[n];
//         float futureReturn = 0f;
//         for (int t = n - 1; t >= 0; t--) {
//             futureReturn = rewards.get(t) + gamma * futureReturn;
//             returns[t] = futureReturn;
//             advantages[t] = returns[t] - values.get(t);
//         }
//         return advantages;
//     }
// }