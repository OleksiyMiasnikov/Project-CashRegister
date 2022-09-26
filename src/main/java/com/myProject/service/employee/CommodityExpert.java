package com.myProject.service.employee;

import com.myProject.dao.entitie.User;
import com.myProject.dao.entitie.Warehouse;
import com.myProject.service.exception.DaoException;
import com.myProject.service.CommodityExpertManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.myProject.util.Constants.*;

public class CommodityExpert extends Employee {
    private static final Logger logger = (Logger) LogManager.getLogger(CommodityExpert.class);

    public CommodityExpert(User user) {
        super(user, "/WarehouseFragment", NEW_PRODUCT_COMMAND, LIST_OF_PRODUCT_COMMAND, NEW_INCOME_COMMAND, INCOMES_COMMAND, REMAINS_COMMAND, REPORTS_COMMAND);
    }


    public void initWindow(HttpServletRequest req, HttpServletResponse resp) throws DaoException {
        logger.info("initWindow start");
        CommodityExpertManager commodityExpertManager =
                (CommodityExpertManager) req
                        .getSession()
                        .getServletContext()
                        .getAttribute("CommodityExpertManager");
        List<Warehouse> warehouseList = commodityExpertManager.findAll();
        req.setAttribute("result", warehouseList);
        req.setAttribute("Fragment", "/WarehouseFragment");
        logger.info(Arrays.toString(warehouseList.toArray()));
        try {
            req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

