package com.myProject.service.command;

import com.myProject.employee.Employee;
import com.myProject.entitie.Order;
import com.myProject.entitie.OrderDetails;
import com.myProject.service.CashierManager;
import com.myProject.service.exception.AppException;
import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.myProject.util.Constants.*;

/**
 * Implementation of SERVE_ORDER_COMMAND
 */
public class ServeOrder implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(ServeOrder.class);

    /**
     * prepares data for jsp page
     *
     * @return address of 'order.jsp'
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, AppException {
        logger.info("SERVE_ORDER_COMMAND started");
        CashierManager manager =
                (CashierManager) req.getServletContext()
                        .getAttribute("CashierManager");
        HttpSession session = req.getSession();
        String operation = req.getParameter("operation");
        if ("orders".equals(operation)) {
            req.setAttribute("operation", "Order");
        } else {
            req.setAttribute("operation", "Income");
        }
        String strId = req.getParameter("id");
        long id = Long.parseLong(strId);
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = manager.read(id);
            Employee.menuUp(session, List.of(BACK_COMMAND));
        }
        List<OrderDetails> orderDetailsList = manager.findAllOrderDetails(0, 1000, id);
        session.setAttribute("order", order);
        req.setAttribute("orderDetails", orderDetailsList);
        return PATH + "order.jsp";
    }
}
