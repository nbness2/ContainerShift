package jv;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ArrayFunctional {
    public static <T> T[] partitionJoin(T[] arrayToPartition, Predicate<T> predicate) {
        final ArrayList<T> first = new ArrayList<>();
        final ArrayList<T> second = new ArrayList<>();
        for (T thing : arrayToPartition) {
            if (predicate.test(thing)) {
                first.add(thing);
            } else {
                second.add(thing);
            }
        }
        first.addAll(second);
        return (T[]) first.toArray();
    }

    public static <T> void forEachIndexed(T[] arrayToDoThingWith, BiConsumer<Integer, T> thingToDo) {
        int index = 0;
        for (T thing : arrayToDoThingWith) { thingToDo.accept(index++, thing); }
    }
}
