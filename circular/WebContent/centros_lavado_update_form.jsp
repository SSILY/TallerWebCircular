<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%--
/* 

    Code Pioneers
    Innovar, crear, conquistar
    
    González Villa Sonia Maurilia    - 2193077059
    
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
<title>Catálogo de Centros de lavado</title>
<link rel="stylesheet"
    href="${ pageContext.request.contextPath }/css/style.css">
</head>
<body>
    <div class="step">Catálogo de Centros de lavado</div>
    <div class="instructions">Actualiza los Campos que se
        Requieran Modificar</div>
    <br>
    <%-- 
    <sql:query var="resultados" dataSource="jdbc/TestDS"
        sql="SELECT * FROM centros_lavado;" />
    <c:forEach var="fila" items="${ resultados.rows }">
        <form method="post"
            action="${ pageContext.request.contextPath }/CentrosUpdate">
            <table width="100%" class="data">
                <tr class="form">
                    <td align="center">
                        <div class="label">ID</div>
                    </td>
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
                    <td></td>
                </tr>
                <tr>
                    <td align="center">${ fila.centro_id }<input
                        type="hidden" name="id"
                        value="${ fila.centro_id }">
                    </td>
                    <td align="center">${ fila.nombre } <input
                        type="hidden" size="20" name="nombre"
                        value="${ fila.nombre }">
                    </td>
                    <td align="center">
                        <table border="0" cellspacing="0"
                            cellpadding="0">
                            <tr>
                                <td><input size="20"
                                    name="name_dir"
                                    value="${ fila.direccion }">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="center">
                         ${ fila.telefono } <input size="20"
                        name="tel" value="${ fila.telefono }">
                    </td>
                    <td align="center">
                        ${ fila.email } <input size="20"
                        name="tel" value="${ fila.email }">
                    </td>
                    <td valign="bottom"><input type="submit"
                        value="  Modificar  "> <input
                        type="submit" value="  Borrar  "></td>
                </tr>
            </table>
        </form>
    </c:forEach>
    --%>
    <c:forEach var="centro" items="${ centros }">
        <form method="post"
            action="${ pageContext.request.contextPath }/CentrosUpdate">
            <table width="100%" class="data">
                <tr class="form">
                    <td align="center">
                        <div class="label">ID</div>
                    </td>
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
                    <td></td>
                </tr>
                <tr>
                    <td align="center">${ centro.id }<input
                        type="hidden" name="id"
                        value="${ centro.id }">
                    </td>
                    <td align="center">${ centro.nombre } <input
                        type="hidden" size="20" name="nombre"
                        value="${ centro.nombre }">
                    </td>
                    <td align="center">
                        <table border="0" cellspacing="0"
                            cellpadding="0">
                            <tr>
                                <td><input size="20"
                                    name="dir"
                                    value="${ centro.dir }">
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td align="center">
                         <%-- ${ centro.tel }  --%><input size="20"
                        name="tel" value="${ centro.tel }">
                    </td>
                    <td align="center">
                        <%-- ${ centro.email } --%> <input size="20"
                        name="mail" value="${ centro.email }">
                    </td>
                    <%--Se agregó la propiedad name - P8 - 5/9/24 --%>
                    <td valign="bottom"><input type="submit"
                        value="Modificar" name="boton"> <input
                        type="submit" value="Borrar" name="boton"></td>
                </tr>
            </table>
        </form>
    </c:forEach>
    <form method="post"
        action="${ pageContext.request.contextPath }/CentrosUpdate">
        <div class="step">Alta de centro</div>
        <table width="100%">
            <tr class="form">
                <td align="center">
                        <div class="label">ID</div>
                    </td>
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
                    <td></td>
                    <td></td>
            </tr>
            <tr>
                <td align="center">AutoIncrement<input type="hidden" name="id" size="20" placeholder="Ingresa el ID"></td>
                <td align="center"><input name="nombre" size="20" placeholder="Ingresa el Nombre"></td>
                <td align="center"><input name="dir" size="20" placeholder="Ingresa la Direccion"></td>
                <td align="center"><input name="tel" size="20" placeholder="Ingresa el Telefono"></td>
                <td align="center"><input name="mail" size="20" placeholder="Ingresa el Email"></td>
                <td align="center">
                    <table border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><strong>:</strong></td>
                        </tr>
                    </table>
                </td>
                <%-- Se agregó la propiedad name - P8 - 5/9/24 --%>
                <td valign="bottom"><input type="submit"
                    value="Agregar" name="boton"></td>
            </tr>
        </table>
        <br> <input type="button" value="Regresar"
            onClick="window.location='${ pageContext.request.contextPath }/cliente_update_form.jsp'">
    </form>
    <br>
</body>
</html>