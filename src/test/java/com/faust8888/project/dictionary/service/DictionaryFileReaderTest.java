package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.items.Word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;


public class DictionaryFileReaderTest{

    private DictionaryFileReader dictionaryFileReader;

    @BeforeEach
    public void setDictionaryFileReader() {
        dictionaryFileReader = new DictionaryFileReader();
    }

    @Test
    @DisplayName("1. Read dictionary test")
    public void readDictionaryTest() throws Exception {
        List<Word> wordList = dictionaryFileReader.readDictionary("Words.xlsx");
        assertTrue(!wordList.isEmpty());
    }
}
