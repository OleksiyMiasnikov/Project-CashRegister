<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <form action="menuCommand" method="post">
            <div class="sideBar">
                <c:forEach items="${sessionScope.menuItems}" var="item">
                   <input type="submit" value='${item}' id='${item}' name="menuButton">
                   <br>
                </c:forEach>
                <br>
                <br>
                <br>
                <br>
                <input type="submit" value="Log out" name="menuButton">
           </div>

           <div class="dataBox">
                <ul>
                    <c:forEach var="goods" items="${result}">
                        <li>
                            <input type="radio" id="radio" value="${goods.name}" name="goods" />
                            ${goods.name}
                            :
                            ${goods.unit}
                            :
                            ${goods.price}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </form>
    </body>
</html>