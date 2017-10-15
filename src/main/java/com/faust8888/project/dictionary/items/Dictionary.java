package com.faust8888.project.dictionary.items;


import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public final class Dictionary {

    private final Map<String, Word> wordMap;
    private final Iterator<String> wordIterator;

    public Dictionary(final Map<String,Word> wordMap) {
        this.wordMap = wordMap;
        this.wordIterator = wordMap.keySet().iterator();
    }

    public Stream<Map.Entry<String, Word>> getWordMapEntryStream() {
        return wordMap.entrySet().stream();
    }

    public Word getNextWord() {
        if(wordIterator.hasNext()) {
            return wordMap.get(wordIterator.next());
        } else {
             throw new RuntimeException("Dictionary is empty.");
        }
    }

    public boolean isEmpty() {
        return wordMap.keySet().size() > 0;
    }
}
