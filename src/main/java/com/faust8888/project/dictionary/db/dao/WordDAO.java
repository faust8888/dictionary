package com.faust8888.project.dictionary.db.dao;

import com.faust8888.project.dictionary.db.items.Word;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class WordDAO extends BaseDAO<Word,Long> {

    public WordDAO () {
        super(Word.class);
    }

    public Word getWordById(Long id) {
        return getById(id);
    }
}
