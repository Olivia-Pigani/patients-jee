package com.consultations.patientsjee.service;

import com.consultations.patientsjee.dto.UserDTO;
import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.dao.BaseDAO;
import com.consultations.patientsjee.dao.ext.UserBaseDAO;
import com.consultations.patientsjee.utils.HibernateSession;
import com.consultations.patientsjee.utils.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService extends HibernateSession {

    //CR

    private Transaction tx = null;
    private BaseDAO<User> userBaseDAO;


    public UserService(BaseDAO<User> userBaseDAO) {
        this.userBaseDAO = userBaseDAO;
    }

    public boolean addAnUser(UserDTO newUserDTO) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            userBaseDAO.setSession(session);

            User newUser = new User();
            newUser.setUserName(newUserDTO.getUserName());
            newUser.setEmail(newUserDTO.getEmail());


            byte[] salt = PasswordUtils.getSalt();
            byte[] hashedPassword = PasswordUtils.hashPassword(newUserDTO.getPassword().toCharArray(),salt,10_000,256);
            newUser.setSalt(salt);
            newUser.setHashedPassword(hashedPassword);

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

                System.out.println("There is no users !");

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

    public User getUserWithEmailAndPassword(UserDTO userDTO){
        User userToFind = null;
        try  (Session session = HibernateSession.getSessionFactory().openSession()) {

            userToFind = verifyIfAnEmailExist(userDTO.getEmail());

            if (userToFind != null){
                byte[] correctHashedPassword = PasswordUtils.hashPassword(userDTO.getPassword().toCharArray(),userToFind.getSalt(),10_000,256);

                if (Arrays.equals(correctHashedPassword,userToFind.getHashedPassword())){
                    return userToFind;
                }
            }

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
