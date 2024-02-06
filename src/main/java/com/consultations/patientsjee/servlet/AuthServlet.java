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
import org.hibernate.query.Query;

import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/authservlet")
public class AuthServlet extends HttpServlet {



    private UserService userService;

    public AuthServlet() {
        userService = new UserService(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);


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
            case "signout":
                signOutLogic(req,resp);
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
            System.out.println(email);
            if (userService.addAnUser(newUser)){
                resp.sendRedirect("patient-details.jsp");
            }
        }else {
            resp.sendRedirect("auth-form.jsp?mode=signin&error=alreadySignUped");
        }




    }
    private void signInLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.getUserWithEmailAndPassword(email,password);

        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);

            resp.sendRedirect("patient-details.jsp");
        } else {
            resp.sendRedirect("auth-form.jsp?mode=signup&error=doesntHaveAccount");
        }


    }
    private void signOutLogic(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
        }

    }




}
