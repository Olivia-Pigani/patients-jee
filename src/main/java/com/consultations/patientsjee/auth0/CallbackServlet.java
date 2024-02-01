package com.consultations.patientsjee.auth0;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = "/callback")
public class CallbackServlet extends HttpServlet {
    private String redirectOnSuccess;
    private String redirectOnFail;
    private AuthenticationController authenticationController;

    @Override
    public void init() throws ServletException {
        super.init();
        redirectOnSuccess = "/patientdetails";
        redirectOnFail = "/auth";

        try {
            authenticationController = AuthenticationControllerProvider.getInstance();
        } catch (NamingException | UnsupportedEncodingException e) {
            throw new ServletException("Error initializing AuthenticationController", e);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handle(req, res);
    }

    private void handle(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            // Parse the request
            Tokens tokens = authenticationController.handle( req,  res);
            SessionUtils.set( req, "accessToken", tokens.getAccessToken());
            SessionUtils.set( req, "idToken", tokens.getIdToken());
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }
}
