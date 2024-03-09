package com.consultations.patientsjee.service;

import com.consultations.patientsjee.dao.BaseDAO;
import com.consultations.patientsjee.entity.Patient;
import com.consultations.patientsjee.dao.ext.PatientBaseDAO;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientService extends HibernateSession {

    private Transaction tx = null;
    private BaseDAO<Patient> patientBaseDAO;


    public PatientService(BaseDAO<Patient> patientBaseDAO) {
        this.patientBaseDAO = patientBaseDAO;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();

        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            patientBaseDAO.setSession(session);

            tx = session.beginTransaction();
            patientList = patientBaseDAO.getAll();

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
            patientBaseDAO.setSession(session);

            tx = session.beginTransaction();
            patientToFind = patientBaseDAO.getById(patientId);
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
            patientBaseDAO.setSession(session);

            tx = session.beginTransaction();
            patientBaseDAO.add(newPatient);
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
            patientBaseDAO.setSession(session);
            Patient patientToFind = patientBaseDAO.getById(patientId);
            if (patientToFind != null) {
                tx = session.beginTransaction();
                patientBaseDAO.delete(patientToFind);
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
            patientBaseDAO.setSession(session);

            Patient patientToUpdate = patientBaseDAO.getById(patientId);
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



    public Patient getAPatientByConsultationId(Long consultationId){
        Patient patient = new Patient();
        try (Session session = HibernateSession.getSessionFactory().openSession()){

            PatientBaseDAO castedRepo = (PatientBaseDAO) patientBaseDAO;
            castedRepo.setSession(session);
            tx = session.beginTransaction();
            patient = castedRepo.getAPatientByConsultationId(consultationId);
            tx.commit();
        }catch (Exception e){
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        }
        return patient;
    }

    public List<Patient> getAllFilteredPatients(String searchQuery){
    List<Patient> patientList = new ArrayList<>();
    try (Session session = HibernateSession.getSessionFactory().openSession()){
        PatientBaseDAO castedRepo = (PatientBaseDAO) patientBaseDAO;
        castedRepo.setSession(session);
        patientList = castedRepo.getAllFilteredPatients(searchQuery);


    }catch (Exception e){

            e.printStackTrace();

    }
    return patientList;
    }


}
