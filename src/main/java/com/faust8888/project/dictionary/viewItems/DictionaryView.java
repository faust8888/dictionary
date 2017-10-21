package com.faust8888.project.dictionary.viewItems;


import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public final class DictionaryView {

    private final Map<String, WordView> wordMap;
    private final Iterator<String> wordIterator;

    public DictionaryView(final Map<String,WordView> wordMap) {
        this.wordMap = wordMap;
        this.wordIterator = wordMap.keySet().iterator();
    }

    public Stream<Map.Entry<String, WordView>> getWordMapEntryStream() {
        return wordMap.entrySet().stream();
    }

    public WordView getNextWord() {
        if(wordIterator.hasNext()) {
            return wordMap.get(wordIterator.next());
        } else {
             throw new RuntimeException("DictionaryView is empty.");
        }
    }

    public boolean isEmpty() {
        return wordMap.keySet().size() > 0;
    }
}
