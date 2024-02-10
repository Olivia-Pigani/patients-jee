package com.consultations.patientsjee.repository.ext;

import com.consultations.patientsjee.entity.MedicalForm;
import com.consultations.patientsjee.entity.Prescription;
import com.consultations.patientsjee.repository.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class MedicalFormRepository extends Repository<MedicalForm> {

    @Override
    public MedicalForm getById(long elementId) {
        return null;
    }

    @Override
    public List<MedicalForm> getAll() {
        return null;
    }

    public List<MedicalForm> getAllMedicalFormByConsultation(Long consultationId){
        Query<MedicalForm> medicalFormQuery = session.createQuery("from MedicalForm where consultation_id =:consultationId", MedicalForm.class);
        medicalFormQuery.setParameter("consultationId",consultationId);
        return medicalFormQuery.getResultList();
    }
}
