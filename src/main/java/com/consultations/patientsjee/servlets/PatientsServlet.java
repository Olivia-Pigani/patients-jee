package com.consultations.patientsjee.servlets;

import com.consultations.patientsjee.dao.impl.PatientDaoImpl;
import com.consultations.patientsjee.service.ConsultationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class PatientsServlet extends HttpServlet {

    private ConsultationService consultationService;

    private PatientDaoImpl patientDao;


    @Override
    public void init() throws ServletException {
        consultationService = new ConsultationService(patientDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action){
            case "/patientsList":
                patientsList(req,resp);
                break;
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
