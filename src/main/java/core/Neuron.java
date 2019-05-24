package core;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Neuron {
    private LinkedList<Connection> weights = new LinkedList<>();
    private float bias = NNUtil.random(1);
    private Perceptron perceptron;

    public Neuron(int weights) {
        IntStream.range(0, weights)
                .mapToObj(Connection::new)
                .forEach(this.weights::add);
    }

    public float guess(List<Float> perceptronList) {
        float sum = 0;
        for (int i = 0; i < perceptronList.size(); i++) {
            sum += perceptronList.get(i) * weights.get(i).getWeight();
        }
        sum+=bias;
        return sum;
    }

    public void train(float error){

    }
}
