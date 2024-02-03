package com.consultations.patientsjee.service;

import com.consultations.patientsjee.dao.Repository;
import com.consultations.patientsjee.entities.Patient;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientService extends HibernateSession {

    private Repository<Patient> patientRepository;

    SessionFactory sessionFactory;

    public PatientService(Repository<Patient> patientRepository, SessionFactory sessionFactory) {
        this.patientRepository = patientRepository;
        this.sessionFactory = sessionFactory;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try {
           patientList = patientRepository.getAll();
           if (patientList != null){
               return patientList;
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("There is no patients !");
        return patientList;
    }

    public Patient getPatientById(long patientId) {
        Patient patientToFind = new Patient();
        try {
            patientToFind = patientRepository.getById(patientId);
            if (patientToFind != null){
                return patientToFind;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Can't find the patient ! ");
        return patientToFind;
    }

    public boolean addAPatient(Patient newPatient){
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            patientRepository.setSession(session);

            tx = session.beginTransaction();
            patientRepository.add(newPatient);
            tx.commit();
            return true;
        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
        }
        return false;
    }

    public boolean deleteAPatient(long patientId){
        Transaction tx = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Patient patient = patientRepository.getById(patientId);
            if (patient != null){
                patientRepository.delete(patient);
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
        }
        return false;
    }


    public boolean updateAPatient(long patientId, Patient updatedPatient){
        Transaction tx = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            Patient patientToUpdate = patientRepository.getById(patientId);
            if (patientToUpdate != null){
                patientToUpdate.setFirstName(updatedPatient.getFirstName());
                patientToUpdate.setLastName(updatedPatient.getLastName());
                patientToUpdate.setBirthDate(updatedPatient.getBirthDate());
                patientToUpdate.setImageUrl(updatedPatient.getImageUrl());

                return true;
            }

        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            session.close();
        }
        return false;
    }

}
