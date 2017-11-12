package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.db.dao.DictionaryContentDAO;
import com.faust8888.project.dictionary.db.dao.DictionaryDAO;
import com.faust8888.project.dictionary.db.dao.UserDAO;
import com.faust8888.project.dictionary.db.dao.WordDAO;
import com.faust8888.project.dictionary.db.items.*;
import com.faust8888.project.dictionary.db.items.Dictionary;
import com.faust8888.project.dictionary.utils.DictionaryConvertorUtils;
import com.faust8888.project.dictionary.viewItems.DictionaryInfoView;
import com.faust8888.project.dictionary.viewItems.DictionaryView;
import com.faust8888.project.dictionary.viewItems.WordView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(scopeName = "singleton")
@Order(2)
@Transactional
public class DictionaryService {

    @Autowired
    private WordDAO wordDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DictionaryDAO dictionaryDAO;
    @Autowired
    private DictionaryContentDAO dictionaryContentDAO;

    private DictionaryView dictionaryView;
    private Dictionary dictionary;

    public void installDictionary(final DictionaryView dictionaryView) {
        this.dictionaryView = dictionaryView;
    }

    public void installDictionaryUser(final User user) {
        Dictionary dictionary = dictionaryDAO.getDictionaryByUser(user);

        Map<String, WordView> mapWord = DictionaryConvertorUtils.createWordMap(dictionary);
        String dictionaryName = dictionary.getName();

        this.dictionaryView = new DictionaryView(dictionaryName, mapWord);
        this.dictionary = dictionary;
    }

    public void saveWord(final WordView wordView) {
        Word word = DictionaryConvertorUtils.createWord(wordView);
        dictionaryDAO.addWord(dictionary, word);
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

    public DictionaryInfoView getDictionaryInfo() {
        String dictionaryName = dictionaryView.getDictionaryName();
        Long countOfWords = dictionaryView.getWordMapEntryStream().count();

        return new DictionaryInfoView(dictionaryName, countOfWords);
    }

    public Collection<WordView> getAllWords() {
        return dictionaryView.getWordMapEntryStream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }
}
