<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<meta name="Author" content="Code Pioneers">
<title>Detalle de Cliente</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <form method="get"
        action="${ pageContext.request.contextPath }/cliente_view.jsp">
        <div class="step">Detalle del Cliente</div>
        <br> <br>
        <table border="0" width="100%">
            <tr class="form">
                <td align="center">
                    <div class="label">Nombre</div>
                </td>
                <td align="center">
                    <div class="label">Direccion</div>
                </td>
                <td align="center">
                    <div class="label">Telefono</div>
                </td>
                <td align="center">
                    <div class="label">Email</div>
                </td>
            </tr>
            <c:forEach var="cliente" items="${ clientes }">
                <tr>
                    <td align="center">${ cliente.nombre }</td>
                    <td align="center">${ cliente.dir }</td>
                    <td align="center">${ cliente.tel }</td>
                    <td align="center">${ cliente.mail }</td>
                    
                </tr>
            </c:forEach>
        </table>
        <br> <input type="button" value="Regresar"
            onclick="window.location='${ pageContext.request.contextPath }/cliente_search2.jsp'">
    </form>
    <br>
</body>
</html>