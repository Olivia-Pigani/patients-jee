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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = "/callback" )
public class CallbackServlet extends HttpServlet {

    private String redirectOnSuccess;
    private String redirectOnFail;
    private AuthenticationController authenticationController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        redirectOnSuccess = "/portal/home";
        redirectOnFail = "/login";
        try {
            authenticationController = AuthenticationControllerProvider.getInstance(config);
        } catch (UnsupportedEncodingException e) {


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
            Tokens tokens = authenticationController.handle((javax.servlet.http.HttpServletRequest) req, (javax.servlet.http.HttpServletResponse) res);
            SessionUtils.set((javax.servlet.http.HttpServletRequest) req, "accessToken", tokens.getAccessToken());
            SessionUtils.set((javax.servlet.http.HttpServletRequest) req, "idToken", tokens.getIdToken());
            res.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            res.sendRedirect(redirectOnFail);
        }
    }
}
