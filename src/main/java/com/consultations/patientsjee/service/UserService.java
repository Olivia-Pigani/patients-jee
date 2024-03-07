package com.consultations.patientsjee.service;

import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.DAO.BaseDAO;
import com.consultations.patientsjee.DAO.ext.UserBaseDAO;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserService extends HibernateSession {

    //CR

    private Transaction tx = null;
    private BaseDAO<User> userBaseDAO;


    public UserService(BaseDAO<User> userBaseDAO) {
        this.userBaseDAO = userBaseDAO;
    }

    public boolean addAnUser(User newUser) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            userBaseDAO.setSession(session);

            tx = session.beginTransaction();

            userBaseDAO.add(newUser);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return false;
    }



    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            userBaseDAO.setSession(session);

            userList = userBaseDAO.getAll();


        } catch (Exception e) {

                System.out.println("There is no patients !");

                e.printStackTrace();

        }

        return userList;
    }

    public User getUserById(long userId) {
        User userToFind = new User();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            userBaseDAO.setSession(session);

            userToFind = userBaseDAO.getById(userId);
        } catch (Exception e) {

                e.printStackTrace();

        }
        return userToFind;
    }

    public User getUserWithEmailAndPassword(String email, String password){
        User userToFind = new User();
        try  (Session session = HibernateSession.getSessionFactory().openSession()) {
            UserBaseDAO castedRepo = (UserBaseDAO) userBaseDAO;
            castedRepo.setSession(session);
            return userToFind = castedRepo.getUserWithEmailAndPassword(email,password);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public User verifyIfAnEmailExist(String email){
        User userToFind = new User();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            UserBaseDAO castedRepo = (UserBaseDAO) userBaseDAO;
            castedRepo.setSession(session);
            return userToFind = castedRepo.verifyIfAnEmailExist(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
