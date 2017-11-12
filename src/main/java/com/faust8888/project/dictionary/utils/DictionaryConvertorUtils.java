package com.faust8888.project.dictionary.utils;

import com.faust8888.project.dictionary.db.items.Dictionary;
import com.faust8888.project.dictionary.db.items.DictionaryContent;
import com.faust8888.project.dictionary.db.items.Word;
import com.faust8888.project.dictionary.viewItems.WordView;
import com.faust8888.project.dictionary.viewItems.WordViewBuilder;

import java.util.HashMap;
import java.util.Map;


public class DictionaryConvertorUtils {

    public static Map<String, WordView> createWordMap(final Dictionary dictionary) {
        Map<String,WordView> mapWord = new HashMap<>(dictionary.getDictionaryContents().size());

        for(DictionaryContent content: dictionary.getDictionaryContents()) {
            WordView wordView = createWordView(content);
            mapWord.put(wordView.getWord(), wordView);
        }
        return mapWord;
    }

    public static Word createWord(final WordView wordView) {
        Word word = new Word();
        word.setWord(wordView.getWord());
        word.setMeaning(wordView.getMeaning());
        word.setTranslate(wordView.getTranslate());
        word.setContext(wordView.getContext());

        return word;
    }

    private static WordView createWordView(final DictionaryContent content) {
        Word word = content.getWord();

        WordViewBuilder wordViewBuilder = new WordViewBuilder();
        wordViewBuilder.setWord(word.getWord());
        wordViewBuilder.setMeaning(word.getMeaning());
        wordViewBuilder.setTranslate(word.getTranslate());
        wordViewBuilder.setContext(word.getContext());

        return wordViewBuilder.buildWord();
    }
}
