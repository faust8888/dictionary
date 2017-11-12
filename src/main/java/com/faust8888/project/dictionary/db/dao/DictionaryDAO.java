package com.faust8888.project.dictionary.db.dao;


import com.faust8888.project.dictionary.db.items.*;
import org.hibernate.criterion.Restrictions;
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

    public void addWord(final Dictionary dictionary, final Word word) {
        DictionaryContent dictionaryContent = new DictionaryContent();
        dictionaryContent.setDictionary(dictionary);
        dictionaryContent.setWord(word);
        dictionaryContent.setStatusId(WordStatusEnum.NORMAL.getStatusId());

        dictionary.getDictionaryContents().add(dictionaryContent);
        update(dictionary); //TODO: In next time, i should repair this
    }

    public Dictionary getDictionaryByName(final String name) {
        return (Dictionary) createCriteria()
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    public Dictionary getDictionaryByUser(final User user) {
        return (Dictionary) createCriteria()
                .add(Restrictions.eq("user", user))
                .uniqueResult();
    }
}
