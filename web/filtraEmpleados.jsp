<%-- 
    Document   : filtraEmpleados
    Created on : 09-jun-2021, 12:44:36
    Author     : macar
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="emp" items="${gestion.filtrarEmpleados(param.filtro)}">
    <tr>
        <td>${emp.nombre} ${emp.apellidos}</td>
        <td>${emp.email}</td>
        <td>${emp.login}</td>
        <td>${emp.encargado?"Si":"No"}</td>
        <td>
            <form action="EditarEmpleado" method="post">
                <input type="hidden" name="id" value="${emp.id}">
                <input class="btn btn-danger" type="submit" value="Editar">
            </form>
        </td>
        <td>
            <form onsubmit="return confirm('¿Quieres eliminar al empleado ${emp.nombre} ${emp.apellidos}?');" action="EliminarEmpleado" method="post">
                <input type="hidden" name="id" value="${emp.id}">
                <input class="btn btn-danger"  type="submit" value="Eliminar">
            </form>
        </td>
    </tr>
        
    
</c:forEach>

