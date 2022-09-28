package com.myProject.service.command;

import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.myProject.util.Constants.LIST_OF_PRODUCT_COMMAND;

public class CreateProduct implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(CreateProduct.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        logger.info("Start");
        return "controller?command=" + LIST_OF_PRODUCT_COMMAND;
    }
}