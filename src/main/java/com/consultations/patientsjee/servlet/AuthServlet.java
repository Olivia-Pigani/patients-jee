package com.consultations.patientsjee.servlet;

import com.consultations.patientsjee.dto.UserDTO;
import com.consultations.patientsjee.entity.User;
import com.consultations.patientsjee.dao.ext.UserBaseDAO;
import com.consultations.patientsjee.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/signform")
public class AuthServlet extends HttpServlet {



    private UserService userService;

    public AuthServlet() {
        userService = new UserService(new UserBaseDAO());
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
        String clearPassword = req.getParameter("password");


        if (userService.verifyIfAnEmailExist(email) == null){

            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setUserName(userName);
            newUserDTO.setEmail(email);
            newUserDTO.setPassword(clearPassword);

            if (userService.addAnUser(newUserDTO)){
                resp.sendRedirect(req.getContextPath() +"/signform?mode=signin");
            }
        }else {
            resp.sendRedirect(req.getContextPath() +"/signform?mode=signin&error=alreadySignUped");
        }




    }
    private void signInLogic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        User user = userService.getUserWithEmailAndPassword(userDTO);

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
