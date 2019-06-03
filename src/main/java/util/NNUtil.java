package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class NNUtil {
    private NNUtil() {
    }

    public static float sigmoid(float val) {
        return (float) (1 / (1 + Math.pow(Math.E, -val)));
    }

    public static float random(int max) {
        return (float) (Math.random() * max);
    }

    public static void processSameElements(Consumer<List> func, List... lists) {
        for (int i = 0; i < lists[0].size(); i++) {
            List objects = new ArrayList<>();
            for (List list : lists) {
                objects.add(list.get(i));
            }
            func.accept(objects);
        }
    }

    public static <T> List<T> processSameElements(Function<List, T> func, List... lists) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < lists[0].size(); i++) {
            List objects = new ArrayList<>();
            for (List list : lists) {
                objects.add(list.get(i));
            }
            result.add(func.apply(objects));
        }
        return result;
    }

}
