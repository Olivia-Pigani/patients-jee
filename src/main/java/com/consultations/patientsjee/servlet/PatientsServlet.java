package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.repository.ext.PatientRepository;
import com.consultations.patientsjee.entity.Patient;
import com.consultations.patientsjee.service.PatientService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "patientsList", urlPatterns = "/patientslist")
public class PatientsServlet extends HttpServlet {
    @Inject
    private PatientService patientService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/patientslist":
                patientsList(req, resp);
                break;
            case "/details":
                getPatientDetails(req, resp);
                break;
        }

    }

    private void getPatientDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Patient patient = patientService.getPatientById(id);
            req.setAttribute("patient", patient);
            req.getRequestDispatcher("patient-details.jsp").forward(req, resp);
        }
    }

    private void patientsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("patients", patientService.getAllPatients());
        req.getRequestDispatcher("patients-list.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
