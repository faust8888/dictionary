package com.faust8888.project.dictionary.db.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;

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
        return getSessionFactory().getCurrentSession().get(type, id);
    }

    public S save(T entity) {
        S id = (S) getSessionFactory().getCurrentSession().save(entity);
        getSessionFactory().getCurrentSession().flush();
        return id;
    }

    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }

    public Criteria createCriteria() {
        return getSessionFactory().getCurrentSession().createCriteria(type);
    }

    public void flush() {
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().clear();;
    }
}
