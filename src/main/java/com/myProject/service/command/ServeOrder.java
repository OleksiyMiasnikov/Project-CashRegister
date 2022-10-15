package com.myProject.service.command;

import com.myProject.entitie.Order;
import com.myProject.entitie.OrderDetails;
import com.myProject.service.CashierManager;
import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.myProject.util.Constants.PATH;
/**
 * Implementation of
 */
public class ServeOrder implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(ServeOrder.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        logger.info("--- ServeOrder ---");
        CashierManager manager =
                (CashierManager) req.getServletContext()
                        .getAttribute("CashierManager");
        String operation = req.getParameter("operation");
        if ("orders".equals(operation)) {
            req.setAttribute("operation", "Order");
        } else {
            req.setAttribute("operation", "Income");
        }
        String strId = req.getParameter("id");
        long id = Long.parseLong(strId);
        Order order = manager.read(id);
        List<OrderDetails> orderDetailsList = manager.findAllOrderDetails(0, 1000, id);
        req.setAttribute("order", order);
        req.setAttribute("orderDetails", orderDetailsList);
        return PATH + "order.jsp";
    }
}
