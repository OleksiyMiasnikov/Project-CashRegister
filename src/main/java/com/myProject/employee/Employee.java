package com.myProject.employee;

import com.myProject.dao.entitie.User;
import com.myProject.util.ConnectionPool;
import com.myProject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Employee implements Serializable {
    private static final Logger logger = (Logger) LogManager.getLogger(ConnectionPool.class);
    private final User user;
    private final List<String> menuItems;
    private final String fragment;

    public User getUser() {
        return user;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }

    public String getFragment() {
        return fragment;
    }

    public Employee(User user, String fragment, String ... items) {
        this.user = user;
        this.fragment = fragment;
        this.menuItems = new ArrayList<>(Arrays.asList(items));
    }

    public static Employee createEmployee(User user) throws DaoException {
        switch (user.getRole().getName()) {
            case "admin": return new Admin(user);
            case "cashier": return new Cashier(user);
            case "commodity expert": return new CommodityExpert(user);
            case "senior cashier": return new SeniorCashier(user);
            default: {
                logger.error("Role is incorrect");
                throw new DaoException("Role is incorrect");
            }
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "user=" + user +
                ", menuItems=" + menuItems +
                '}';
    }
}
