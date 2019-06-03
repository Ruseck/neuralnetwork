import util.NNUtil;
import core.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestNN {
    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2, 2, 2, 1);
        List<Float> list = new ArrayList<>();

        list.add(1f);
        list.add(-1f);

        System.out.println(nn.guess(list).get(0));

        List<List<Float>> tests = new ArrayList<>();
        tests.add(createList(1f, 1f));
        tests.add(createList(1f, -1f));
        tests.add(createList(-1f, 1f));
        tests.add(createList(-1f, -1f));
        List<List<Float>> answers = new ArrayList<>();
        answers.add(createList(-1));
        answers.add(createList(1));
        answers.add(createList(1));
        answers.add(createList(-1));

        for (int i = 0; i < 100; i++) {
            int testcase = (int) NNUtil.random(tests.size());
            List<Float> testCaseIn = tests.get(testcase);
            List<Float> testCaseOut = answers.get(testcase);
            nn.train(testCaseIn, testCaseOut);
        }
        NNUtil.processSameElements((objs) -> {
            List<Float> in = (List<Float>) objs.get(0);
            List<Float> answer = (List<Float>) objs.get(1);
            System.out.println("=============");
            System.out.println(in.stream().map(f -> f + "").collect(Collectors.joining(", ")));
            System.out.println(answer.stream().map(f -> f + "").collect(Collectors.joining(", ")));
            System.out.println(nn.guess(in));
        }, tests, answers);
    }

    private static List<Float> createList(float... val) {
        List<Float> temp = new ArrayList<>();
        for (float tempval : val) {
            temp.add(tempval);
        }
        return temp;
    }
}
