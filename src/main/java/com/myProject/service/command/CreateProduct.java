package com.myProject.service.command;

import com.myProject.entitie.Product;
import com.myProject.entitie.Unit;
import com.myProject.service.CommodityExpertManager;
import com.myProject.service.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.myProject.util.Constants.LIST_OF_PRODUCT_COMMAND;

public class CreateProduct implements Command {
    private static final Logger logger = (Logger) LogManager.getLogger(CreateProduct.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DaoException, ServletException, IOException {
        logger.info("Start creating new product");
        String name = req.getParameter("newName");
        if ("".equals(name)) return "controller?command=" + LIST_OF_PRODUCT_COMMAND;
        CommodityExpertManager commodityExpertManager =
                                (CommodityExpertManager) req.getSession()
                                        .getServletContext()
                                        .getAttribute("CommodityExpertManager");
        String unit = req.getParameter("newUnit");
        String strPrice = req.getParameter("newPrice");
        String strId = req.getParameter("newId");
        logger.info(name + "-" + unit + "-" + strPrice + "-" + strId);
        long id = 0L;
        double price = 0;
        if (strPrice != null && !strPrice.equals("")) {
            price = Double.parseDouble(strPrice);
        }
        if (strId != null && !strId.equals("")) {
            id = Long.parseLong(strId);
        }
        Product newProduct = Product.builder()
                .id(id)
                .name(name)
                .unit(Unit.valueOf(unit))
                .price(price)
                .build();
        if (id == 0L) {
            if (commodityExpertManager.create(newProduct) != null) {
                logger.info(name + " added");
            } else {
                logger.info("Unable to add " + name);
            }
        } else {
            commodityExpertManager.update(newProduct);
            logger.info(name + " updated");
        }
        return "controller?command=command.list_of_products";
    }
}
