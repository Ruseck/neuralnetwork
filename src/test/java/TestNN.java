import core.NeuralNetwork;

public class TestNN {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork();
        nn.addLayer(2,1);
        System.out.println(nn.getResult(5,6));
        nn.train("trainingList",100);
        System.out.println(nn.getResult(5,6));
    }
}
