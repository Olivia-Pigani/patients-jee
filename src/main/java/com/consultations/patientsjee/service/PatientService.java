package com.consultations.patientsjee.service;

import com.consultations.patientsjee.dao.Repository;
import com.consultations.patientsjee.entities.Patient;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientService extends HibernateSession {

    private Session session = null;
    private Transaction tx = null;
    private Repository<Patient> patientRepository;


    public PatientService(Repository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void getSession() {
        session = HibernateSession.getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }



    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try {
            getSession();
            patientRepository.setSession(session);

            tx = session.beginTransaction();
            patientList = patientRepository.getAll();
            tx.commit();
        } catch (Exception e) {
            if (tx!=null){
                System.out.println("There is no patients !");
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            closeSession();
        }
        return patientList;
    }

    public Patient getPatientById(long patientId) {
        Patient patientToFind = new Patient();
        try {
            patientToFind = patientRepository.getById(patientId);
            if (patientToFind != null) {
                return patientToFind;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Can't find the patient ! ");
        return patientToFind;
    }

    public boolean addAPatient(Patient newPatient) {
        tx = null;
        session = null;
        try {
            getSession();
            patientRepository.setSession(session);

            tx = session.beginTransaction();
            patientRepository.add(newPatient);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            closeSession();
        }
        return false;
    }

    public boolean deleteAPatient(long patientId) {
        tx = null;
        session = null;

        try {
            getSession();

            Patient patient = patientRepository.getById(patientId);
            if (patient != null) {
                tx = session.beginTransaction();
                patientRepository.delete(patient);
                tx.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            closeSession();
        }
        return false;
    }


    public boolean updateAPatient(long patientId, Patient updatedPatient) {
        tx = null;
        session = null;

        try {
            getSession();
            Patient patientToUpdate = patientRepository.getById(patientId);
            if (patientToUpdate != null) {
                tx = session.beginTransaction();
                patientToUpdate.setFirstName(updatedPatient.getFirstName());
                patientToUpdate.setLastName(updatedPatient.getLastName());
                patientToUpdate.setBirthDate(updatedPatient.getBirthDate());
                patientToUpdate.setImageUrl(updatedPatient.getImageUrl());
                tx.commit();

                return true;
            }

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        } finally {
            closeSession();
        }
        return false;
    }

}
