package com.faust8888.project.dictionary.db.dao;


import com.faust8888.project.dictionary.db.items.Dictionary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@EnableTransactionManagement
public class DictionaryDAO extends BaseDAO<Dictionary, Long> {

    public DictionaryDAO() {
        super(Dictionary.class);
    }

}
