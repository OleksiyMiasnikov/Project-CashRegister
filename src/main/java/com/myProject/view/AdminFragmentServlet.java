package com.myProject.view;

import com.myProject.dao.entitie.User;
import com.myProject.dao.entitie.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/AdminFragment")
public class AdminFragmentServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger(AdminFragmentServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("service start");
        List<User> userList;
        userList = (List<User>) req.getAttribute("result");
        try (PrintWriter printWriter = resp.getWriter()){
            /*
             <input type="radio" id="radio" value="${user.login}" name="users" />
                            ${user.login}
                            :
                            ${user.role.name}
             */

            for (User element : userList) {
                printWriter.write("<input type=\"radio\" id=\"radio\" value="
                                + element.getLogin()
                                +" name=\"users\" />");
                printWriter.write("--");
                printWriter.write(element.getLogin());
                printWriter.write("--");
                printWriter.write(String.valueOf(element.getRole().getName()));
                printWriter.write("<br>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("service finish");
    }
}