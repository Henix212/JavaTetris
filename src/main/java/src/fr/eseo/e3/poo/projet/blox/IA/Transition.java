package src.fr.eseo.e3.poo.projet.blox.IA;

public class Transition {
    public float[] state;
    public int action;
    public float reward;
    public float[] nextState;
    public boolean done;

    public float value;         // Valeur estimée de l’état courant (V(s))
    public float logProb;       // Log-probabilité de l’action (pour PPO)
    public float advantage;     // Avantage estimé (A(s,a))
    public float return_;       // Retour (discounted sum of rewards)

    public Transition(float[] state, int action, float reward, float[] nextState, boolean done,
                      float value, float logProb) {
        this.state = state;
        this.action = action;
        this.reward = reward;
        this.nextState = nextState;
        this.done = done;
        this.value = value;
        this.logProb = logProb;
    }

    public float[] getState() {
        return state;
    }

    public void setState(float[] state) {
        this.state = state;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public float[] getNextState() {
        return nextState;
    }

    public void setNextState(float[] nextState) {
        this.nextState = nextState;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getLogProb() {
        return logProb;
    }

    public void setLogProb(float logProb) {
        this.logProb = logProb;
    }

    public float getAdvantage() {
        return advantage;
    }

    public void setAdvantage(float advantage) {
        this.advantage = advantage;
    }

    public float getReturn_() {
        return return_;
    }

    public void setReturn_(float return_) {
        this.return_ = return_;
    }

}