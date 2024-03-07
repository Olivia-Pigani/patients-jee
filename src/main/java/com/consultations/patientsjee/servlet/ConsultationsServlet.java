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

@WebServlet(name = "ConsultationsServlet", urlPatterns = {"/consultationslist", "/consultationdetails", "/add-consultation", "/delete-consultation"})
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
            case "/add-consultation" -> addAConsultationLogic(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/add-consultation" -> postAConsultation(req, resp);
            case "/delete-consultation" -> deleteConsultation(req, resp);
        }

    }

    private void deleteConsultation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String consultationIdStr = req.getParameter("consultationId");
        long patientId = Long.parseLong(req.getParameter("patientId"));
        if (consultationIdStr != null && !consultationIdStr.isEmpty() ){
            try {
                long consultationId = Long.parseLong(consultationIdStr);
                consultationService.deleteAConsultation(consultationId);

                resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);

        }
    }


    private void postAConsultation(HttpServletRequest req, HttpServletResponse resp) {

        try {
            long patientId = Long.parseLong(req.getParameter("patientId"));
            String doctorFirstName = req.getParameter("doctorFirstName");
            String doctorLastName = req.getParameter("doctorLastName");
            Date consulationDate;

            if (req.getParameter("dateConsultation").isEmpty() || req.getParameter("dateConsultation") == null) {
                consulationDate = java.sql.Date.from(Instant.now());
            } else {
                consulationDate = java.sql.Date.valueOf(req.getParameter("dateConsultation"));
            }

            String careType = req.getParameter("careType");
            int treatmentDuration = Integer.parseInt(req.getParameter("treatment-duration"));
            String pillType = req.getParameter("pillType");
            int prescriptionDuration = Integer.parseInt(req.getParameter("prescription-duration"));
            Patient patient = patientService.getPatientById(patientId);

            Prescription newPrescription = new Prescription();
            newPrescription.setPillType(pillType);
            newPrescription.setDuration(prescriptionDuration);

            MedicalForm newMedicalForm = new MedicalForm();
            newMedicalForm.setCareType(careType);
            newMedicalForm.setDuration(treatmentDuration);

            Consultation newConsultation = new Consultation();
            newConsultation.setDoctorFirstName(doctorFirstName);
            newConsultation.setDoctorLastName(doctorLastName);
            newConsultation.setDateConsultation(consulationDate);
            newConsultation.setPatient(patient);

            //Set bidirectionnal relationships
            newPrescription.setConsultation(newConsultation);
            newMedicalForm.setConsultation(newConsultation);
            newConsultation.setMedicalForm(newMedicalForm);
            newConsultation.setPrescription(newPrescription);

            consultationService.addAConsultation(newConsultation);

            resp.sendRedirect(req.getContextPath() + "/patientdetails?id=" + patientId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private void addAConsultationLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Patient patient = patientService.getPatientById(id);
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/views/consultation-formular.jsp").forward(req, resp);
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
