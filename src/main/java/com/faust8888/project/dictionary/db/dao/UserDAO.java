package com.faust8888.project.dictionary.db.dao;

import com.faust8888.project.dictionary.db.items.User;
import com.faust8888.project.dictionary.db.items.Word;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserDAO extends BaseDAO<User,Long> {

    public UserDAO() {
        super(User.class);
    }

    public User getUserByLogin(final String login) {
        return (User) createCriteria()
                .add(Restrictions.eq("login", login))
                .uniqueResult();
    }
}
