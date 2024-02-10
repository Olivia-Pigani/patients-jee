package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.Consultation;
import com.consultations.patientsjee.repository.ext.ConsultationRepository;
import com.consultations.patientsjee.service.ConsultationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConsultationsServlet", urlPatterns = {"/consultationslist","/consultationdetails"})
public class ConsultationsServlet extends HttpServlet {


private ConsultationService consultationService;

    public ConsultationsServlet() {
        consultationService = new ConsultationService(new ConsultationRepository());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action){
            case "/consultationslist":
                getConsultationsList(req,resp);
                break;
            case "/consultationdetails":
                getConsultationDetails(req,resp);
                break;
        }
    }

    private void getConsultationsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String patientIdParam = req.getParameter("patientId");
        if (patientIdParam != null) {
            long patientId = Long.parseLong(patientIdParam);
            List<Consultation> consultations = consultationService.getConsultationsById(patientId);
            req.setAttribute("consultations",consultations);
            req.getRequestDispatcher("WEB-INF/views/consultations-list.jsp").forward(req, resp);
        }
    }


    private void getConsultationDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            long id = Long.parseLong(req.getParameter("id"));
            Consultation consultation = consultationService.getByIdOneConsultation(id);
            req.setAttribute("consultation", consultation);
            req.getRequestDispatcher("WEB-INF/views/consultation-details.jsp").forward(req, resp);

        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }
}
