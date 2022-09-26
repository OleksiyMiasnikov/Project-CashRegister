package com.myProject.service.command;

import com.myProject.dao.entitie.Role;
import com.myProject.dao.entitie.User;
import com.myProject.service.exception.DaoException;
import com.myProject.service.UserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.myProject.util.Constants.COMMAND_SHOW_USERS;


public class UpdateUser implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(UpdateUser.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException {
        String userId = req.getParameter("selectedUser");
        logger.info("Start updating user: " + userId);
        UserManager userManager = (UserManager) req.getSession().getServletContext().getAttribute("UserManager");
        User user = userManager.read(Long.parseLong(userId));
        req.setAttribute("user", user);
        List<Role> rolesList = userManager.findAllRoles();
        req.setAttribute("roles", rolesList);
        try {
            req.getRequestDispatcher("user_details.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        return "controller?command=" + COMMAND_SHOW_USERS;
    }
}
