package com.example.test.task.dao;


import com.example.test.task.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    protected Session getCurrentSession()  {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public User createUser(String name, String password) {
        Session session = getCurrentSession();

        User newUser = new User(name, password);
        session.save(newUser);

        return newUser;
    }

    @Override
    public User getByName(String name) {
        Query query = getCurrentSession().createQuery("from User where name = :user_name");
        query.setParameter("user_name", name);
        List results = query.list();

        if(results.size() < 1){
            throw new UsernameNotFoundException("User '" + name + "' not found");
        }

        User user = (User) results.get(0);
        return user;
    }
}
