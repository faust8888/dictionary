package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.viewItems.DictionaryView;
import com.faust8888.project.dictionary.viewItems.WordView;
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

    private DictionaryView dictionaryView;

    public void installDictionary(final DictionaryView dictionaryView) {
        this.dictionaryView = dictionaryView;
    }

    public WordView getWord() {
        return dictionaryView.getWordMapEntryStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't get a word from dictionaryView."))
                .getValue();
    }

    public List<WordView> getWords(final int count) {
        return dictionaryView.getWordMapEntryStream()
                .limit(count)
                .map(entry -> {return dictionaryView.getNextWord();})
                .collect(Collectors.toList());
    }

    public Collection<WordView> getAllWords() {
        return dictionaryView.getWordMapEntryStream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
