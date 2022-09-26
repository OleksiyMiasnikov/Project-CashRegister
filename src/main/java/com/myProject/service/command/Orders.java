package com.myProject.service.command;

import com.myProject.service.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.myProject.util.Constants.MAIN_PAGE;

public class Orders implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        //new Cashier(new User()).initWindow(req, resp);
        req.getSession().setAttribute("Fragment", "/CashierFragment");
        return MAIN_PAGE;
    }
}
