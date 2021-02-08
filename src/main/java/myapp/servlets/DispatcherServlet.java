package myapp.servlets;


import myapp.utils.URIDispatcher;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet implements Servlet {

    private final URIDispatcher dispatcher;

    public DispatcherServlet() {
        dispatcher = new URIDispatcher();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        dispatcher.getController(req.getRequestURI()).handle(req, resp);
    }
}
