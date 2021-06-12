<%-- 
    Document   : reserva
    Created on : 14-may-2021, 12:44:33
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
        <title>Reservar</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function cargar(){
                console.log("HOLA");
                var nombre= window.event.target.parentNode.childNodes[3].innerHTML;
                var fecha = new Date();
                var dd = fecha.getDate();
                var mm = fecha.getMonth()+1;
                var yyyy = fecha.getFullYear();
                if(dd<10){
                    dd='0'+dd
                } 
                if(mm<10){
                    mm='0'+mm
                } 
                console.log(fecha);
                fecha = yyyy+'-'+mm+'-'+dd;
                console.log(nombre);
                if(nombre!=null){
                    $.ajax({
                        method:"POST",
                        url:"filtraReservas.jsp",
                        data:{nombre:nombre, fecha:fecha}
                        
                    })
                            .done(function (respuesta){
                                $("#reservas").html(respuesta);
                    })
                }
            }
            
            
        </script>
        <script src="reserva.js" type="text/javascript"></script>
    </head>
    
    <body style="background-color: rgb(252, 235, 204);">
    <!-- Menú desplegable -->
    <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="../index.jsp"><img src="img/logoTonyBlanco.png"alt="logo"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-white"></span>
                </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <c:if test="${usuario!=null or cliente!=null}">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="../reserva.jsp" tabindex="-1"></a>
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
                                <li><a class="dropdown-item" href="../miPerfil.jsp"><span class="bi bi-gear-fill">Mi perfil</span></a></li>
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
    <!-- Modal reserva-->
    
    <div class="modal fade " id="reservas" >
        <div class="modal-dialog modal-lg">
            <div class="modal-content" style="background-color: wheat;">
            <div class="modal-header justify-content-center bg-danger">
                <h3 class="text-white fw-bolder">Reservar</h3>
            </div>
            <div class="modal-body row g-1" id="reservas">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    Debido a las resticciones por el <strong>COVID-19</strong> no se podrán reservar mesa de más de 4 comensales
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
                <div class="container p-3 bg-light text-center">
                    <h2 class="fw-bold">Tony Roma's ${nombre}</h2>
                    <div class="card p-3">
                        <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Selecciona día</label>
                            <input type="date" id="fecha" class="form-control">
                        </div>
                        <div class="mb-3 row">
                            <div class="offset-md-2 col-md-4 my-2">
                                <h5>Nº Comensales</h5>
                                <input type="number" name="comensales" class="form-control form-control-lg">
                            </div>
                        </div>
                        <div class="mb-3">
                            <button class="btn btn-success">Reservar</button>
                        </div>
                    </div>
                </div>
            </div>
          </div>
        </div>
      </div>
    <!--Accordion-->
    <div style="height: 100px"></div>
    <section class="wrap container">
        <div class="container-fluid row text-center" style="background-color: rgba(215, 92, 90, 0.468);">
            <c:forEach var="res" items="${gestion.restaurantes}">
                <div class="card m-5 p-3 text-center" style="width: 18rem;">
                    <img src="img/kounde.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title fw-bolder">${res.provincia}</h5>
                        <p id="nombre" class="card-text">${res.nombre}</p>
                        <a href="#" id="enlace" onclick="cargar()" data-bs-toggle="modal" data-bs-target="#reservas" aria-disabled="true"class="btn btn-danger">Reservar</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
                        
    <div style="height: 50px;"></div>
    <!-- Footer -->
    <footer>
       
          <!-- Grid container -->
        
          <!-- Copyright -->
          <div class="text-center p-3 fixed-bottom" style="background-color: rgb(198, 198, 198)">
            © 2021 Copyright:
            <a class="text-danger text-decoration-none" href=#>Macario Castaño <span class="bi bi-controller text-dark"></span></a>
          </div>
    </footer>
</body>
</html>
