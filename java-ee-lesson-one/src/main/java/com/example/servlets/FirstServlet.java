package com.example.servlets;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        servletResponse.getWriter().println("<h1>You are inside the servlet!</h1>");
        servletResponse.getWriter().println("<h2>You are inside the servlet!</h2>");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
