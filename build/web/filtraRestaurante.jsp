<%-- 
    Document   : filtraRestaurante
    Created on : 01-may-2021, 14:15:14
    Author     : macar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" name="id" value="${gestion.buscarRestauranteNombre(param.nombre).id}">
    <label for="nombre" class="col-xl-2 my-3">Nombre</label><input class="col-xl-10 my-3 " id="nombre" name="nombre" type="text" value="${gestion.buscarRestauranteNombre(param.nombre).nombre}">
    <label for="direccion" class="col-xl-2 my-3">Dirección</label><input class="col-xl-10 my-3 " id="direccion" name="direccion" type="text" value="${gestion.buscarRestauranteNombre(param.nombre).direccion}" >
    <label for="cif" class="col-xl-2 my-3">CIF</label><input class="col-xl-10 my-3 " id="cif" type="text" name="cif" value="${gestion.buscarRestauranteNombre(param.nombre).cif}" >
    <label for="provincia" class="col-xl-2 my-3">Provincia</label><input class="col-xl-10 my-3 " id="provincia" type="text" name="provincia" value="${gestion.buscarRestauranteNombre(param.nombre).provincia}" >
    <label for="propietario" class="col-xl-2 my-3">Propietario</label><input class="col-xl-10 my-3 " id="propietario" type="text" name="propietario" value="${gestion.buscarRestauranteNombre(param.nombre).propietario}" >
    <label for="propietario" class="col-xl-2 my-3">Mesas</label><input class="col-xl-10 my-3 " id="mesas" type="number" name="mesas" value="${gestion.buscarRestauranteNombre(param.nombre).mesas}">
            <button class="btn btn-danger btn-lg">Actualizar restaurante</button>
