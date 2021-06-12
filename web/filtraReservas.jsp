<%-- 
    Document   : filtraReservas.jsp
    Created on : 19-may-2021, 14:38:55
    Author     : macar
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<div class="modal-dialog modal-lg">
    <form action="Reservar" method="POST">
        <input type="hiddem" name="id" value="${gestion.buscarRestauranteNombre(param.nombre).id}">
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
                    <h2 class="fw-bold">Tony Roma's ${param.nombre}</h2>
                    <div class="card p-3">
                        <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Selecciona día</label>
                            <input type="date" min="${param.fecha}" name="fecha" class="form-control">
                        </div>
                        <div class="mb-3 row">
                            <div class="offset-md-4 col-md-4 my-2">
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
    </form>
        
        </div>
