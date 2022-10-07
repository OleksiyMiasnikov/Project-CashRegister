package com.myProject.service.command;

import com.myProject.service.exception.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.myProject.util.Constants.*;

public class Receiver {
    private static final Map<String, Command> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(AUTHORIZATION_COMMAND, new Authorization());
        commandMap.put(LOGOUT_COMMAND, new Logout());
        commandMap.put(ADD_USER_COMMAND, new AddUser());
        commandMap.put(ADD_USER_DETAILS_COMMAND, new AddUserDetails());
        commandMap.put(SHOW_USERS_COMMAND, new ShowUsers());
        commandMap.put(UPDATE_USER_COMMAND, new UpdateUser());
        commandMap.put(DELETE_USER_COMMAND, new DeleteUser());
        commandMap.put(NEW_ORDER_COMMAND, new NewOrder());
        commandMap.put(X_REPORT_COMMAND, new ReportX());
        commandMap.put(Z_REPORT_COMMAND, new ReportZ());
        commandMap.put(NEW_PRODUCT_COMMAND, new ServeProduct());
        commandMap.put(SERVE_ORDER_COMMAND, new ServeOrder());
        commandMap.put(LIST_OF_PRODUCT_COMMAND, new ListOfProducts());
        commandMap.put(NEW_INCOME_COMMAND, new NewIncome());
        commandMap.put(INCOMES_COMMAND, new Incomes());
        commandMap.put(CREATE_DATABASE_COMMAND, new CreateDatabase());
        commandMap.put(DELETE_ORDER_COMMAND, new DeleteOrder());
        commandMap.put(DELETE_ORDER_PRODUCT_COMMAND, new DeleteOrderedProduct());
        commandMap.put(CREATE_PRODUCT_COMMAND, new CreateProduct());
        commandMap.put(REMAINS_COMMAND, new Remains());
        commandMap.put(ORDERS_COMMAND, new Orders());
        commandMap.put(MOVIES_COMMAND, new Movies());
        commandMap.put(LOCALE_COMMAND, new ChangeLocale());
        commandMap.put(BACK_COMMAND, new Back());
        commandMap.put(PRINT_REPORT_COMMAND, new PrintReport());
        commandMap.put(SEND_REPORT_COMMAND, new SendReport());
    }

    public static Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
    public static String runCommand(HttpServletRequest req, HttpServletResponse resp, String commandName)
            throws NullPointerException, DaoException, ServletException, IOException {
        return commandMap.get(commandName).execute(req, resp);
    }
}
