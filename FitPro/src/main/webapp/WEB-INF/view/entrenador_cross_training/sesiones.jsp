<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<SesionDTO> sesiones = (List<SesionDTO>) request.getAttribute("sesiones");
    String sesionFiltrada = "";
    if (request.getParameter("nombre")!=null) sesionFiltrada = request.getParameter("nombre");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sesiones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Sesiones</h1>
</header>
<section class="scrollable-section">
    <section class="table-container">
        <table class="table table-striped table-dark table-width">
            <thead>
            <form method="get" action="/entrenador_cross_training/filtrar_sesiones">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">
                        <div class="filter-flex">
                            <input type="text" placeholder="Sesion" name="nombre" value="<%= sesionFiltrada %>" class="form-control filter-input" data-bs-theme="dark" >
                            <button name="filtrar" class="btn btn-dark">🔍</button>
                        </div>
                    </th>
                    <th scope="col">Editar</th>
                    <th scope="col">Borrar</th>
                </tr>
            </form>
            </thead>
            <tbody>
            <%
                int num = 1;
                for (SesionDTO s : sesiones){
            %>
            <tr>
                <th scope="row"><%= num %></th>
                <td name="nombre_sesion"><%= s.getNombre() %></td>
                <td>
                    <button name="editar" class="btn btn-warning button-image" onclick="window.location.href='/entrenador_cross_training/sesion?id=<%= s.getId()%>'">
                        <img class="image-button" src="${pageContext.request.contextPath}/assets/editar_button.svg">
                    </button>
                </td>
                <td>
                    <form action="/entrenador_cross_training/borrar_sesion" method="post">
                        <input type="hidden" name="id" value="<%=s.getId()%>">
                        <button type="submit" class="btn btn-danger button-image">
                            <img class="image-button" src="${pageContext.request.contextPath}/assets/delete_button.svg">
                        </button>
                    </form>
                </td>
            </tr>
            <%
                    num++;
                }
            %>
            </tbody>
        </table>
    </section>
    <% if (sesiones.isEmpty()) { %>
    <section class="seccion-alerta">
        <section class="mensaje-alerta"><h2>No hay sesiones disponibles</h2></section>
    </section>
    <% } %>
</section>

<div class="sesion-buttons">
    <button class="btn btn-success" onclick="window.location.href='/entrenador_cross_training/'">Guardar</button>
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#nuevaSesion">
        Añadir sesion
    </button>
</div>


<div class="modal fade" id="nuevaSesion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div style="background-color: #696767;color: white" class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nueva sesion</h5>
            </div>
            <div class="modal-body">
                <form method="post" action="/entrenador_cross_training/nueva_sesion">
                    Nombre de la sesion: <input type="text" name="nombre">
                    <div style="display: flex; justify-content: flex-end; margin-top: 20px">
                        <button type="submit" class="btn btn-success">Guardar sesion</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>