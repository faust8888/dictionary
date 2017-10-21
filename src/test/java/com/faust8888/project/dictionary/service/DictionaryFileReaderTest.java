package com.faust8888.project.dictionary.service;

import com.faust8888.project.dictionary.db.dao.WordDAO;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class DictionaryFileReaderTest{

    private DictionaryFileReader dictionaryFileReader;

    @Autowired
    private WordDAO userDao;

    @BeforeEach
    public void setDictionaryFileReader() {
        dictionaryFileReader = new DictionaryFileReader();
    }
}
