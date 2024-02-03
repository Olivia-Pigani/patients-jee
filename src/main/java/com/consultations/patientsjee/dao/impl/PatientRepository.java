package com.consultations.patientsjee.dao.impl;

import com.consultations.patientsjee.dao.Repository;
import com.consultations.patientsjee.entities.Patient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository extends Repository<Patient> {

    SessionFactory sessionFactory;

    public PatientRepository( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Patient getById(long elementId) {
        return session.get(Patient.class, elementId);
    }

    @Override
    public List<Patient> getAll() {

        session = sessionFactory.openSession();
        Query<Patient> query = session.createQuery("from Patient ", Patient.class);
        return query.getResultList();
    }


}
