package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.repository.ext.PatientRepository;
import com.consultations.patientsjee.repository.ext.UserRepository;
import com.consultations.patientsjee.service.PatientService;
import com.consultations.patientsjee.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/authservlet")
public class AuthServlet extends HttpServlet {


    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        String action = req.getParameter("action");

        switch (action){
            case "signup":
                signUpLogic(req,resp);
                break;
            case "signin":
                signInLogic(req,resp);
                break;
            case "signout":
                signOutLogic(req,resp);
                break;
        }
    }

    private void signUpLogic(HttpServletRequest req, HttpServletResponse resp) {

        String userName = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        userService.addAnUser(newUser);



    }
    private void signInLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.getUserWithEmailAndPassword(email,password);

        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);

            resp.sendRedirect("patient-details.jsp");
        }


    }
    private void signOutLogic(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
        }

    }




}
