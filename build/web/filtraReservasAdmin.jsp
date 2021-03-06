<%-- 
    Document   : filtraReservasAdmin
    Created on : 03-jun-2021, 17:54:38
    Author     : macar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<c:forEach var="res" items="${gestion.filtraReservas(param.nombre)}">
    <tr>
        <td>${gestion.fechaVista(res.fecha)}</td>
        <td>${res.hora}</td>
        <td>${res.cliente.nombre} ${res.cliente.apellidos}</td>
        <td>${res.restaurante.nombre}</td>
        <td>${res.comensales}</td>
        <td>
            <form onsubmit="return confirm('¿Quieres eliminar la reserva?');" action="EliminarReserva" method="post">
                <input type="hidden" name="id" value="${res.id}">
                <input class="btn btn-danger" type="submit" value="Eliminar">
            </form>
        </td>
    </tr>
</c:forEach>
