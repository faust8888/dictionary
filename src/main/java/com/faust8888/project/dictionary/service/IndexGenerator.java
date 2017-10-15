package com.faust8888.project.dictionary.service;


import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

public final class IndexGenerator {

    private PrimitiveIterator.OfInt randomIterator;

    public IndexGenerator(int min, int max) {
        randomIterator = new Random().ints(min, max + 1).iterator();
    }

    public int nextIndex() {
        return randomIterator.next();
    }

    public IntStream createRandomIndices(int limit, int low, int high) {
        return new Random().ints(limit, low, high);
    }
}
