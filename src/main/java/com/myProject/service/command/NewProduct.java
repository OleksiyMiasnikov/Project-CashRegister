package com.myProject.service.command;

import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewProduct implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(NewProduct.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        logger.info("--- NewProduct ---");
       // req.getRequestDispatcher("product_details.jsp").forward(req, resp);
        return "product_details.jsp";
    }
}
