package com.myProject.service.command;

import com.myProject.service.CommodityExpertManager;
import com.myProject.entitie.Order;
import com.myProject.entitie.OrderDetails;
import com.myProject.entitie.Product;
import com.myProject.entitie.Warehouse;
import com.myProject.employee.Employee;
import com.myProject.service.exception.AppException;
import com.myProject.service.exception.DaoException;
import com.myProject.service.CashierManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.myProject.util.Constants.*;

/**
 * Implementation of NEW_ORDER_COMMAND and NEW_INCOME_COMMAND
 */
public class NewOrder implements Command {
    private Order currentOrder;
    private CashierManager cashierManager;
    private CommodityExpertManager commodityExpertManager;
    private String direction;
    private String operation;
    private String commandName;
    private static final Logger logger = (Logger) LogManager.getLogger(NewOrder.class);

    /**
     * makes control of order/income flows
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, AppException {
        logger.info("NEW_ORDER_COMMAND. Current order: " + currentOrder);
        cashierManager
                = (CashierManager) req.getServletContext().getAttribute("CashierManager");
        commodityExpertManager
                = (CommodityExpertManager) req.getServletContext().getAttribute("CommodityExpertManager");
        currentOrder = (Order) req.getSession().getAttribute("order");
        String button = req.getParameter("button");
        direction = req.getParameter("direction");
        if (direction == null) {
            direction = "OUT";
        }
        String exitCommandName;
        if ("IN".equals(direction)) {
            operation = "income";
            exitCommandName = "command.incomes";
            commandName = "command.new_income";
        } else {
            operation = "order";
            exitCommandName = "command.orders";
            commandName = "command.new_order";
        }
        if (button == null) {
            button = "First invoke";
        }
        switch (button) {
            case "Cancel":
                cashierManager.deleteOrder(currentOrder.getId(), "OUT");
            case "Complete":
                currentOrder = null;
                req.getSession().removeAttribute("order");
                req.getSession().removeAttribute("orderDetails");
                return "controller?command=" + exitCommandName;
            case "Log out":
                currentOrder = null;
                logger.info("Log out pressed");
                return "controller?command=command.log_out";
            case "Save":
                logger.info("Save pressed");
                addOrderDetails(req);
            default:
                showOrder(req);
                return PATH + "new_order.jsp";
        }
    }

    /**
     * gets data from jsp page and passes it to the CashManager to store in database
     */
    private void addOrderDetails(HttpServletRequest req) throws DaoException {
        if (currentOrder.getId() == 0) {
            currentOrder = cashierManager.createOrder(currentOrder, direction);
        }
        double price = Double.parseDouble(req.getParameter("newPrice"));
        double quantity = Double.parseDouble(req.getParameter("newQuantity"));
        Product product = commodityExpertManager.read(Long.parseLong(req.getParameter("newProductId")));
        OrderDetails orderDetail = cashierManager
                .findOrderDetailByOrderAndProduct(currentOrder, product);
        if (orderDetail != null && Double.compare(price, orderDetail.getPrice()) == 0) {
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
            cashierManager.updateOrderDetail(orderDetail);
        } else {
            orderDetail = OrderDetails.builder()
                    .id(0L)
                    .order(currentOrder)
                    .product(product)
                    .quantity(quantity)
                    .price(price)
                    .build();
            orderDetail = cashierManager.createOrderDetails(orderDetail, direction);
        }
        cashierManager.updateTotal(currentOrder.getId());
        currentOrder.setTotalAmount(cashierManager.read(currentOrder.getId()).getTotalAmount());
        logger.info("Added order details: " + orderDetail);
    }

    /**
     * prepares data and shows order or income
     */
    private void showOrder(HttpServletRequest req) throws DaoException {
        String strPage = req.getParameter("page");
        HttpSession session = req.getSession();
        List<Warehouse> warehouseList;
        int currentPage = 1;
        int pagesTotal = 1;
        if (currentOrder == null) {
            currentOrder = Order.builder()
                    .user(((Employee) session.getAttribute("employee")).getUser())
                    .date(new Timestamp(new Date().getTime()))
                    .build();
            Employee employee = (Employee) session.getAttribute("employee");
            employee.setMenuItems(List.of(COMPLETE_ORDER_COMMAND, CANCEL_ORDER_COMMAND, BACK_COMMAND));
            employee.setStackOfPages((String) session.getAttribute("previous_command"));
            session.setAttribute("employee", employee);
            session.removeAttribute("order");
            session.removeAttribute("orderDetails");
        } else {
            if (strPage != null &&
                    !"null".equals(strPage)) {
                currentPage = Integer.parseInt(strPage);
            }
            pagesTotal = (int) Math.ceil(cashierManager.findRowsTotalInOrderDetails(currentOrder.getId()) / 5d);
            List<OrderDetails> list =
                    cashierManager.findAllOrderDetails((currentPage - 1) * 5,
                            5,
                            currentOrder.getId());
            session.setAttribute("orderDetails", list);
        }
        if ("IN".equals(direction)) {
            warehouseList = new ArrayList<>();
            commodityExpertManager
                    .findAllProducts(0, 1000)
                    .forEach(p ->
                            warehouseList.add(Warehouse.builder()
                                    .id(0L)
                                    .quantity(100000d)
                                    .product(p)
                                    .build())
                    );
        } else {
            warehouseList = commodityExpertManager.findAll(0, 1000);
        }
        session.setAttribute("warehouse", warehouseList);
        session.setAttribute("page", currentPage);
        session.setAttribute("pages_total", pagesTotal);
        session.setAttribute("command_name", commandName);
        session.setAttribute("direction", direction);
        session.setAttribute("operation", operation);
        session.setAttribute("pcs", "шт");
        session.setAttribute("order", currentOrder);
        session.setAttribute("title", "command.new_" + operation);
        currentOrder = null;
    }
}
