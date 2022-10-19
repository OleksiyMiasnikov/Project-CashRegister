package com.myProject.service.command;

import com.myProject.employee.Employee;
import com.myProject.service.exception.AppException;
import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of BACK_COMMAND
 */
public class Back implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(Back.class);

    /**
     * pops list of menu items from stack and puts it into session attribute
     *
     * @return start command of current employee
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)
            throws DaoException, AppException {
        logger.info("BACK_COMMAND executed");
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        employee.popMenuItems();
        req.getSession().removeAttribute("pdf");
        req.getSession().setAttribute("employee", employee);
        return "controller?command=" + employee.getStartCommand();
    }
}
