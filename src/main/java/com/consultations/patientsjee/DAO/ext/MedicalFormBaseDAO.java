package com.consultations.patientsjee.DAO.ext;

import com.consultations.patientsjee.entity.MedicalForm;
import com.consultations.patientsjee.DAO.BaseDAO;
import org.hibernate.query.Query;

import java.util.List;

public class MedicalFormBaseDAO extends BaseDAO<MedicalForm> {

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
