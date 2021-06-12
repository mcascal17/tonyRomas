<%-- 
    Document   : registroUsuario
    Created on : 11-abr-2021, 22:22:29
    Author     : macar
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src="font-awesome5.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    </head>
    <body style="background-color: rgb(252, 235, 204);">
        
         <!-- Menú desplegable -->
    <header>
        <nav class="navbar navbar-expand-md navbar-light fixed-top bg-danger">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp"><img src="img/logoTonyBlanco.png" alt="logo"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon text-white"></span>
                </button>
            
        </div>
        </nav>
    </header>
         
         <div style="height: 75px;"></div>
         <c:if test="${!empty error}">    
            <div class="fw-bold alert alert-danger alert-dismissible fade show p-2" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
    <!-- formulario -->
    <section class="wrap">
        <div class="container p-3 rounded mt-4 mb-5" style="background-color: rgba(0, 0, 0, 0.126)">
            <form action="AltaUsuario">
                <div class="mb-3 form-floating">
                    <input type="text" name="nombre" class="form-control" id="floatingInput" placeholder="">
                    <label for="floatingInput">Nombre</label>
                    
                </div>
                <div class="mb-3 form-floating">
                    <input type="text" id="apellidos" name="apellidos" class="form-control" placeholder=""> 
                    <label for="apellidos">Apellidos</label>
                </div>
                <div class="mb-3 form-floating">
                    <input type="email" id="email" name="email" class="form-control" placeholder="">
                    <label for="email">Email</label>
                    
                </div>
                <div class="mb-3 form-floating">
                    <input type="text" id="login" class="form-control" name="login" placeholder=""> 
                  <label for="login">Usuario</label>
              </div>
                <div class="mb-3 form-floating">
                    <input type="password" id="contraseña" name="password" class="form-control" placeholder="">
                    <label for="contraseña">Constraseña</label>
                </div>
                <div class="mb-3 form-floating">
                    <input type="password" id="confirmacion" class="form-control" name="confirmacion" placeholder="">
                    <label for="confirmacion">Confirmar contraseña</label>
                </div>
                
                <div class="form-check form-switch mb-3">
                    <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                    <label class="form-check-label" for="flexSwitchCheckDefault">Acepto terminos y condiciones</label>
                </div>
                <input type="submit" class="btn btn-lg btn-outline-success offset-md-5 offset-4" name="registrar" value="Resgistrarse">
            </form>
        </div>
    </section>
    </body>
</html>
