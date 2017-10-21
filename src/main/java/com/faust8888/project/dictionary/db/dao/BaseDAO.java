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

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public T getById(Long id) {
        return getSession().get(type, id);
    }

    public S save(T entity) {
        return (S) getSession().save(entity);
    }

    public void flush() {
        getSession().flush();
        getSession().clear();;
    }
}
