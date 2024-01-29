package com.consultations.patientsjee.servlets;

import com.consultations.patientsjee.dao.impl.PatientDaoImpl;
import com.consultations.patientsjee.entities.Patient;
import com.consultations.patientsjee.service.ConsultationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "patientsList", urlPatterns = "/patientslist")
public class PatientsServlet extends HttpServlet {

    private ConsultationService consultationService;

    private PatientDaoImpl patientDao;


    @Override
    public void init() throws ServletException {
        patientDao = new PatientDaoImpl();
        consultationService = new ConsultationService(patientDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        //dofilter !

        switch (action){
            case "/patientslist":
                patientsList(req,resp);
                break;
            case "/details":
//                getPatientDetails(req,resp);
                break;
        }

    }

    private void getPatientDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (req.getParameter("id") != null){
           long id = Long.parseLong(req.getParameter("id"));
           Patient patient = consultationService.getPatientById(id);
           req.setAttribute("patient", patient);
           req.getRequestDispatcher("produit-details.jsp").forward(req,resp);
       }
    }

    private void patientsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("patients",consultationService.getAllPatients());
        req.getRequestDispatcher("patients-list.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
