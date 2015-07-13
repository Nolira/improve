<%--
  Created by IntelliJ IDEA.
  User: Nolira
  Date: 06.07.2015
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="ru.improve.testtask.entities.Product" %>
<html>
<head>
    <title>Поиск по продуктам</title>
</head>
<body>
<h1>Прайс-лист</h1>
<form action="ProductFilter" method="GET">
    <table>
        <tr>
            <td>
                Категория:
            </td>
            <td>
                Наименование:
            </td>
            <td>
                Цена от:
            </td>
            <td>
                Цена до:
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" name="catName" maxlength="255">
            </td>
            <td>
                <input type="text" name="prodName" maxlength="255">
            </td>
            <td>
                <input type="text" name="lowerPrice" size="17" maxlength="17">
            </td>
            <td>
                <input type="text" name="upperPrice" size="17" maxlength="17">
            </td>
            <td>
                <input type="submit" value="Найти"/>
            </td>
        </tr>
    </table>
    <br>
    <table width="50%">
    <%
        if (request.getAttribute("results")!=null){
            List<Product> results = (List) request.getAttribute("results");
            if (results.size()>0) {
        %>
            <tr bgcolor="#dadada">
                <td>
                    Категория
                </td>
                <td>
                    Название
                </td>
                <td>
                    Цена
                </td>
            </tr>
            <%
                int i = 0;
                for (Product result : results) {i++;
                    if(i%2==0){%><tr bgcolor="#f6f6f6">
                <%} else { %><tr><%}%>
                        <td width="25%">
                            <%=result.getCategory().getName()%>
                        </td>
                        <td width="60%">
                            <%=result.getName()%>
                        </td>
                        <td width="15%">
                            <%=result.getPrice()%>
                        </td>
                    </tr>
                <%
                }
            }
            else {%>
                <tr>
                    <td>Ничего не найдено</td>
                </tr>
            <%}
        }
        else { %>
                <tr>
                    <td>Введите хотя бы один параметр поиска</td>
                </tr>
        <%}
    %>
    </table>

</form>
</body>
</html>
