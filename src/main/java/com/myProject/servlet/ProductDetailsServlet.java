package com.myProject.servlet;

import com.myProject.dao.entitie.Product;
import com.myProject.dao.entitie.Unit;
import com.myProject.employee.Employee;
import com.myProject.exception.DaoException;
import com.myProject.service.ProductManager;
import com.myProject.service.command.Receiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/serveProduct")
public class ProductDetailsServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger(ProductDetailsServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if ("Cancel".equals(req.getParameter("button"))) {
            try {
                logger.info("'Cansel' has pressed");
                req.getRequestDispatcher("jsp/mainWindow.jsp").forward(req, resp);
                return;
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
        long id = 0L;
        double price = 0;
        String name = req.getParameter("newName");
        Unit unit = Unit.valueOfLabel(req.getParameter("newUnit"));

        String strPrice = req.getParameter("newPrice");
        if (strPrice != null && !strPrice.equals("")) {
            price = Double.parseDouble(strPrice);
        }

        String strId = req.getParameter("id");
        if (strId != null && !strId.equals("")) {
            id = Long.parseLong(strId);
        }

        try {
            ProductManager productManager =
                    (ProductManager) req.getSession()
                    .getServletContext()
                    .getAttribute("ProductManager");

            Product newProduct = new Product(id, name, unit, price);
            if (id == 0L) {
                if (productManager.create(newProduct) != null){
                    logger.info(name + " added");
                } else {
                    logger.info("Unable to add " + name);
                }
            } else {
                productManager.update(newProduct);
                logger.info(name + " updated");
            }
            //productManager = (ProductManager) req.getSession().getServletContext().getAttribute("ProductManager");
            List<Product> productList = productManager.findAllProducts();
            req.setAttribute("result", productList);
            req.setAttribute("Fragment", "/ProductListFragment");
            logger.info(Arrays.toString(productList.toArray()));
            req.getRequestDispatcher("jsp/mainWindow.jsp").forward(req, resp);
            // new Receiver().runCommand(req, resp, "COMMAND_LIST_OF_PRODUCT");
            //((Employee) req.getSession().getAttribute("Employee")).initWindow(req, resp);
           /* List<Product> productList = productManager.findAllProducts();
            req.setAttribute("result", productList);
            req.setAttribute("Window", "jsp/commodityExpertWindow.jsp");
            req.getRequestDispatcher("main").forward(req, resp);*/
        } catch (DaoException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}