package com.faust8888.project.dictionary;


import com.faust8888.project.dictionary.db.items.*;
import com.faust8888.project.dictionary.viewItems.WordView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DictionaryTestUtils {

    public static final String NAME_DICTIONARY_TEST_FILE_BY_DEFAULT = "test_dictionary.xlsx";

    public static Dictionary createDictionaryTest(List<Map.Entry<String, WordView>> mapWordEntries) {
        User user = new User();
        user.setEmail("faust8888@mail.ru");
        user.setLogin("faust8888");
        user.setPassword("hashPassword");

        Dictionary dictionary = new Dictionary();
        dictionary.setName("My first dictionary");
        dictionary.setUser(user);
        List<DictionaryContent> contents = createDictionaryContents(dictionary, mapWordEntries);
        dictionary.setDictionaryContents(contents);

        return dictionary;
    }

    public static boolean isTestDictionaryFileExists() {
        File dictionaryFile = new File(NAME_DICTIONARY_TEST_FILE_BY_DEFAULT);
        return dictionaryFile.exists();
    }

    public static void deleteTestDictionaryFile() {
        File dictionaryFile = new File(NAME_DICTIONARY_TEST_FILE_BY_DEFAULT);
        dictionaryFile.delete();
    }

    private static List<DictionaryContent> createDictionaryContents(Dictionary dictionary, List<Map.Entry<String, WordView>> mapWordEntries) {
        List<DictionaryContent> result = new ArrayList<>(mapWordEntries.size());

        Iterator<Map.Entry<String, WordView>> iterator = mapWordEntries.iterator();
        while (iterator.hasNext()) {
            WordView wordView = iterator.next().getValue();
            Word word = convertToWord(wordView);

            DictionaryContent dictionaryContent = new DictionaryContent();
            dictionaryContent.setStatusId(WordStatusEnum.NORMAL.getStatusId());
            dictionaryContent.setWord(word);
            dictionaryContent.setDictionary(dictionary);

            result.add(dictionaryContent);
        }
        return result;
    }

    private static Word convertToWord(WordView wordView) {
        Word word = new Word();
        word.setWord(wordView.getWord());
        word.setMeaning(wordView.getMeaning());
        word.setTranslate(wordView.getTranslate());
        word.setContext(wordView.getContext());

        return word;
    }
}
