<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%--
/* 

    Code Pioneers
    Innovar, crear, conquistar
    Contreras Hernández Miguel Ángel - 2193005151
    González Villa Sonia Maurilia    - 2193077059
    Mora Sánchez Edgar Israel        - 2193042305
    Rios Valencia Gisela Rocio       - 2173003053
    
    Taller de Desarrollo de Aplicaciones Web
    Hugo Pablo Leyva
    17/08/2024

*/
--%>
<html>
<head>
<meta http-equiv="content-type"
    content="text/html;
      charset=windows-1252">

<meta name="Author" content="Code Pioneers">
<title>Forma de Búsqueda de Cliente</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <form method="get"
        action="${ pageContext.request.contextPath }/cliente_search.jsp">
        <div class="step">Forma de Búsqueda de Cliente</div>
        <div class="instructions">Proporciona la información de
            búsqueda solicitada</div>
        <br>
        <table border="1" cellpadding="10" width="100%">
            <tr>
                <td align="center">
                    <table>
                        <tr class="form">
                            <td align="right">
                                <div class="label">Patrón:</div>
                            </td>
                            <td><input name="pattern" size="10"
                                value="a*"></td>
                            <td><input type="submit"
                                value="  Buscar  "></td>
                        </tr>
                    </table>
                    <br>
       <table border="0" width="100%">
<tr class="form">
                <td align="center">
                    <div class="label">ID</div>
                </td>
                <td align="center">
                    <div class="label">Nombre completo</div>
                </td>
                <td align="center">
                    <div class="label">Direccion</div>
                </td>
                <td align="center">
                    <div class="label">Detalle</div>
                </td>
                <td align="center">
                    <div class="label">PDF</div>
                </td>
                <td align="center">
                    <div class="label">XLS</div>
                </td>
            </tr>
            <sql:query var="resultados" dataSource="jdbc/TestDS"
                sql="SELECT * FROM clientes;" />
                <c:forEach var="fila" items="${ resultados.rows }">
               <tr>
                    <td align="center">${ fila.cliente_id }</td>
                    <td align="center">${ fila.nombre }</td>
                    <td align="center">${ fila.direccion }</td>
                    <td align="center"><input type="button"
                        value="  Ver  "
                        onclick="window.location='cliente_view.jsp'">
                    </td>
                    <td align="center"><input type="button"
                        value="  Ver como PDF  "></td>
                    <td align="center"><input type="button"
                        value="  Ver como XLS  "></td>
                </tr>
            </c:forEach>
        </table>
        <br> <input type="button" value="  Regresar  "
            onclick="window.location='${ pageContext.request.contextPath }/main.jsp'">
    </form>
    <br>
</body>
</html>