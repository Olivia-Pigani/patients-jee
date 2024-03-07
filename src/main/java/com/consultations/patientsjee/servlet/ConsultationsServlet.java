package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.*;
import com.consultations.patientsjee.repository.ext.ConsultationRepository;
import com.consultations.patientsjee.repository.ext.MedicalFormRepository;
import com.consultations.patientsjee.repository.ext.PatientRepository;
import com.consultations.patientsjee.repository.ext.PrescriptionRepository;
import com.consultations.patientsjee.service.ConsultationService;
import com.consultations.patientsjee.service.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ConsultationsServlet", urlPatterns = {"/consultationslist", "/consultationdetails", "/add-consultation", "/delete-consultation", "/update-consultation"})
public class ConsultationsServlet extends HttpServlet {


    private ConsultationService consultationService;
    private PatientService patientService;

    public ConsultationsServlet() {
        consultationService = new ConsultationService(new ConsultationRepository(), new MedicalFormRepository(), new PrescriptionRepository());
        patientService = new PatientService(new PatientRepository());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/consultationslist" -> getConsultationsList(req, resp);
            case "/consultationdetails" -> getConsultationDetails(req, resp);
            case "/add-consultation", "/update-consultation" -> getConsultationFormular(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/add-consultation", "/update-consultation" -> postOrUpdateAConsultation(req, resp);
            case "/delete-consultation" -> deleteConsultation(req, resp);
        }

    }

    private void deleteConsultation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String consultationIdStr = req.getParameter("consultationId");
        long patientId = Long.parseLong(req.getParameter("patientId"));
        if (consultationIdStr != null && !consultationIdStr.isEmpty()) {
            try {
                long consultationId = Long.parseLong(consultationIdStr);
                consultationService.deleteAConsultation(consultationId);

                resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);

        }
    }


    private void postOrUpdateAConsultation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long patientId = Long.parseLong(req.getParameter("patientId"));
        Patient patient = patientService.getPatientById(patientId);

        String doctorFirstName = req.getParameter("doctorFirstName");
        String doctorLastName = req.getParameter("doctorLastName");

        Date consulationDate;
        if (req.getParameter("dateConsultation") == null || req.getParameter("dateConsultation").isEmpty()) {
            consulationDate = java.sql.Date.from(Instant.now());
        } else {
            consulationDate = java.sql.Date.valueOf(req.getParameter("dateConsultation"));
        }

        String careType = req.getParameter("careType");
        String pillType = req.getParameter("pillType");

        String treatmentDurationParam = req.getParameter("treatment-duration");
        int treatmentDuration = treatmentDurationParam != null && !treatmentDurationParam.isEmpty() ? Integer.parseInt(treatmentDurationParam) : 0;

        String prescriptionDurationParam = req.getParameter("prescription-duration");
        int prescriptionDuration = prescriptionDurationParam != null && !prescriptionDurationParam.isEmpty() ? Integer.parseInt(prescriptionDurationParam) : 0;



        Consultation consultation;
        Prescription prescription;
        MedicalForm medicalForm;
        String consultationIdStr = req.getParameter("consultationId");
        if (consultationIdStr != null && !consultationIdStr.isEmpty()) {
            long consultationId = Long.parseLong(consultationIdStr);
            consultation = consultationService.getByIdOneConsultation(consultationId);
            prescription = consultation.getPrescription();
            medicalForm = consultation.getMedicalForm();
        } else {
            consultation = new Consultation();
            prescription = new Prescription();
            medicalForm = new MedicalForm();

            //Set bidirectional relationships
            prescription.setConsultation(consultation);
            medicalForm.setConsultation(consultation);
            consultation.setMedicalForm(medicalForm);
            consultation.setPrescription(prescription);
        }


        consultation.setDoctorFirstName(doctorFirstName);
        consultation.setDoctorLastName(doctorLastName);
        consultation.setDateConsultation(consulationDate);
        consultation.setPatient(patient);

        prescription.setPillType(pillType);
        prescription.setDuration(prescriptionDuration);

        medicalForm.setCareType(careType);
        medicalForm.setDuration(treatmentDuration);





        consultationService.addOrUpdateAConsultation(consultation);

        resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);


    }


    private void getConsultationFormular(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("patientId"));
        Patient patient = patientService.getPatientById(id);
        req.setAttribute("patient", patient);

        String consultationIdStr = req.getParameter("consultationId");

        if (consultationIdStr == null || consultationIdStr.isEmpty()) {
            //See no data to Post a new consultation
            req.getRequestDispatcher("/WEB-INF/views/consultation-formular.jsp").forward(req, resp);
        } else {
            //See existing data to Update a consultation
            long consultationId = Long.parseLong(consultationIdStr);
            Consultation consultation = consultationService.getByIdOneConsultation(consultationId);
            Prescription prescription = consultation.getPrescription();
            MedicalForm mF = consultation.getMedicalForm();
            req.setAttribute("consultation", consultation);
            req.setAttribute("prescription", prescription);
            req.setAttribute("mF", mF);

            req.getRequestDispatcher("/WEB-INF/views/consultation-formular.jsp").forward(req, resp);


        }


    }

    private void getConsultationsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patientIdParam = req.getParameter("patientId");
        if (patientIdParam != null) {
            long patientId = Long.parseLong(patientIdParam);
            List<Consultation> consultations = consultationService.getConsultationsById(patientId);
            req.setAttribute("consultations", consultations);
            req.getRequestDispatcher("WEB-INF/views/consultations-list.jsp").forward(req, resp);
        }
    }


    private void getConsultationDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Consultation consultation = consultationService.getByIdOneConsultation(id);
            List<Prescription> prescriptions = consultationService.getAllPrescriptionOfAConsultation(id);
            List<MedicalForm> medicalForms = consultationService.getAllMedicalFormByConsultation(id);
            Patient patient = patientService.getAPatientByConsultationId(id);
            req.setAttribute("consultation", consultation);
            req.setAttribute("prescriptions", prescriptions);
            req.setAttribute("medicalForms", medicalForms);
            req.setAttribute("patient", patient);
            req.getRequestDispatcher("WEB-INF/views/consultation-details.jsp").forward(req, resp);

        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
