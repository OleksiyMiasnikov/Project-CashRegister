<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>
    <body>
        <script src="js/order.js"></script>
        <%@ include file="header.jspf" %>

        <div class="dataBox">
            <h2 class="id">
                NEW ORDER
                <hr>
            </h2>
            <form action="serveNewOrder" method="post">
                <span>
                    Order id :
                </span>
                ${order.id}
                <br>
                Date :
                ${order.date}
                <br>
                Cashier :
                ${order.user.login}
                <br>
                <hr>

                    <select id="AllGoods">
                        <c:forEach var="item" items="${goods }">
                            <option value="${item.id }|${item.name }|${item.price }|${item.unit }">

                            </option>
                        </c:forEach>
                    </select>

                    <input  list="idList"
                            name="newGoodsId"
                            id="newGoodsId"
                            onchange="idUpdated();">
                    <datalist id="idList">
                        <c:forEach var="item" items="${goods }">
                            <option value="${item.id }">
                                ${item.id }
                            </option>
                        </c:forEach>
                    </datalist>
                    <input  class="id"
                            list="goodsList"
                            name="newGoods"
                            id="newGoods"
                            onchange="goodsUpdated();">
                    <datalist id="goodsList">
                        <c:forEach var="item" items="${goods }">
                            <option value="${item.name }">
                            ${item.name }
                            </option>
                        </c:forEach>
                    </datalist>
                    <select name="newPrice1" hidden="hidden">
                        <option> --- </option>
                        <c:forEach var="item" items="${goods }">
                            <option value="${item.price }">
                                ${item.price }
                            </option>
                        </c:forEach>
                    </select>
                    <select name="newUnit1" hidden="hidden">
                        <option> --- </option>
                        <c:forEach var="item" items="${goods }">
                            <option value="${item.unit }">
                                ${item.unit }
                            </option>
                        </c:forEach>
                    </select>
                    |
                    <input name="newQuantity" type="number" default value="1" onchange="newAmount.value = this.value*newPrice.value">
                    |
                    <input name="newUnit" disabled>
                    |
                    <input name="newPrice" type="number" step="0.01" onchange="newAmount.value = this.value*newQuantity.value">
                    |
                    <input name="newAmount" id="newAmount" disabled>
                    |
                    <input type="submit" name="button" value="Save">
                <br>
                <hr>
                Total Amount
                <input name="total" value="${order.totalAmount}" onchange="newTotal.value = this.value" disabled>
                <br>
                <ul>
                    <c:forEach var="element" items="${orderDetails}">
                        <li>
                            ${element.id}
                            |
                            ${element.order.id}
                            |
                            ${element.goods.name}
                            |
                            ${element.goods.unit}
                            |
                            ${element.quantity}
                            |
                            ${element.price}
                            <br>
                        </li>
                    </c:forEach>
                </ul>
                <hr>
                <br>
                <input type="submit" name="button" value="Complete">
                <input type="submit" name="button" value="Cancel">
               </div>
            </form>
        </div>
    </body>
</html>