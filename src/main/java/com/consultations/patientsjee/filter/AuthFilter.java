package com.consultations.patientsjee.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/patient-details.jsp")
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false); // get the actual session and doesn't produce a new one if it not exists

        if (session == null || session.getAttribute("user") == null){
            response.sendRedirect(request.getContextPath() + "/signform");
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
