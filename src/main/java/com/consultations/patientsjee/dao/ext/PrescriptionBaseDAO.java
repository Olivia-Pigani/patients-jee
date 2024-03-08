package com.consultations.patientsjee.dao.ext;

import com.consultations.patientsjee.entity.Prescription;
import com.consultations.patientsjee.dao.BaseDAO;
import org.hibernate.query.Query;

import java.util.List;

public class PrescriptionBaseDAO extends BaseDAO<Prescription> {

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
