package com.consultations.patientsjee.service;

import com.consultations.patientsjee.repository.Repository;
import com.consultations.patientsjee.entity.Patient;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientService extends HibernateSession {

    private Transaction tx = null;
    private Repository<Patient> patientRepository;


    public PatientService(Repository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            patientRepository.setSession(session);

            tx = session.beginTransaction();
            patientList = patientRepository.getAll();
            tx.commit();


        } catch (Exception e) {
            if (tx != null) {
                System.out.println("There is no patients !");
                tx.rollback();
                e.printStackTrace();
            }
        }

        return patientList;
    }

    public Patient getPatientById(long patientId) {
        Patient patientToFind = new Patient();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            patientRepository.setSession(session);

            tx = session.beginTransaction();
            patientToFind = patientRepository.getById(patientId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Can't find the patient ! ");
                tx.rollback();
                e.printStackTrace();
            }
        }
        return patientToFind;
    }

    public boolean addAPatient(Patient newPatient) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
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
        }
        return false;
    }

    public boolean deleteAPatient(long patientId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            patientRepository.setSession(session);
            Patient patientToFind = patientRepository.getById(patientId);
            if (patientToFind != null) {
                tx = session.beginTransaction();
                patientRepository.delete(patientToFind);
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
        }
        return false;
    }


    public boolean updateAPatient(long patientId, Patient updatedPatient) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            patientRepository.setSession(session);

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
        }
        return false;
    }

}
