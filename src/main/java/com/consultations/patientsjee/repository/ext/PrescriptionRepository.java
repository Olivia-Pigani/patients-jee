package com.consultations.patientsjee.repository.ext;

import com.consultations.patientsjee.entity.Prescription;
import com.consultations.patientsjee.repository.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class PrescriptionRepository extends Repository<Prescription> {

    @Override
    public Prescription getById(long elementId) {
        return null;
    }

    @Override
    public List<Prescription> getAll() {
        return null;
    }

    public List<Prescription> getAllPrescriptionOfAConsultation(Long consultationId){
        Query<Prescription> prescriptionQuery = session.createQuery("from Prescription where consultation_id =:consultationId", Prescription.class);
        prescriptionQuery.setParameter("consultationId",consultationId);
        return prescriptionQuery.getResultList();
    }

}
