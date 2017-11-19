package com.faust8888.project.dictionary.db.dao;


import com.faust8888.project.dictionary.db.items.Dictionary;
import com.faust8888.project.dictionary.db.items.DictionaryContent;
import com.faust8888.project.dictionary.db.items.Word;
import com.faust8888.project.dictionary.db.items.WordStatusEnum;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class DictionaryContentDAO extends BaseDAO<DictionaryContent, Long> {

    public DictionaryContentDAO() {
        super(DictionaryContent.class);
    }

    public void addWord(final Dictionary dictionary, final Word word) {
        DictionaryContent dictionaryContent = new DictionaryContent();
        dictionaryContent.setDictionary(dictionary);
        dictionaryContent.setWord(word);
        dictionaryContent.setStatusId(WordStatusEnum.NORMAL.getStatusId());

        dictionary.getDictionaryContents().add(dictionaryContent);
        save(dictionaryContent);
    }
}
