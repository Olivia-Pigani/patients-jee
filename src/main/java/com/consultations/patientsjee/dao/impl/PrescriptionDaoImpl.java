package com.consultations.patientsjee.dao.impl;

import com.consultations.patientsjee.dao.BaseDao;
import com.consultations.patientsjee.dao.InitDao;
import com.consultations.patientsjee.entities.Prescription;

import java.util.List;

public class PrescriptionDaoImpl extends InitDao implements BaseDao<Prescription> {

    @Override
    public boolean add(Prescription element) {
        return false;
    }

    @Override
    public Prescription getById(long elementId) {
        return null;
    }

    @Override
    public List<Prescription> getAll() {
        return null;
    }

    @Override
    public boolean update(long elementId) {
        return false;
    }

    @Override
    public boolean delete(long elementId) {
        return false;
    }
}
