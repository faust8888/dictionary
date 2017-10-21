package com.faust8888.project.dictionary.db.dao;


import com.faust8888.project.dictionary.db.items.DictionaryContent;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class DictionaryContentDAO extends BaseDAO<DictionaryContent, Long> {

    public DictionaryContentDAO() {
        super(DictionaryContent.class);
    }

}
