<%-- 
    Document   : index
    Created on : 15-mar-2021, 15:51:58
    Author     : macar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tony Roma's</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src="font-awesome5.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    </head>
    <body style="background-color: rgb(252, 235, 204);" >
        <c:if test="${!empty param.mensaje}">
        <script>
            alert("${param.mensaje}");
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
         
         <!-- Usuarios-->
    <div class="modal fade"  id="usuarios">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class=" mx-1 modal-header justify-content-center row">
            <a href="registroUsuario.jsp" class=" my-1 btn btn-danger btn-block w-100 fw-bold" role="button"><i class="fal fa-pen"></i> Registrate</a>
          </div>
          <div class="modal-body">
              <form action="Login" method="post">
              <div class="form-group my-1">
                <label for="login">Usuario</label>
                <input type="text" name="login" class="form-control" id="login" placeholder="Escribe tu email...">
              </div>
              
              <div class="form-group my-1">
                <label for="password">Contraseña</label>
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Escribe tu contraseña...">
              </div>
              <hr class="featurette-divider mx-5">
              <input type="submit" class="btn btn-success btn-block w-100 fw-bold" value="Inicia Sesión"/>
            </form>
          </div>
          <div class="modal-footer">
            <p><a href="">¿Has olvidado la contraseña?</a></p>
            
          </div>
        </div>
      </div>
    </div>
         <!-- Carrusel -->
        <section>
            <div id="carouselExampleCaptions" class="carousel slide carousel-dark my-5" data-bs-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"></li>
                    <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"></li>
                    <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                    <img src="img/banner.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block bg-danger">
                        <h5 class="text-white fw-bolder">Promoción Semanal</h5>
                        <p class="text-white fst-italic" >Menú 10.95? persona, incluye bebida recargable</p>
                    </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/banner2.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block bg-danger">
                            <h5 class="text-white fw-bolder">Cenas Martes y Jueves </h5>
                            <p class="text-white fst-italic">Ven acompañado de una pareja y te regalamos el segundo costillar</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/banner3.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block bg-danger">
                            <h5 class="text-white fw-bolder">Menú infantil</h5>
                            <p class="text-white fst-italic">Patatas, hamburguesa , bebida y postre </p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon " aria-hidden="true"></span>
                    <span class="visually-hidden ">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </a>
            </div>
        </section>
         <!-- Prueba -->
        <section>
            <div class="container marketing">

                <!-- Three columns of text below the carousel -->
                <div class="row text-center">
                  <div class="col-lg-4">
                    <img class="rounded-circle" src="img/entrantes.jpg" alt="postre">
            
                    <h2>Entrantes</h2>
                    <p>Llegas a un Tony Roma's , ves los entrantes... <strong>¡Y ves que la cosa va enserio!</strong></p>
                    <p><a class="btn btn-danger" href="#" role="button" data-bs-toggle="modal" data-bs-target="#entrantes" aria-disabled="true">Galeria &raquo;</a></p>
                  </div><!-- /.col-lg-4 -->
                  <div class="col-lg-4">
                    <img class="rounded-circle" src="img/burger.jpg" alt="postre">
            
                    <h2>Principales</h2>
                    <p>Huelo... <strong>¡A CARNE! </strong>Ven a Tony Roma's y disfruta de una gran variedad de <strong>Costillares y Hamburguesas</strong></p>
                    <p><a class="btn btn-danger" href="#" role="button" data-bs-toggle="modal" data-bs-target="#principales" aria-disabled="true">Galeria &raquo;</a></p>
                  </div><!-- /.col-lg-4 -->
                  <div class="col-lg-4">
                    <img class="rounded-circle" src="img/postre.jpg" alt="postre">
            
                    <h2>Postres</h2>
                    <p>Disfruta de nuestros deliciosos postres y da un toque dulce a tú comida.</p>
                    <p><a class="btn btn-danger" href="#" role="button"  data-bs-toggle="modal" data-bs-target="#postres" aria-disabled="true">Galeria &raquo;</a></p>
                  </div><!-- /.col-lg-4 -->
                </div><!-- /.row -->
            
            
                <!-- Introduccion-->
            
                <hr class="featurette-divider">
            
                <div class="row featurette my-5">
                  <div class="col-md-7">
                    <h2 class="featurette-heading fw-bold">¿A qué esperas para visitarnos?</h2>
                    <p class="lead">Ven y visítanos en nuestro nuevo restaurante en el <strong>CC Torre Sevilla</strong> inaugurado en el año 2018</p>
                    <h3>Horario: </h3>
                    <p class="lead">Lunes - Viernes: <strong>12:00 - 01:00</strong></p>
                    <p class="lead">Fines de Semana: <strong>11:00 - 02:30</strong></p>
                  </div>
                  <div class="col-md-5">
                    <img src="img/tony.jpg" alt="local" class="w-100">
            
                  </div>
                </div>
        </section>
         
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
