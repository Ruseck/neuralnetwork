package core;

public class Connection {
    private float weight;

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
}
