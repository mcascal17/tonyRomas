<%-- 
    Document   : confirmarReserva
    Created on : 26-may-2021, 16:59:03
    Author     : macar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
        <script src="font-awesome5.js"></script>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <script>
            window.onload= function(){
                console.log("Hola");
                var botones= document.getElementsByTagName("button");
                for (var i=0; i < botones.length ; i++){
                    botones[i].addEventListener('click',seleccionar, false);
                }
                console.log(botones);
                
            }
            function seleccionar(){
                var botones= document.getElementsByTagName("button");
                for (var i=0; i < botones.length ; i++){
                    botones[i].style.backgroundColor= "#ffc107";
                    botones[i].style.color= "black";

                }
                console.log("hola");
                var boton= window.event.target;
                boton.style.backgroundColor="blue";
                boton.style.color="white";
                var hora=window.event.target.innerHTML;
                document.getElementById("hora").value=hora;
                console.log(hora);
            }
        </script>
    </head>
    <body style="background-color: rgb(252, 235, 204);">
        <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp"><img src="img/logoTonyBlanco.png"alt="logo"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-white"></span>
                </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    
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
                                    <c:if test="${!usuario.encargado}">
                                        <li><a class="dropdown-item" href="reservasEmpleado.jsp">Reservas</a></li>
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
        <div style="height:70px"></div>
        <section class="container-fluid mt-5 text-center ">
            <div class="container bg-light p-4">
                <h3>Está realizando una reserva para ${comensales} personas</h3>
            <h4>Para el dia ${fecha} en el restaurante ${restaurante.nombre}</h4>
            <h3>Horas disponibles</h3>
            <div class="container row">
                <c:forEach var="h" items="${horas}">
                    <div class="col-sm-3 mt-2">
                        <button id="boton" class="btn btn-warning">${h}</button>
                    </div>
                
            </c:forEach>
            </div>
            <div class="row text-center m-5">
                <form action="Reservar" method="POST">
            <input type="hidden" name="id" value="${restaurante.id}">
            <input type="hidden" name="fecha" value="${fecha}">
            <input type="hidden" name="mesas" value="${mesas}">
            <input type="hidden" name="comensales" value="${comensales}">
            <input type="hidden" name="hora" id="hora" value="">
                <input type="hidden" name="login" value="${cliente.login}">
                <input type="submit" class="btn btn-danger btn-lg" name="reservar" value="Reservar">
            </form>
            </div>
            
            </div>
            
        </section>
        
        
    </body>
</html>
