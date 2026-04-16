package org.irri.iric.portal.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("OK");
    }
}

