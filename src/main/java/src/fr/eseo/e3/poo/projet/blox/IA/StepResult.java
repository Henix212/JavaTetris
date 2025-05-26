package src.fr.eseo.e3.poo.projet.blox.IA;

public class StepResult {
        public final float[] nextState;
        public final float reward;
        public final boolean done;

        public StepResult(float[] nextState, float reward, boolean done) {
            this.nextState = nextState;
            this.reward = reward;
            this.done = done;
        }
    }