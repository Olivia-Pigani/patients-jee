package com.consultations.patientsjee.service;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.entity.MedicalForm;
import com.consultations.patientsjee.entity.Prescription;
import com.consultations.patientsjee.DAO.ext.ConsultationBaseDAO;
import com.consultations.patientsjee.DAO.ext.MedicalFormBaseDAO;
import com.consultations.patientsjee.DAO.ext.PrescriptionBaseDAO;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ConsultationService extends HibernateSession {

    private Transaction tx = null;

    private ConsultationBaseDAO consultationRepository;

    private MedicalFormBaseDAO medicalFormRepository;

    private PrescriptionBaseDAO prescriptionRepository;


    public ConsultationService(ConsultationBaseDAO consultationRepository, MedicalFormBaseDAO medicalFormRepository, PrescriptionBaseDAO prescriptionRepository) {
        this.consultationRepository = consultationRepository;
        this.medicalFormRepository = medicalFormRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public List<Consultation> getAllConsultations(Long patientId) {
        List<Consultation> consultationList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            ConsultationBaseDAO castedRepo = (ConsultationBaseDAO) consultationRepository;
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

    public Consultation getByIdOneConsultation(Long consultationId) {
        Consultation consultation = new Consultation();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            ConsultationBaseDAO castedRepo = (ConsultationBaseDAO) consultationRepository;
            castedRepo.setSession(session);
            tx = session.beginTransaction();
            consultation = castedRepo.getById(consultationId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Can't find the consultation ! ");
                tx.rollback();
                e.printStackTrace();
            }
        }
        return consultation;
    }


    public List<Consultation> getConsultationsById(long consultationId) {
        List<Consultation> consultationsToFind = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
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


    public boolean deleteAConsultation(long consultationId) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
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



    public List<MedicalForm> getAllMedicalFormByConsultation(Long consultationId) {
        List<MedicalForm> medicalFormList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            MedicalFormBaseDAO castedRepo = (MedicalFormBaseDAO) medicalFormRepository;
            castedRepo.setSession(session);
            tx = session.beginTransaction();
            medicalFormList = castedRepo.getAllMedicalFormByConsultation(consultationId);
            tx.commit();


        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Can't find medical forms ! ");
                tx.rollback();
                e.printStackTrace();
            }
        }
        return medicalFormList;


    }

    public List<Prescription> getAllPrescriptionOfAConsultation(Long consultationId) {
        List<Prescription> prescriptionList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            PrescriptionBaseDAO castedRepo = (PrescriptionBaseDAO) prescriptionRepository;
            castedRepo.setSession(session);
            tx = session.beginTransaction();
            prescriptionList = castedRepo.getAllPrescriptionOfAConsultation(consultationId);
            tx.commit();


        } catch (Exception e) {
            if (tx != null) {
                System.out.println("Can't find prescriptions ! ");
                tx.rollback();
                e.printStackTrace();
            }
        }
        return prescriptionList;


    }


    public void addOrUpdateAConsultation(Consultation consultation) {

        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            consultationRepository.setSession(session);

            Consultation consultationToUpdate = consultationRepository.getById(consultation.getId());
            tx = session.beginTransaction();

            if (consultationToUpdate == null) {
                consultationRepository.add(consultation);

            } else {
                consultationToUpdate.setDateConsultation(consultationToUpdate.getDateConsultation());
                consultationToUpdate.setDoctorFirstName(consultationToUpdate.getDoctorFirstName());
                consultationToUpdate.setDoctorLastName(consultationToUpdate.getDoctorLastName());
                consultationToUpdate.setMedicalForm(consultationToUpdate.getMedicalForm());
                consultationToUpdate.setPrescription(consultationToUpdate.getPrescription());
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        }

    }
}
