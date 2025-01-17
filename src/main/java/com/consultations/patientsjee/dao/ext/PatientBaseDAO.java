package com.consultations.patientsjee.dao.ext;

import com.consultations.patientsjee.dao.BaseDAO;
import com.consultations.patientsjee.entity.Patient;
import org.hibernate.Query;

import java.util.List;

public class PatientBaseDAO extends BaseDAO<Patient> {



    @Override
    public Patient getById(long elementId) {
        return session.get(Patient.class, elementId);
    }

    @Override
    public List<Patient> getAll() {
        Query<Patient> patientQuery = session.createQuery("from Patient ", Patient.class);
        return patientQuery.getResultList();
    }

    public Patient getAPatientByConsultationId(Long consultationId){
        Query<Patient> patientQuery = session.createQuery("select c.patient from Consultation c where c.id=:consultationId", Patient.class);
        patientQuery.setParameter("consultationId",consultationId);
        return patientQuery.getSingleResult();
    }

    public List<Patient> getAllFilteredPatients(String searchQuery){
        String searchedPattern = searchQuery + "%";
        Query<Patient> patientQuery = session.createQuery("from Patient p where lower(p.firstName) like :searchedPattern or lower(p.lastName) like :searchedPattern", Patient.class);
        patientQuery.setParameter("searchedPattern",searchedPattern);
        return patientQuery.getResultList();

    }
}
