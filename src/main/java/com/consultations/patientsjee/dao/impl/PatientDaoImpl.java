package com.consultations.patientsjee.dao.impl;

import com.consultations.patientsjee.dao.BaseDao;
import com.consultations.patientsjee.dao.InitDao;
import com.consultations.patientsjee.entities.Patient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl extends InitDao implements BaseDao<Patient> {


    @Override
    public boolean add(Patient element) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(element);
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

    @Override
    public Patient getById(long elementId) {
        Patient patientToFind = new Patient();
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, elementId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientToFind;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patientList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            Query<Patient> query = session.createQuery("from Patient ", Patient.class);
            return patientList = query.getResultList();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientList;
    }

    @Override
    public boolean update(long elementId, Patient element) {
        Transaction tx = null;
        Patient patientToFind = new Patient();
        try (Session session = sessionFactory.openSession()) {


           patientToFind = getById(elementId);

           tx = session.beginTransaction();

            if (patientToFind != null){
                patientToFind.setFirstName(element.getFirstName());
                patientToFind.setLastName(element.getLastName());
                patientToFind.setBirthDate(element.getBirthDate());
                patientToFind.setImageUrl(element.getImageUrl());

            tx.commit();
            return true;
            }


        }catch (Exception e){
            if (tx != null){
                tx.rollback();
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean delete(long elementId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession())  {

            if (getById(elementId) != null){
                session.delete(getById(elementId));
                tx.commit();
                return true;
            }

        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
                e.printStackTrace();
            }
        }

    return false;
    }
}
