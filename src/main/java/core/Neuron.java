package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Neuron {
    private float learnRate = 0.02f;
    private List<Connection> weights = new LinkedList<>();
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

    public List<Float> train(float error) {
        //e=w/Ew*error
        float errorDivideSum = (float) weights.stream().mapToDouble(Connection::getWeight).sum() / error;
        List<Float> errors = weights.stream()
            .map(Connection::getWeight).map(w -> w * errorDivideSum).collect(Collectors.toList());
        List<Float> result = new ArrayList<>();
        for (int i = 0; i < errors.size(); i++) {
            float w = weights.get(i).getWeight();
            float e = errors.get(i);
            float dw = processError(e, w);
            result.add(w + dw);
        }
        weights = result.stream().map(Connection::new).collect(Collectors.toList());
        return errors;
    }

    private float processError(float error, float weight) {
        //dw=lr*e*in*(out-out^2)
        //in=out/w TODO optimize
        float out = perceptron.getValue();
        return learnRate * error * out * (1 - out) * out / weight;
    }

}
