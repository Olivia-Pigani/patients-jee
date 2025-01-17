package com.consultations.patientsjee.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/patientdetails")
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false); // get the actual session if it exists and doesn't produce a new one if it not exists

        if (session == null || session.getAttribute("user") == null){

            //memorize requested URL
            String requestURI = request.getRequestURI();
            String queryString = request.getQueryString();
            String originalUrl = requestURI + (queryString != null ? "?" + queryString : "");
            session = request.getSession(true);
            session.setAttribute("urlBeforeRedirect", originalUrl);

            response.sendRedirect(request.getContextPath() + "/signform");
        }else {
            filterChain.doFilter(request,response);
        }
    }
}
