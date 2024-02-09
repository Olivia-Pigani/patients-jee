package com.consultations.patientsjee.repository.ext;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.repository.Repository;
import org.hibernate.query.Query;

import java.util.List;

public class ConsultationRepository extends Repository<Consultation> {

    @Override
    public Consultation getById(long elementId) {
        return session.get(Consultation.class,elementId);
    }


    @Override
    public List<Consultation> getAll() {
        Query<Consultation> consultationQuery = session.createQuery("from Consultation", Consultation.class);
        return consultationQuery.getResultList();
    }


    public List<Consultation> getAllConsultationByPatient(Long patientId) {
        Query<Consultation> consultationQuery = session.createQuery("from Consultation where patient_id =:patientId", Consultation.class);
        consultationQuery.setParameter("patientId",patientId);
        return consultationQuery.getResultList();
    }



}
