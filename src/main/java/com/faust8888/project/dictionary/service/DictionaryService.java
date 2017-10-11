package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.items.Word;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(scopeName = "singleton")
@Order(2)
public class DictionaryService {


    private List<Word> words;


    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
