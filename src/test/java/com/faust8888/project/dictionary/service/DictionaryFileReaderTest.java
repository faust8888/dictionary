package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.items.Dictionary;
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
        Dictionary dictionary = dictionaryFileReader.readDictionary("Words.xlsx");
        assertTrue(dictionary != null && !dictionary.isEmpty());
    }
}
