package com.myProject.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/npe")
public class ErrorNpeServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger(ErrorNpeServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().append("NullPointerException");
        logger.info("NullPointerException");
    }
}