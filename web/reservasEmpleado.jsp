<%-- 
    Document   : reservasEmpleado
    Created on : 12-jun-2021, 16:36:38
    Author     : macar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar Reservas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
        <script src="font-awesome5.js"></script>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
         <script>
             function filtraReservas(){
            console.log("Filtrando Reservas");
            var select= document.getElementById("lista2");
            var nombre= select.options[select.selectedIndex].value;
            if(nombre!=null){
                $.ajax({
                    method:"POST",
                    url:"../filtraReservasEmp.jsp",
                    data:{nombre:nombre}
                })
                        .done(function(respuesta){
                            
                            $("#suplir").html(respuesta);
                });
                
            }
        }
         </script>
    </head>
    <body style="background-color: rgb(252, 235, 204);">
        <!-- Menú desplegable -->
    <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp"><img src="img/logoTonyBlanco.png"alt="logo"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-white"></span>
                </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <c:if test="${usuario!=null or cliente!=null}">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="reserva.jsp" tabindex="-1">Reservar</a>
                    </li>
                    </c:if>
                    
                </ul>
                <ul class="d-flex navbar-nav me-4">
                    <c:if test="${usuario==null and cliente==null}">
                        <li class="nav-item">
                            <a class="nav-link fw-bold" href="#" tabindex="-1" data-bs-toggle="modal" data-bs-target="#usuarios" aria-disabled="true">Área de Usuarios</a>
                        </li>
                    </c:if>
                    <c:if test="${usuario!=null or cliente!=null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link fw-bold dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <c:if test="${usuario!=null}">
                                    ${usuario.nombre} ${usuario.apellidos}
                                </c:if>
                                <c:if test="${usuario==null}">
                                    ${cliente.nombre} ${cliente.apellidos}
                                </c:if>
                            </a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navbarDarkDropdownMenuLink">
                                <li><a class="dropdown-item" href="miPerfil.jsp"><span class="bi bi-gear-fill">Mi perfil</span></a></li>
                                <c:if test="${usuario['class'].simpleName=='Empleado'}">
                                    <c:if test="${usuario.encargado}">
                                        <li><a class="dropdown-item" href="admin/administrar.jsp">Administración</a></li>
                                    </c:if>
                                </c:if>
                                
                                <li><a class="dropdown-item" href="CerrarSesion"><span class="bi bi-x-circle-fill"> Salir</a></span>
                            </ul>
                        </li>
                    </c:if>
                    
                </ul>
                
            </div>
        </div>
        </nav>
    </header>
    <section>
        <div style="height: 70px"></div>
        <div class="p-sm-5 pt-2" id="reserva" >
                      <h4 class="fw-bold">Reservas</h4>
                      <select id="lista2" onchange="filtraReservas()">
                          <option selected>Todos los restaurantes</option>
                            <c:forEach var="res" items="${gestion.restaurantes}">
                                
                                <option value="${res.nombre}">${res.nombre} (${res.provincia})</option>
                            </c:forEach>
                      </select>
                      <div style="height: 500px; overflow: scroll;">
                          <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Fecha</th>
                            <th scope="col">Hora</th>
                            <th scope="col">Cliente</th>
                            <th scope="col">Restaurante</th>
                            
                          </tr>
                        </thead>
                        <tbody id="suplir">
                            <c:forEach var="res" items="${gestion.verReservaSinRepes()}">
                                <tr>
                                    <td>${gestion.fechaVista(res.fecha)}</td>
                                    <td>${res.hora}</td>
                                    <td>${res.cliente.nombre} ${res.cliente.apellidos}</td>
                                    <td>${res.restaurante.nombre}</td>
                                    <td>
                                        <form onsubmit="return confirm('¿Quieres eliminar la reserva?');" action="EliminarReservaEmp" method="post">
                                            <input type="hidden" name="id" value="${res.id}">
                                            <input class="btn btn-danger" type="submit" value="Eliminar">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                      </table>
                      </div>
                      
                    </div>
                                           
    </section>
    </body>
</html>
