package com.myProject.service.command;

import com.myProject.entitie.User;
import com.myProject.service.UserManager;
import com.myProject.service.exception.AppException;
import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.myProject.util.Constants.PATH;
import static com.myProject.util.Constants.SHOW_USERS_COMMAND;
/**
 * Implementation of SHOW_USERS_COMMAND
 */
public class ShowUsers implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(ShowUsers.class);

    /**
     * prepares data for jsp page
     *
     * @return address of 'users_list.jsp'
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, AppException {
        logger.info("SHOW_USERS_COMMAND started");
        UserManager manager = (UserManager) req.getServletContext().getAttribute("UserManager");
        String strPage = req.getParameter("page");
        int currentPage = 1;
        if (strPage != null &&
                !"".equals(strPage) &&
                SHOW_USERS_COMMAND.equals(req.getSession().getAttribute("command_name"))) {
            currentPage = Integer.parseInt(strPage);
        }
        int pagesTotal = (int)Math. ceil(manager.findRowsTotal()/10d);
        List<User> userList = manager.findAllUsers((currentPage - 1) * 10, 10);
        req.getSession().setAttribute("page", currentPage);
        req.getSession().setAttribute("pages_total", pagesTotal);
        req.getSession().setAttribute("command_name", SHOW_USERS_COMMAND);
        req.getSession().setAttribute("result", userList);
        req.getSession().setAttribute("title", "command.show_users");
        return PATH + "users_list.jsp";
    }
}
