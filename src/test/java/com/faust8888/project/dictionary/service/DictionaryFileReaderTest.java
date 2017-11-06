package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.db.dao.WordDAO;

import com.faust8888.project.dictionary.viewItems.DictionaryView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.faust8888.project.dictionary.DictionaryTestUtils.NAME_DICTIONARY_TEST_FILE_BY_DEFAULT;
import static com.faust8888.project.dictionary.DictionaryTestUtils.deleteTestDictionaryFile;
import static com.faust8888.project.dictionary.DictionaryTestUtils.isTestDictionaryFileExists;


public class DictionaryFileReaderTest{

    private DictionaryFileReader dictionaryFileReader;

    @Autowired
    private WordDAO userDao;

    @Before
    public void prepareTests() {
        dictionaryFileReader = new DictionaryFileReader();
    }

    @After
    public void clearAfterTests() {
        deleteTestDictionaryFile();
    }

    @Test
    public void readDictionaryTest() throws Exception {
        DictionaryView dictionaryView = dictionaryFileReader.readDictionary("Words.xlsx");
        Assert.assertFalse("Dictionary was read, but it's empty.", !dictionaryView.isEmpty());
    }

    @Test
    public void writeDictionaryTest() throws Exception {
        DictionaryView dictionaryView = dictionaryFileReader.readDictionary("Words.xlsx");
        dictionaryFileReader.writeDictionary(dictionaryView, NAME_DICTIONARY_TEST_FILE_BY_DEFAULT);
        Assert.assertTrue("Dictionary file wasn't created", isTestDictionaryFileExists());
    }
}
