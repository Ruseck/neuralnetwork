package core;

import util.NNUtil;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {//lkklkl

    public NeuralNetwork(int in, int... layers) {
        for (int layerSize : layers) {
            addLayer(in, layerSize);
            in = layerSize;
        }
    }

    private List<Layer> layers = new ArrayList<>();

    public void addLayer(int in, int size) {
        layers.add(new Layer(in, size));
    }

    public List<Float> guess(List<Float> in) {
        for (Layer layer : layers) {
            in = layer.guess(in);
        }
        return in;
    }

    public void train(List<Float> in, List<Float> shoulBe) {
        List<Float> out = guess(in);
        List<Float> errors =
                NNUtil.processSameElements((list) -> (float) list.get(0) - (float) list.get(1), shoulBe, out);
        correctWiehgts(errors);
    }

    private void correctWiehgts(List<Float> errors) {
        for (int i = layers.size() - 1; i >= 0; i--) {
            errors = layers.get(i).correctWiehgts(errors);
        }
    }
}
