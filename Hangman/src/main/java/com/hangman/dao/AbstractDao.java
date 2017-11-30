package com.hangman.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hangman.db.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Turkmen on 29/11/2017
 */
// this is the base dao where I abstractly define DB operations 
public abstract class  AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass=(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.sessionFactory=HibernateUtil.getSessionFactory();
    }

    
    

    protected Session getSession(){
        return  HibernateUtil.getSessionFactory().openSession();

    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key){
        return (T) getSession().get(persistentClass,key);
    }


    public void persist(T entity){
        getSession().persist(entity);
    }
    
	public void merge(T entity) {
    	 getSession().update(entity);
    }

    public void delete(T entity){
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
}