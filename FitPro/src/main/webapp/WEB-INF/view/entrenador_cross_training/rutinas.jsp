<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
    Usuario cliente = (Usuario) request.getAttribute("cliente");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rutinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="">
    <h1 class="header-text text-center"><%= cliente!= null ? "Rutinas de " + cliente.getNombre() : "Rutinas"%></h1>
</header>
<section class="table-container">
    <table class="table table-striped table-dark">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Rutina</th>
            <th scope="col">Editar</th>
            <th scope="col">Borrar</th>
        </tr>
        </thead>
        <tbody>
        <%
            int num = 1;
            for (Rutina r : rutinas){
        %>
        <tr>
            <th scope="row"><%= num %></th>
            <td><%= r.getNombre() + " , FECHA: " + r.getFechaCreacion()%></td>
            <td><a href="">Editar</a></td>
            <td><a href="/entrenador_cross_training/borrar_rutina?id=<%= r.getId()%>">Borrar</a></td>
        </tr>
        <%
                num++;
            }
        %>

        </tbody>
    </table>
</section>
<br/>

<button type="button" class="btn btn-primary button-margin" data-toggle="modal" data-target="#nuevaRutina">
    Añadir rutina
</button>

<div class="modal fade" id="nuevaRutina" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nueva rutina</h5>
            </div>
            <div class="modal-body">
                <form method="post" action="/entrenador_cross_training/nueva_rutina">
                    Nombre de la rutina: <input type="text" name="nombre">
                    <div style="display: flex; justify-content: center; margin-top: 20px">
                        <button type="submit" class="btn btn-primary">Guardar rutina</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>