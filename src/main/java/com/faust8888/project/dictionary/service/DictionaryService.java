package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.items.Dictionary;
import com.faust8888.project.dictionary.items.Word;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(scopeName = "singleton")
@Order(2)
public class DictionaryService {

    private Dictionary dictionary;

    public void installDictionary(final Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Word getWord() {
        return dictionary.getWordMapEntryStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't get a word from dictionary."))
                .getValue();
    }

    public List<Word> getWords(final int count) {
        return dictionary.getWordMapEntryStream()
                .limit(count)
                .map(entry -> {return dictionary.getNextWord();})
                .collect(Collectors.toList());
    }

    public Collection<Word> getAllWords() {
        return dictionary.getWordMapEntryStream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
