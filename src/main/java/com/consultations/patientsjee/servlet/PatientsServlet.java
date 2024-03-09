package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.dao.ext.ConsultationBaseDAO;
import com.consultations.patientsjee.dao.ext.MedicalFormBaseDAO;
import com.consultations.patientsjee.dao.ext.PatientBaseDAO;
import com.consultations.patientsjee.entity.Patient;
import com.consultations.patientsjee.dao.ext.PrescriptionBaseDAO;
import com.consultations.patientsjee.service.ConsultationService;
import com.consultations.patientsjee.service.PatientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@WebServlet(name = "patientsServlet", urlPatterns = {"/patientslist", "/patientdetails", "/addapatient","/delete-patient"})
public class PatientsServlet extends HttpServlet {

    private PatientService patientService;
    private ConsultationService consultationService;

    public PatientsServlet() {
        patientService = new PatientService(new PatientBaseDAO());
        consultationService = new ConsultationService(new ConsultationBaseDAO(), new MedicalFormBaseDAO(), new PrescriptionBaseDAO());
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
           case "/delete-patient"->deleteAPatientLogic(req,resp);

        }
    }

    private void deleteAPatientLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long patientId = Long.parseLong(req.getParameter("patientId"));
        patientService.deleteAPatient(patientId);
        resp.sendRedirect(req.getContextPath() + "/patientslist");
    }

    private void PostAPatient(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        Date birthDate =  java.sql.Date.valueOf(req.getParameter("birthDate"));

        int randomNb = (int) ((Math.random()*10));
        String imageUrl =  randomNb < 5 ? "https://img.freepik.com/free-vector/illustration-user-avatar-icon_53876-5907.jpg?w=826&t=st=1706192993~exp=1706193593~hmac=a640026012b274e821b4b273acae9a804eb77191ccef63b5210d44effe47809b" : "https://img.freepik.com/free-vector/illustration-customer-service-concept_53876-5882.jpg?t=st=1706192975~exp=1706193575~hmac=1923f76a9fb4827c6f0216032cfe646d961a25ac28f640848ce1795f257cc3f8";
        System.out.println("image url :" + imageUrl);

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
