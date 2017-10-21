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

    public Long saveWord(WordView wordView) {
        Word word = new Word();
        word.setWord(wordView.getWord());
        word.setMeaning(wordView.getMeaning());
        word.setTranslate(wordView.getTranslate());
        word.setContext(wordView.getContext());

        return save(word);
    }
}
