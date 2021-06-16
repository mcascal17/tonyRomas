<%-- 
    Document   : administrar
    Created on : 16-abr-2021, 12:07:39
    Author     : macar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title>JSP Page</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--<link rel="stylesheet" href="css/style.css">
        <link href="style.css" rel="stylesheet">-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <!--<link href="/node_modules/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">-->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    <script src="../font-awesome5.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        function cargar(){
            var select= document.getElementById("lista");
            var nombre= select.options[select.selectedIndex].value;
            if(nombre!=null){
            $.ajax({
            method:"POST",
            url:"../filtraRestaurante.jsp",
            data:{nombre:nombre}
            })
                    .done(function (respuesta){
                        
                $("#respuesta").html(respuesta);
            });
            
          
        }
    }
        
        function filtraReservas(){
            console.log("Filtrando Reservas");
            var select= document.getElementById("lista2");
            var nombre= select.options[select.selectedIndex].value;
            if(nombre!=null){
                $.ajax({
                    method:"POST",
                    url:"../filtraReservasAdmin.jsp",
                    data:{nombre:nombre}
                })
                        .done(function(respuesta){
                            
                            $("#suplir").html(respuesta);
                });
                
            }
        }
        
        function filtrarClientes(){
            console.log("hola");
            var filtro= document.getElementById('filtroCliente').value;
            $.ajax({
                method:"POST",
                url: "../filtraClientes.jsp",
                data: {filtro: filtro}
            })
                    .done(function(listado){
                        $("#filtraCliente").html(listado);
            });
        }
        
        function filtrarEmpleados(){
            console.log("hola");
            var filtro= document.getElementById('filtroEmpleado').value;
            $.ajax({
                method:"POST",
                url: "../filtraEmpleados.jsp",
                data: {filtro: filtro}
            })
                    .done(function(listado){
                        $("#filtraEmpleados").html(listado);
            });
        }
    
    </script>
    </head>
    <body style="background-color: rgb(251, 237, 211);">
        <c:if test="${!empty param.mensaje}">
        <script>
            alert("${param.mensaje}");
        </script>
        </c:if>
        <c:if test="${!empty param.error}">
        <script>
            alert("${param.error}");
        </script>
        </c:if>
        <c:if test="${!empty error}">
        <script>
            alert("${error}");
        </script>
        </c:if>
         <!-- Menú desplegable -->
    <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="../index.jsp"><img src="../img/logoTonyBlanco.png"alt="logo"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-white"></span>
                </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <c:if test="${usuario!=null or cliente!=null}">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="../reserva.jsp" tabindex="-1">Reservar</a>
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
                                    <c:if test="${!usuario.encargado}">
                                        <li><a class="dropdown-item" href="../reservasEmpleado.jsp">Reservas</a></li>
                                    </c:if>
                                </c:if>
                                <li><a class="dropdown-item" href="CerrarSesion1"><span class="bi bi-x-circle-fill"> Salir</a></span>
                            </ul>
                        </li>
                    </c:if>
                    
                </ul>
                
            </div>
        </div>
        </nav>
    </header>
                                        
    <section class="wrap my-5" id="admin">
        <div class="container-sm container-fluid my-5 bg-light p-sm-5" >
            <h1 class="fw-bold">Administración</h1>
            <p class="lead">Administra reservas y restaurantes</p>
            <div class="container">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item bg-primary" role="presentation">
                      <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">Clientes</button>
                    </li>
                    <li class="nav-item bg-warning" role="presentation">
                      <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">Restaurantes</button>
                    </li>
                    <li class="nav-item bg-warning" role="presentation">
                      <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">Añadir restaurante</button>
                    </li>
                    <li class="nav-item bg-danger" role="presentation">
                      <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#reserva" type="button" role="tab" aria-controls="reserva" aria-selected="false">Gestionar reservas</button>
                    </li>
                    <li class="nav-item bg-success" role="presentation">
                      <button class="nav-link" id="empleados-tab" data-bs-toggle="tab" data-bs-target="#empleados" type="button" role="tab" aria-controls="empleados" aria-selected="false">Empleados</button>
                    </li>
                    <li class="nav-item bg-success" role="presentation">
                      <button class="nav-link" id="añadirEmpleado-tab" data-bs-toggle="tab" data-bs-target="#añadirEmpleado" type="button" role="tab" aria-controls="añadirEmpleado" aria-selected="false">Añadir Empleado</button>
                    </li>
                  </ul>
                  <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active p-sm-5 pt-2" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="container p-3">
                            <label class="fw-bold mb-2" for="filtroCliente">Filtrar</label>
                            <input class="form-control" type="text" id="filtroCliente" onkeyup="filtrarClientes()">
                        </div>
                      <h4 class="fw-bold">Clientes</h4>
                      <div style="height: 500px; overflow: scroll;" >
                          <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Login</th>
                            <th scope="col">Email</th>
                            <th scope="col">Acción</th>
                          </tr>
                        </thead>
                        <tbody id="filtraCliente">
                            <c:forEach var="cli" items="${gestion.clientes}">
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
                        </tbody>
                      </table>
                      </div>
                      
                    </div>
                    <div class="tab-pane fade p-5" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                      <h4 class="fw-bold">Editar Restaurante</h4>
                      <div>
                          <form action="EditarRestaurante">
                            <select class="form-select" id="lista" onchange="cargar()">
                            <option selected>Selecciona restaurante a editar</option>
                            <c:forEach var="res" items="${gestion.restaurantes}">
                                
                                <option value="${res.nombre}">${res.nombre} (${res.provincia})</option>
                            </c:forEach>
                          </select>
                            <div class="row" id="respuesta">
                            <label for="nombre" class="col-xl-2 my-3">Nombre</label><input class="col-xl-10 my-3 " id="nombre" name="nombre" type="text" value="${gestion.buscarRestauranteNombre(param.nombre).direccion}">
                            <label for="direccion" class="col-xl-2 my-3">Dirección</label><input class="col-xl-10 my-3 " id="direccion" name="direccion" type="text" value="${gestion.buscarRestauranteNombre(param.nombre).direccion}" >
                            <label for="cif" class="col-xl-2 my-3">CIF</label><input class="col-xl-10 my-3 " id="cif" type="text" name="cif" value="${gestion.buscarRestauranteNombre(param.nombre).cif}" >
                            <label for="provincia" class="col-xl-2 my-3">Provincia</label><input class="col-xl-10 my-3 " id="provincia" type="text" name="provincia" value="${gestion.buscarRestauranteNombre(param.nombre).provincia}" >
                            <label for="propietario" class="col-xl-2 my-3">Propietario</label><input class="col-xl-10 my-3 " id="propietario" type="text" name="propietario" value="${gestion.buscarRestauranteNombre(param.nombre).propietario}" >
                            <label for="propietario" class="col-xl-2 my-3">Mesas</label><input class="col-xl-10 my-3 " id="mesas" type="number" name="mesas" value="">
                          <button class="btn btn-danger btn-lg">Actualizar restaurante</button>
                          </div>
                          

                        </form>
                      </div>
                    </div>
                    <div class="tab-pane fade p-5" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                      <h4 class="fw-bold">Añadir Restaurante</h4>
                      <div>
                          <form action="AltaRestaurante" method="post">
                          <div class="row">
                            <label for="nombre" class="col-xl-2 my-3">Nombre</label><input class="col-xl-10 my-3 " name="nombre" id="nombre" type="text">
                            <label for="direccion" class="col-xl-2 my-3">Provincia</label><input class="col-xl-10 my-3 " name="provincia" id="provincia" type="text">
                            <label for="direccion" class="col-xl-2 my-3">Dirección</label><input class="col-xl-10 my-3" name="direccion" id="direccion" type="text">
                            <label for="cif" class="col-xl-2 my-3">CIF</label><input class="col-xl-10 my-3 " id="cif" type="text" name="cif" >
                            <label for="propietario" class="col-xl-2 my-3">Propietario</label><input class="col-xl-10 my-3 " id="Propietario" type="text" name="propietario" >
                            <label for="mesas" class="col-xl-2 my-3">Mesas</label><input class="col-xl-10 my-3" id="mesas" type="number" min="10" name="mesas">
                            <input type="submit" class="btn btn-danger btn-lg" name="foto" value="Añadir Restaurante"/>
                          </div>
                          

                        </form>
                      </div>
                    </div>
                    <div class="tab-pane fade p-sm-5 pt-2" id="reserva" role="tabpanel" aria-labelledby="reserva-tab">
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
                            <th scope="col">Comensales</th>
                            
                          </tr>
                        </thead>
                        <tbody id="suplir">
                            <c:forEach var="res" items="${gestion.verReservaSinRepes()}">
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
                        </tbody>
                      </table>
                      </div>
                      
                    </div>
                            <div class="tab-pane fade p-sm-5 pt-2" id="empleados" role="tabpanel" aria-labelledby="empleados-tab">
                      <h4 class="fw-bold">Empleados</h4>
                      <div class="container p-3">
                            <label class="fw-bold mb-2" for="filtroEmpleados">Filtrar</label>
                            <input class="form-control" type="text" id="filtroEmpleado" onkeyup="filtrarEmpleados()">
                        </div>
                      <div style="height: 500px; overflow: scroll;">
                          <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">Nombre Completo</th>
                            <th scope="col">Email</th>
                            <th scope="col">Login</th>
                            <th scope="col">Encargado</th>
                            <th scope="col" colspan="2">Acción</th>
                            
                          </tr>
                        </thead>
                        <tbody id="filtraEmpleados">
                            <c:forEach var="emp" items="${gestion.empleados}">
                                <tr>
                                    <td>${emp.nombre} ${emp.apellidos}</td>
                                    <td>${emp.email}</td>
                                    <td>${emp.login}</td>
                                    <td>${emp.encargado?"Si":"No"}</td>
                                    
                                    <td>
                                        <form action="EditarEmpleado" method="post">
                                            <input type="hidden" name="id" value="${emp.id}">
                                            <input type="submit" class="btn btn-success" value="Editar">
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
                        </tbody>
                      </table>
                      </div>
                      
                    </div>
                    <div class="tab-pane fade p-5" id="añadirEmpleado" role="tabpanel" aria-labelledby="añadirEmpleado-tab">
                      <h4 class="fw-bold">Añadir Empleado</h4>
                      <div>
                          <form action="AltaEmpleado" method="post">
                          <div class="row">
                            <label for="nombre" class="col-xl-2 my-3">Nombre</label><input class="col-xl-10 my-3 " name="nombre" id="nombre" type="text">
                            <label for="apellidos" class="col-xl-2 my-3">Apellidos</label><input class="col-xl-10 my-3 " name="apellidos" id="apellidosEmpleado" type="text">
                            <label for="email" class="col-xl-2 my-3">Email</label><input class="col-xl-10 my-3" name="email" id="email" type="text">
                            <label for="login" class="col-xl-2 my-3">Login</label><input class="col-xl-10 my-3 " id="cif" type="text" name="login" >
                            <label for="password" class="col-xl-2 my-3">Contraseña</label><input class="col-xl-10 my-3" id="password" type="password" name="password" >
                            <label for="confirmacion" class="col-xl-2 my-3">Confirmar contraseña</label><input class="col-xl-10 my-3" id="confirmacion" type="password" name="confirmacion">
                            <div class="form-check p-3 offset-6 mb-2">
                                <input class="form-check-input" type="checkbox" name="administrador" value="true" ${checked} id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                    Administrador
                                </label>
                            </div>
                            <input type="submit" class="btn btn-danger btn-lg" name="foto" value="Añadir Empleado"/>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                  
            </div>

        </div>
    </section>
    <div style="height:80px;"></div>
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
