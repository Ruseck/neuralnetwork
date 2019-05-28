package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Layer {
    private List<Neuron> neurons = new LinkedList<>();

    public Layer(int in, int nsize) {
        IntStream.range(0, nsize)
            .parallel()
            .map(ind -> in)
            .mapToObj(Neuron::new)
            .forEach(neurons::add);
    }

    public List<Float> train(List<Float> errors) {
        List<Float> nextErrors = new ArrayList<>();
        NNUtil.processSameElements((list) -> {
            float er = (float) list.get(0);
            Neuron neuron = (Neuron) list.get(1);
            List<Float> w = neuron.train(er);
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
        return errors;
    }
}
