<!DOCTYPE html>
<html>
<body>
        <%@ include file="/jsp/header.jspf" %>
        <script src=js/pagination.js></script>
            <div class="dataBox">
                <span style="font-size: 8px;">
                    [report_x.jsp]
                </span>
                <p style="text-align: center; font-size: 22px;font-weight: bold">
                    *** Report X ***
                </p>
                <hr>
                <span class="header_key" style="width: 170px;">
                    Senior cashier :
                </span>
                <span>
                    ${employee.user.login}
                </span>
                <br>
                <div class=table_header>
                    <span class=table_header style="width: 50px;">
                        <fmt:message key="data_id"/>
                    </span>
                    <span class=table_header style="width: 170px;">
                        <fmt:message key="data_product_name"/>
                    </span>
                    <span class=table_header style="width: 50px;">
                        <fmt:message key="data_unit"/>
                    </span>
                    <span class=table_header style="width: 70px;text-align: center;">
                        <fmt:message key="data_quantity"/>
                    </span>
                    <span class=table_header style="width: 70px;text-align: right;">
                        <fmt:message key="data_price"/>
                    </span>
                    <span class=table_header style="width: 80px;text-align: right;">
                        <fmt:message key="data_amount"/>
                    </span>
                </div>
                <br>
                <hr>
                <div class=data_list>
                    <c:forEach var="item" items="${reports}">
                        <span class="item" style="width: 50px;text-align: center;">
                            ${item.productId}
                        </span>
                        <span class="item" style="width: 170px;text-align: center;">
                            ${item.productName}
                        </span>
                        <span class="item" style="width: 50px;text-align: center;">
                            <fmt:message key="${item.unit}"/>
                        </span>
                        <span class="item" style="width: 70px;text-align: center;">
                            ${item.quantity}
                        </span>
                        <span class="item" style="width: 70px;text-align: right;">
                            ${item.price}
                        </span>
                        <input value="${item.price * item.quantity}"
                                type="number"
                                step="0.01"
                                style="width: 80px;
                                text-align: right;"
                                disabled>
                        <br>
                        <hr>
                    </c:forEach>
                </div>
                <br>
            </div>
        </form>
    </body>
</html>