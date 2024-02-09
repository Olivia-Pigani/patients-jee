package com.consultations.patientsjee.service;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.repository.ext.ConsultationRepository;
import com.consultations.patientsjee.repository.ext.UserRepository;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ConsultationService extends HibernateSession {

    private Transaction tx = null;

    private ConsultationRepository consultationRepository;


    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public List<Consultation> getAllConsultations(Long patientId) {
        List<Consultation> consultationList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            ConsultationRepository castedRepo = (ConsultationRepository) consultationRepository;
            castedRepo.setSession(session);
            tx = session.beginTransaction();
            consultationList = castedRepo.getAllConsultationByPatient(patientId);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                System.out.println("There is no consultations !");
                tx.rollback();
                e.printStackTrace();
            }
        }

        return consultationList;
    }

    public List<Consultation> getConsultationsById(long consultationId) {
        List<Consultation> consultationsToFind = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            consultationRepository.setSession(session);

            tx = session.beginTransaction();
            consultationsToFind = consultationRepository.getAllConsultationByPatient(consultationId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Can't find consultations ! ");
                tx.rollback();
                e.printStackTrace();
            }
        }
        return consultationsToFind;
    }

    public boolean addAConsultation(Consultation newConsultation) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            consultationRepository.setSession(session);

            tx = session.beginTransaction();
            consultationRepository.add(newConsultation);
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

    public boolean deleteAConsultation(long consultationId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()){
            consultationRepository.setSession(session);
            Consultation consultationToFind = consultationRepository.getById(consultationId);
            if (consultationToFind != null) {
                tx = session.beginTransaction();
                consultationRepository.delete(consultationToFind);
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


    public boolean updateAConsultation(long consultationId, Consultation updatedConsultation) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            consultationRepository.setSession(session);

            Consultation consultationToUpdate = consultationRepository.getById(consultationId);
            if (consultationToUpdate != null) {
                tx = session.beginTransaction();
                consultationToUpdate.setDateConsultation(updatedConsultation.getDateConsultation());
                consultationToUpdate.setDoctorFirstName(updatedConsultation.getDoctorFirstName());
                consultationToUpdate.setDoctorLastName(updatedConsultation.getDoctorLastName());
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
