<%-- 
    Document   : test
    Created on : Feb 13, 2022, 8:58:05 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="testController" method="POST">
            <input type="submit" value="Test"/>
        </form>
        <c:if test="${sessionScope.id != null}">
            <h2>${sessionScope.id}</h2>
        </c:if>
    </body>
</html>
