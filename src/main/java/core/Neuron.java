package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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
        sum += bias;
        return sum;
    }

    public void train(float error) {
        //e=w/Ew*error
        float errorDivideSum = (float) weights.stream().mapToDouble(Connection::getWeight).sum() / error;
        List<Float> errors = weights.stream()
                .map(Connection::getWeight).map(w -> w * errorDivideSum).collect(Collectors.toList());
        //dw=lr*e*in*(out-out^2)

    }


}
