package com.consultations.patientsjee.service;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.entity.MedicalForm;
import com.consultations.patientsjee.entity.Prescription;
import com.consultations.patientsjee.repository.ext.ConsultationRepository;
import com.consultations.patientsjee.repository.ext.MedicalFormRepository;
import com.consultations.patientsjee.repository.ext.PrescriptionRepository;
import com.consultations.patientsjee.repository.ext.UserRepository;
import com.consultations.patientsjee.utils.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ConsultationService extends HibernateSession {

    private Transaction tx = null;

    private ConsultationRepository consultationRepository;

    private MedicalFormRepository medicalFormRepository;

    private PrescriptionRepository prescriptionRepository;


    public ConsultationService(ConsultationRepository consultationRepository, MedicalFormRepository medicalFormRepository, PrescriptionRepository prescriptionRepository) {
        this.consultationRepository = consultationRepository;
        this.medicalFormRepository = medicalFormRepository;
        this.prescriptionRepository = prescriptionRepository;
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

    public Consultation getByIdOneConsultation(Long consultationId) {
        Consultation consultation = new Consultation();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            ConsultationRepository castedRepo = (ConsultationRepository) consultationRepository;
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

    public boolean addAConsultation(Consultation newConsultation) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
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


    public List<MedicalForm> getAllMedicalFormByConsultation(Long consultationId) {
        List<MedicalForm> medicalFormList = new ArrayList<>();
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            MedicalFormRepository castedRepo = (MedicalFormRepository) medicalFormRepository;
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
            PrescriptionRepository castedRepo = (PrescriptionRepository) prescriptionRepository;
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
