package com.consultations.patientsjee.service;

import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.repository.Repository;
import com.consultations.patientsjee.repository.ext.UserRepository;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserService extends HibernateSession {

    //CR

    private Transaction tx = null;
    private Repository<User> userRepository;


    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addAnUser(User newUser) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            userRepository.setSession(session);

            tx = session.beginTransaction();
            userRepository.add(newUser);
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
            userRepository.setSession(session);

            userList = userRepository.getAll();


        } catch (Exception e) {

                System.out.println("There is no patients !");

                e.printStackTrace();

        }

        return userList;
    }

    public User getUserById(long userId) {
        User userToFind = new User();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            userRepository.setSession(session);

            userToFind = userRepository.getById(userId);
        } catch (Exception e) {

                e.printStackTrace();

        }
        return userToFind;
    }

    public User getUserWithEmailAndPassword(String email, String password){
        User userToFind = new User();
        try  (Session session = HibernateSession.getSessionFactory().openSession()) {
            UserRepository castedRepo = (UserRepository) userRepository;
            castedRepo.setSession(session);
            return userToFind = castedRepo.getUserWithEmailAndPassword(email,password);


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}