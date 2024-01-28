package com.consultations.patientsjee.service;

import com.consultations.patientsjee.dao.impl.PatientDaoImpl;
import com.consultations.patientsjee.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class ConsultationService {

    private PatientDaoImpl patientDao;

    public ConsultationService(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        try {
           patientList = patientDao.getAll();
           if (patientList != null){
               for (Patient p : patientList
                    ) {
                   System.out.println(p);
               }
               return patientList;
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("There is no patients !");
        return patientList;
    }

    public Patient getPatientById(long patientId) {
        Patient patientToFind = new Patient();
        try {
            patientToFind = patientDao.getById(patientId);
            if (patientToFind != null){
                return patientToFind;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Can't find the patient ! ");
        return patientToFind;
    }
}
