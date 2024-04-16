<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");

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
    <h1 class="header-text text-center">Rutinas</h1>
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
            <td><a href="">Borrar</a></td>
        </tr>
        <%
                num++;
            }
        %>

        </tbody>
    </table>
</section>
<br/>
<button class="btn btn-primary button-margin">Añadir rutina</button>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>