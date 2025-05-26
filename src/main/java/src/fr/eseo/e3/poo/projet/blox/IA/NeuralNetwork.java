package src.fr.eseo.e3.poo.projet.blox.IA;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class NeuralNetwork {
    private final MultiLayerNetwork model;

    public NeuralNetwork(int inputSize, int outputSize) {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .updater(new Adam(0.0003))
            .list()
            .layer(new DenseLayer.Builder().nIn(inputSize).nOut(64)
                .activation(Activation.RELU)
                .build())
            .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                .nIn(64).nOut(outputSize)
                .activation(Activation.SOFTMAX)
                .build())
            .build();

        this.model = new MultiLayerNetwork(conf);
        this.model.init();
    }

    public MultiLayerNetwork getModel() {
        return model;
    }
}

// package src.fr.eseo.e3.poo.projet.blox.IA;

// import org.tensorflow.Graph;
// import org.tensorflow.Operand;
// import org.tensorflow.op.Ops;
// import org.tensorflow.op.Scope;
// import org.tensorflow.op.core.Placeholder;
// import org.tensorflow.op.core.Variable;
// import org.tensorflow.op.math.Add;
// import org.tensorflow.op.nn.Softmax;
// import org.tensorflow.types.TFloat32;
// import org.tensorflow.ndarray.Shape;

// public class NeuralNetwork {

//     private final Graph graph;
//     private final Ops tf;
//     private final Placeholder<TFloat32> state;
//     private final Operand<TFloat32> policyOutput;
//     private final Operand<TFloat32> valueOutput;

//     public NeuralNetwork() {
//         this.graph = new Graph();
//         this.tf = Ops.create(graph);

//         // Entrée : état du jeu (par exemple, 208 features)
//         this.state = tf.placeholder(TFloat32.class, Placeholder.shape(Shape.of(-1, 208)));

//         // --- Policy Network ---
//         Ops policyOps = tf.withSubScope("policy");
//         Variable<TFloat32> w1 = policyOps.variable(
//                 policyOps.reshape(
//                         policyOps.constant(new float[208 * 64]),
//                         policyOps.constant(new long[]{208, 64})
//                 )
//         );
//         Variable<TFloat32> b1 = policyOps.variable(
//                 policyOps.constant(new float[64])
//         );
//         Operand<TFloat32> hidden = policyOps.nn.relu(
//                 policyOps.math.add(
//                         policyOps.linalg.matMul(state, w1),
//                         b1
//                 )
//         );
//         Variable<TFloat32> wPolicy = policyOps.variable(
//                 policyOps.reshape(
//                         policyOps.constant(new float[64 * 5]),
//                         policyOps.constant(new long[]{64, 5})
//                 )
//         );
//         Variable<TFloat32> bPolicy = policyOps.variable(
//                 policyOps.constant(new float[5])
//         );
//         Add<TFloat32> logits = policyOps.math.add(
//                 policyOps.linalg.matMul(hidden, wPolicy),
//                 bPolicy
//         );
//         this.policyOutput = Softmax.create(policyOps.scope(), logits);

//         // --- Value Network ---
//         Ops valueOps = tf.withSubScope("value");
//         Variable<TFloat32> wValue1 = tf.variable(
//                 tf.reshape(
//                         tf.constant(new float[208 * 32]),
//                         tf.constant(new long[]{208, 32})
//                 )
//         );
//         Variable<TFloat32> bValue1 = tf.variable(
//                 tf.constant(new float[32])
//         );
//         Operand<TFloat32> hiddenValue = tf.nn.relu(
//                 tf.math.add(
//                         tf.linalg.matMul(state, wValue1),
//                         bValue1
//                 )
//         );
//         Variable<TFloat32> wValue2 = tf.variable(
//                 tf.reshape(
//                         tf.constant(new float[32 * 1]),
//                         tf.constant(new long[]{32, 1})
//                 )
//         );
//         Variable<TFloat32> bValue2 = tf.variable(
//                 tf.constant(new float[1])
//         );
//         this.valueOutput = tf.math.add(
//                 tf.linalg.matMul(hiddenValue, wValue2),
//                 bValue2
//         );
//     }

//     public Graph getGraph() {
//         return graph;
//     }

//     public Placeholder<TFloat32> getStatePlaceholder() {
//         return state;
//     }

//     public Operand<TFloat32> getPolicyOutput() {
//         return policyOutput;
//     }

//     public Operand<TFloat32> getValueOutput() {
//         return valueOutput;
//     }
// }
