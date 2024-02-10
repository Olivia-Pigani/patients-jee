package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.repository.ext.PatientRepository;
import com.consultations.patientsjee.repository.ext.UserRepository;
import com.consultations.patientsjee.service.PatientService;
import com.consultations.patientsjee.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.Query;

import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/signform")
public class AuthServlet extends HttpServlet {



    private UserService userService;

    public AuthServlet() {
        userService = new UserService(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("signout".equals(action)) {
            signOutLogic(req, resp);
        } else {
            req.getRequestDispatcher("auth-form.jsp").forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action){
            case "signup":
                signUpLogic(req,resp);
                break;
            case "signin":
                signInLogic(req,resp);
                break;
        }
    }

    private void signUpLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userName = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        if (userService.verifyIfAnEmailExist(email) == null){
            if (userService.addAnUser(newUser)){
                resp.sendRedirect("patient-details.jsp");
            }
        }else {
            resp.sendRedirect(req.getContextPath() +"/signform?mode=signin&error=alreadySignUped");
        }




    }
    private void signInLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.getUserWithEmailAndPassword(email,password);

        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            String redirectUrl = (String) session.getAttribute("urlBeforeRedirect");
            session.removeAttribute("urlBeforeRedirect"); // clean the session after using the attribute

            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                resp.sendRedirect(redirectUrl);
            } else {
                resp.sendRedirect(req.getContextPath() + "/patientslist");
            }
        } else {
            resp.sendRedirect(req.getContextPath() +"/signform?mode=signup&error=doesntHaveAccount");
        }


    }
    private void signOutLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/patientslist");
    }




}
