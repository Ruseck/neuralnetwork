package core;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class Layer {
    private LinkedList<Neuron> neurons = new LinkedList<>();

    public Layer(int in, int nsize) {
        IntStream.range(0, nsize)
                .parallel()
                .map(ind -> in)
                .mapToObj(Neuron::new)
                .forEach(neurons::add);
    }
}
