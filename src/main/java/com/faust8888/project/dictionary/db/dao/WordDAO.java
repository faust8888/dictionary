package com.faust8888.project.dictionary.db.dao;

import com.faust8888.project.dictionary.db.items.Word;

import com.faust8888.project.dictionary.viewItems.WordView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class WordDAO extends BaseDAO<Word,Long> {

    public WordDAO () {
        super(Word.class);
    }

}
