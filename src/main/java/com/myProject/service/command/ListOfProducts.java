package com.myProject.service.command;

import com.myProject.dao.entitie.Product;
import com.myProject.exception.DaoException;
import com.myProject.service.CommodityExpertManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ListOfProducts implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(ListOfProducts.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        logger.info("Start execute command -ListOfProducts-");
        req.getSession().setAttribute("Fragment", "/ProductListFragment");
        try {
            req.getRequestDispatcher("main_window.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
