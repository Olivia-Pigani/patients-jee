package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.repository.ext.ConsultationRepository;
import com.consultations.patientsjee.repository.ext.MedicalFormRepository;
import com.consultations.patientsjee.repository.ext.PatientRepository;
import com.consultations.patientsjee.entity.Patient;
import com.consultations.patientsjee.repository.ext.PrescriptionRepository;
import com.consultations.patientsjee.service.ConsultationService;
import com.consultations.patientsjee.service.PatientService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "patientsServlet", urlPatterns = {"/patientslist", "/patientdetails", "/addapatient"})
public class PatientsServlet extends HttpServlet {

    private PatientService patientService;
    private ConsultationService consultationService;

    public PatientsServlet() {
        patientService = new PatientService(new PatientRepository());
        consultationService = new ConsultationService(new ConsultationRepository(), new MedicalFormRepository(), new PrescriptionRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        String searchedQuery = req.getParameter("search");

        switch (action) {
            case "/patientslist":
                if (searchedQuery != null && !searchedQuery.isEmpty()) {
                    filterLogic(req, resp, searchedQuery);
                } else {
                    patientsList(req, resp);
                    break;
                }

            case "/patientdetails":
                getPatientDetails(req, resp);
                break;
            case "/addapatient":
                addAPatientLogic(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action){
           case "/addapatient"->PostAPatient(req,resp);

        }
    }

    private void PostAPatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String imageUrl = req.getParameter("imageUrl");
        Date birthDate = java.sql.Date.valueOf(req.getParameter("birthDate"));

        Patient newPatient = new Patient();
        newPatient.setFirstName(firstName);
        newPatient.setLastName(lastName);
        newPatient.setImageUrl(imageUrl);
        newPatient.setBirthDate(birthDate);

        patientService.addAPatient(newPatient);
        resp.sendRedirect(req.getContextPath() + "/patientslist");


    }

    private void getPatientDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Patient patient = patientService.getPatientById(id);
            List<Consultation> consultations = consultationService.getAllConsultations(id);
            req.setAttribute("patient", patient);
            req.setAttribute("consultations", consultations);
            req.getRequestDispatcher("WEB-INF/views/patient-details.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/patientslist");
        }
    }

    private void patientsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("patients", patientService.getAllPatients());
        req.getRequestDispatcher("patients-list.jsp").forward(req, resp);
    }

    private void filterLogic(HttpServletRequest req, HttpServletResponse resp, String searchedQuery) throws ServletException, IOException {
        req.setAttribute("patients", patientService.getAllFilteredPatients(searchedQuery));
        req.getRequestDispatcher("patients-list.jsp").forward(req, resp);

    }

    private void addAPatientLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/patient-formular.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
