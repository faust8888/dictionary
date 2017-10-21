package com.faust8888.project.dictionary.db.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

class BaseDAO <T, S> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> type;

    protected BaseDAO(Class type) {
        this.type = type;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected T getById(Long id) {
        return getSession().get(type, id);
    }
}
