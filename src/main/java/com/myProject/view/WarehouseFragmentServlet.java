package com.myProject.view;

import com.myProject.dao.entitie.Warehouse;
import com.myProject.exception.DaoException;
import com.myProject.service.CommodityExpertManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/WarehouseFragment")
public class WarehouseFragmentServlet extends HttpServlet {
    private static final Logger logger = (Logger) LogManager.getLogger(WarehouseFragmentServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("service start");

        CommodityExpertManager commodityExpertManager =
                (CommodityExpertManager) req
                        .getSession()
                        .getServletContext()
                        .getAttribute("CommodityExpertManager");
        List<Warehouse> warehouseList = null;
        try {
            warehouseList = commodityExpertManager.findAll();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        logger.info(Arrays.toString(warehouseList.toArray()));
        try (PrintWriter printWriter = resp.getWriter()){
            printWriter.write("[WarehouseFragmentServlet: /WarehouseFragmentFragment]");
            printWriter.write("<br>");
            printWriter.write("<div class=\"table_header\">");
            printWriter.write("<span class=\"table_header\" style=\"width: 50px;\">Id</span>");
            printWriter.write("<span class=\"table_header\" style=\"width: 200px;\">Product name</span>");
            printWriter.write("<span class=\"table_header\" style=\"width: 50px;\">Unit</span>");
            printWriter.write("<span class=\"table_header\" style=\"width: 100px;\">Quantity</span>");
            printWriter.write("<span class=\"table_header\" style=\"width: 100px;text-align: right;\">Price</span>");
            printWriter.write("</div>");

            printWriter.write("<br><hr>");

            for (Warehouse element : warehouseList) {
                printWriter.write("<span class=\"item\" style=\"width: 50px;text-align: center;\">");
                printWriter.write(String.valueOf(element.getId()));
                printWriter.write("</span>");
                printWriter.write("<span class=\"item\" style=\"width: 200px;\">");
                printWriter.write(element.getProduct().getName());
                printWriter.write("</span>");
                printWriter.write("<span class=\"item\" style=\"width: 50px;text-align: center;\">");
                printWriter.write(element.getProduct().getUnit().labelUa);
                printWriter.write("</span>");
                printWriter.write("<span class=\"item\" style=\"width: 100px;text-align: center;\">");
                printWriter.write(String.valueOf(element.getQuantity()));
                printWriter.write("</span>");
                printWriter.write("<span class=\"item\" style=\"width: 100px;text-align: right;\">");
                printWriter.write(String.valueOf(element.getProduct().getPrice()));
                printWriter.write("</span>");
                printWriter.write("<br>");
                printWriter.write("<hr>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("service finish");
    }
}
