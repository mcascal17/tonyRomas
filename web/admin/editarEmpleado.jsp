<%-- 
    Document   : editarEmpleado
    Created on : 08-jun-2021, 8:32:31
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
        <title>Editar Empleado</title>
    </head>
    <body style="background-color: rgb(252, 235, 204);">
         <!-- Menú desplegable -->
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
                                <li><a class="dropdown-item" href="miPerfil.jsp"><span class="bi bi-gear-fill">Mi perfil</span></a></li>
                                <c:if test="${usuario['class'].simpleName=='Empleado'}">
                                    <c:if test="${usuario.encargado}">
                                        <li><a class="dropdown-item" href="administrar.jsp">Administración</a></li>
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
         <div style="height: 70px"></div>
    <section>
        <div class="container bg-light my-5">
            <div class="row p-5">
                <h2 class="mb-5 fw-bolder">Editar Perfil</h2>
                
                <div>
                    <h3 class="m-3 text-center">${login}</h3>
                        <form class="form-group row" action="EditarEmpleado" method="post">
                        <input type="hidden" name="id" value="${id}">
                        <label for="nombre" class="col-xl-2 mb-3">Nombre</label><input name="nombre" class="col-xl-10 mb-3 " id="nombre" type="text" value="${nombre}" >
                        <label for="apellidos" class="col-xl-2 mb-3">Apellidos</label><input name="apellidos" class="col-xl-10 mb-3 " id="apellidos" type="text" value="${apellidos}" >
                        <label class="col-xl-2 mb-3" for="email">Email </label><input class="col-xl-10 mb-3 " name="email" type="email" value="${email}" >
                        <label class="col-xl-2 mb-3" for="password">Contraseña </label><input class="col-xl-10 mb-3 " name="password" id="direccion" type="password" value="${password}">
                        <label class="col-xl-2 mb-3" for="confirmacion">Confirmar contraseña </label><input class="col-xl-10 mb-3 " name="confirmacion" id="direccion" type="password" value="">
                        <div class="form-check p-3 offset-6 mb-2">
                            <input class="form-check-input" type="checkbox" name="administrador" value="true" ${checked} id="flexCheckDefault">
                            <label class="form-check-label" for="flexCheckDefault">
                                Administrador
                            </label>
                        </div>
                        
                        <input type="submit" name="actualizar" class="btn btn-danger btn-lg" value="Actualizar Perfil">

                    </form>
                </div>
            </div>
        </div>
    </section>
    <div style="height: 50px;">

    </div>
    <!-- Footer -->
    <footer>
        <div class="container-fluid p-4" style="background-color: rgba(0, 0, 0, 0.228)">
            <!--Grid row-->
            <div class="row text-center">
              <!--Grid column-->
              <div class="col-lg-2 col-md-12 mb-4 mt-4">
                <img src="img/logoTony.png" class="w-100" alt="log2">
              </div>
              <!--Grid column-->
        
              <!--Grid column-->
              <div class=" offset-md-1  col-lg-2 col-md-4  my-4">
                <h5 class="text-uppercase">Síguenos</h5>
        
                <ul class="list-unstyled mb-0 row offset-2 offset-sm-0">
                  <li class="col-sm-3 col-2">
                    <a href="https://twitter.com/TonyRomasSpain" class="btn btn-outline-primary rounded-pill my-1"><span class="bi bi-twitter"></span></a>
                  </li>
                  <li class="col-sm-3 col-2">
                    <a href="https://www.instagram.com/tonyromassevilla/" class="btn btn-outline-warning rounded-pill my-1"><span class="fab fa-instagram"></span></a>
                  </li>
                  <li class="col-sm-3 col-2">
                    <a href="https://www.facebook.com/TonyRomasSpain/?brand_redir=187798294603872" class="btn btn-outline-primary rounded-pill my-1"><span class="bi bi-facebook"></span></a>
                  </li>
                  <li class="col-3-sm col-2">
                    <a href="https://www.youtube.com/channel/UC_6m3YX0X2mSNu4OjipATPQ" class="btn btn-outline-danger rounded-pill my-1"><span class="bi bi-youtube"></span></a>
                  </li>
                </ul>
              </div>
              <!--Grid column-->
        
              <!--Grid column-->
              <div class="col-lg-3 col-md-4 mb-4 mb-md-0">
                <h5 class="text-uppercase mb-0">Sobre nosotros</h5>
        
                <ul class="list-unstyled">
                  <li class="mb-2">
                    <a href="#!" class="text-dark">Conocenos</a>
                  </li>
                  <li class="mb-2">
                    <a href="#!" class="text-dark">Trabaja con Nosotros</a>
                  </li>
                  <li class="mb-2">
                    <a href="#!" class="text-dark">¿Que ofrecemos?</a>
                  </li>
                  <li class="mb-2">
                    <a href="#!" class="text-dark">Nuestro compromiso</a>
                  </li>
                </ul>
              </div>
              <!--Grid column-->
              <div class="col-lg-3  mb-4 mb-md-0">
                <h5 class="text-uppercase mb-1">Déjanos tu opinión</h5>
        
                <form action="">
                  <textarea class="form-control "></textarea>
                  <input type="submit" class="btn btn-success my-2" value="Enviar opinión">
                </form>
              </div>
            </div>
            <!--Grid row-->
          </div>
          <!-- Grid container -->
        
          <!-- Copyright -->
          <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.379)">
            © 2021 Copyright:
            <a class="text-danger text-decoration-none" href=#>Macario Castaño <span class="bi bi-controller text-dark"></span></a>
          </div>
    </footer>
    </body>
</html>
