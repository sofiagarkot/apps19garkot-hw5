package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Collections;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> arrayList;
    private static AsIntStream instance;

    private AsIntStream(ArrayList<Integer> values) {
        arrayList = new ArrayList<>();
        arrayList.addAll(values);
    }

    public static IntStream of(int... values) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int each : values) {
            vals.add(each);
        }
        instance = new AsIntStream(vals);
        return instance;
    }

    @Override
    public Double average() {
        if (arrayList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return (double) instance.sum() / arrayList.size();
    }

    @Override
    public Integer max() {
        if (arrayList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return Collections.max(arrayList);
    }

    @Override
    public Integer min() {
        if (arrayList.size() == 0) {
            throw new IllegalArgumentException();
        }
        return Collections.min(arrayList);
    }

    @Override
    public long count() {
        return arrayList.size();
    }

    @Override
    public Integer sum() {
        if (arrayList.size() == 0) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for (int o : arrayList) {
            result += o;
        }
        return result;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int o : arrayList) {
            if (predicate.test(o)) {
                result.add(o);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int o : arrayList) {
            action.accept(o);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int o : arrayList) {
            int newO = mapper.apply(o);
            result.add(newO);
        }
        return new AsIntStream(result);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int o : arrayList) {
            IntStream res = func.applyAsIntStream(o);
            int[] nr = res.toArray();
            for (int e : nr) {
                result.add(e);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int i : arrayList) {
            result = op.apply(result, i);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            result[i] = arrayList.get(i);
        }
        return result;
    }

}
