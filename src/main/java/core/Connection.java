package core;

import util.NNUtil;

public class Connection {
    private float weight;
    private Perceptron in = new Perceptron();

    public Perceptron getIn() {
        return in;
    }

    public void setIn(Perceptron in) {
        this.in = in;
    }

    public Connection(float weight) {
        this.weight = weight;
    }

    public Connection() {
        weight = NNUtil.random(1);
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float multiply(Float aFloat) {
        in.setValue(aFloat);
        return weight * aFloat;
    }
}
