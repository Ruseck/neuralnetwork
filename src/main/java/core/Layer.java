package core;

import util.NNUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Layer {
    private List<Neuron> neurons = new LinkedList<>();

    public Layer(int in, int nsize) {
        IntStream.range(0, nsize)
                .map(ind -> in)
                .mapToObj(Neuron::new)
                .forEach(neurons::add);
    }

    public List<Float> guess(List<Float> in) {
        List<Float> result = new ArrayList<>();
        for (Neuron neuron : neurons) {
            result.add(neuron.guess(in));
        }
        return result;
    }

    public List<Float> correctWiehgts(List<Float> errors) {
        List<Float> nextErrors = new ArrayList<>();
        NNUtil.processSameElements((list) -> {
            float er = (float) list.get(0);
            Neuron neuron = (Neuron) list.get(1);
            List<Float> w = neuron.correctWeights(er);
            if (nextErrors.isEmpty()) {
                nextErrors.addAll(w);
            } else {
                List<Float> tempWeights = NNUtil.processSameElements((temp) -> {
                    float ww = (float) temp.get(0);
                    float err = (float) temp.get(1);
                    return ww + err;
                }, w, nextErrors);
                nextErrors.clear();
                nextErrors.addAll(tempWeights);
            }
        }, errors, neurons);
        return nextErrors;
    }
}
