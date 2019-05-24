package core;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private List<Layer> layers = new ArrayList<>();

    public void addLayer(int in, int size) {
        layers.add(new Layer(in, size));
    }

    public float getResult(float... in) {
        return 1L;
    }

    public void train(String trainingList, int epoch) {

    }
}
