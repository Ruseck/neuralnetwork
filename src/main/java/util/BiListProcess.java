package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BiListProcess {
    public static <A, B, C> List<C> funcSameElements(BiFunction<A, B, C> func, List<A> first, List<B> second) {
        if (first.size() != second.size()) {
            String info = String
                    .format("Different lists's sizes first.size = %s, second.size = %s.", first.size(), second.size());
            throw new IllegalArgumentException(info);
        }
        List<C> result = new ArrayList<>();
        for (int i = 0; i < first.size(); i++) {
            result.add(func.apply(first.get(i), second.get(i)));
        }
        return result;
    }
    public static <A, B, C> void consumeSameElements(BiConsumer<A, B> func, List<A> first, List<B> second) {
        if (first.size() != second.size()) {
            String info = String
                    .format("Different lists's sizes first.size = %s, second.size = %s.", first.size(), second.size());
            throw new IllegalArgumentException(info);
        }
        for (int i = 0; i < first.size(); i++) {
            func.accept(first.get(i), second.get(i));
        }
    }
}
