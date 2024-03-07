package com.consultations.patientsjee.DAO.ext;

import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.DAO.BaseDAO;
import org.hibernate.query.Query;

import java.util.List;

public class UserBaseDAO extends BaseDAO<User> {


    @Override
    public User getById(long elementId) {
        return session.get(User.class,elementId);
    }

    @Override
    public List<User> getAll() {
        Query<User> userQuery = session.createQuery("from User ", User.class);
        return userQuery.getResultList();
    }

    public User getUserWithEmailAndPassword(String email, String password){
        Query<User> userQuery = session.createQuery("from User where email = :email and password = :password", User.class);
        userQuery.setParameter("email",email);
        userQuery.setParameter("password",password);
        return userQuery.getSingleResult();
    }

    public User verifyIfAnEmailExist(String email){
        Query<User> userQuery = session.createQuery("from User where email = :email", User.class);
        userQuery.setParameter("email",email);
        return userQuery.getSingleResult();

    }


}
