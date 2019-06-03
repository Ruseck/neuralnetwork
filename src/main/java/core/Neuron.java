package core;

import util.NNUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Neuron {
    private float learnRate = 0.1f;
    private List<Connection> weights = new LinkedList<>();
    private Connection bias = new Connection();
    private Perceptron perceptron = new Perceptron();

    public Neuron(int weights) {
        IntStream.range(0, weights)
                .forEach(i -> this.weights.add(new Connection()));
    }

    public float guess(List<Float> perceptronList) {
        float sum = 0;
        for (int i = 0; i < perceptronList.size(); i++) {
            sum += weights.get(i).multiply(perceptronList.get(i));
        }
        sum += bias.getWeight();
        perceptron.setValue(NNUtil.sigmoid(sum));
        return perceptron.getValue();
    }

    public List<Float> correctWeights(float error) {
        //e=w/Ew*error
        float errorDivideSum = error / ((float) weights.stream().mapToDouble(Connection::getWeight).sum() + bias.getWeight());
        List<Float> errors = weights.stream()
                .map(Connection::getWeight).map(w -> w * errorDivideSum).collect(Collectors.toList());
        float biasError = bias.getWeight() * errorDivideSum;
        bias.setWeight(bias.getWeight() + processError(biasError, bias.getWeight()));
        List<Float> result = new ArrayList<>();
        for (int i = 0; i < errors.size(); i++) {
            float in = weights.get(i).getIn().getValue();
            float w = weights.get(i).getWeight();
            float e = errors.get(i);
            float dw = processError(e, in);
            result.add(w + dw);
        }
        weights = result.stream().map(Connection::new).collect(Collectors.toList());
        return errors;
    }

    private float processError(float error, float in) {
        //dw=lr*e*in*(out-out^2)
        //in=out/w TODO optimize
        float out = perceptron.getValue();
        return learnRate * error * out * (1 - out) * in;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "learnRate=" + learnRate +
                ", weights=" + weights +
                ", bias=" + bias +
                '}';
    }
}
