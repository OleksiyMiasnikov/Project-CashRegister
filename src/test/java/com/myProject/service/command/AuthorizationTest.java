package com.myProject.service.command;

import com.myProject.entitie.Role;
import com.myProject.entitie.User;
import com.myProject.service.UserManager;
import com.myProject.employee.Admin;
import com.myProject.employee.Employee;
import com.myProject.service.exception.AppException;
import com.myProject.service.exception.DaoException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.myProject.util.Constants.START_PAGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorizationTest {

    @Test
    void successfulAuthorizationTest() throws DaoException, AppException {
        UserManager manager = mock(UserManager.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletContext context = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(req.getParameter("login")).thenReturn("Alex");
        when(req.getParameter("password")).thenReturn("1111");
        when(req.getServletContext().getAttribute("UserManager")).thenReturn(manager);
        User user = new User(2,
                "Alex",
                "b59c67bf196a4758191e42f76670ceba",
                "alex@alex",
                Role.builder().id(1).name("admin").build());
        when(manager.findUser(anyString())).thenReturn(user);

        when(req.getSession().getAttribute("locale")).thenReturn("uk");
        Map<String, Object> attr = new HashMap<>();
        Mockito.doAnswer(aInvocation ->
                {
                    String key   = (String) aInvocation.getArguments()[0];
                    Object value = aInvocation.getArguments()[1];
                    attr.put(key, value);
                    return null;
                })
                .when(session).setAttribute(anyString(), any());
        String result = Receiver.runCommand(req, resp,"authorization");
        assertEquals(result,"controller?command=command.show_users");
        Employee employee = new Admin(user);
        assertEquals(attr.get("employee"), employee);

    }

    @Test
    void wrongPasswordTest() throws DaoException, AppException {
        UserManager manager = mock(UserManager.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletContext context = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        when(req.getSession()).thenReturn(session);
        when(req.getServletContext()).thenReturn(context);
        when(req.getParameter("login")).thenReturn("Alex");
        when(req.getParameter("password")).thenReturn("alex");
        when(req.getServletContext().getAttribute("UserManager")).thenReturn(manager);
        User user = User.builder()
                        .id(2L)
                        .login("Alex")
                        .password("b59c67bf196a4758191e42f76670ceba")
                        .email("alex@alex")
                        .role(Role.builder()
                                .id(1)
                                .name("admin")
                                .build())
                        .build();
        when(manager.findUser(anyString())).thenReturn(user);

        when(req.getSession().getAttribute("locale")).thenReturn("uk");
        Map<String, Object> attr = new HashMap<>();
        Mockito.doAnswer(aInvocation ->
                {
                    String key   = (String) aInvocation.getArguments()[0];
                    Object value = aInvocation.getArguments()[1];
                    attr.put(key, value);
                    return null;
                })
                .when(session).setAttribute(anyString(), any());
        String result = Receiver.runCommand(req, resp,"authorization");
        assertEquals(result,START_PAGE);
        assertEquals(attr.get("title"), "error_page_jsp");
        assertEquals(attr.get("incorrectUser"),"index_jsp.error");

    }
}