package com.consultations.patientsjee.servlets;
//test git
import com.consultations.patientsjee.dao.impl.PatientRepository;
import com.consultations.patientsjee.entities.Patient;
import com.consultations.patientsjee.service.PatientService;
import com.consultations.patientsjee.utils.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "patientsList", urlPatterns = "/patientslist")
public class PatientsServlet extends HttpServlet {


private PatientService patientService;

    @Override
    public void init() {
        patientService = new PatientService(new PatientRepository(),HibernateSession.getSessionFactory() );

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
           Patient patient = patientService.getPatientById(id);
           req.setAttribute("patient", patient);
           req.getRequestDispatcher("produit-details.jsp").forward(req,resp);
       }
    }

    private void patientsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("patients",patientService.getAllPatients());
        req.getRequestDispatcher("patients-list.jsp").forward(req,resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
