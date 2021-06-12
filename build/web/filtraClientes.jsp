<%-- 
    Document   : filtraClientes
    Created on : 09-jun-2021, 11:43:49
    Author     : macar
--%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="cli" items="${gestion.filtrarClientes(param.filtro)}">
    <tr>
        <td>${cli.nombre}</td>
        <td>${cli.apellidos}</td>
        <td>${cli.login}</td>
        <td>${cli.email}</td>
        <td>
            <form action="EditarClienteAdmin" method="post">
                <input type="hidden" name="id" value="${cli.id}">
                <input class="btn btn-danger" type="submit" value="Editar">
            </form>
        </td>
    </tr>
        
    
</c:forEach>
