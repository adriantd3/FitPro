<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Sesion sesion = (Sesion) request.getAttribute("sesion");
    Ejercicio ejercicio = (Ejercicio) request.getAttribute("ejercicio");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Añadir serie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<h1 class="ejemplo">Nueva serie</h1>
<form method="post" action="/entrenador_cross_training/guardar_serie">
    <div  style="width: 300px; margin-left: 40px" class="input-group mb-3">
        <span class="input-group-text btn btn-success">Peso</span>
        <input type="number" name="peso" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <div  style="width: 300px; margin-left: 40px" class="input-group mb-3">
        <span class="input-group-text btn btn-success">Repeticiones</span>
        <input type="number" name="repeticiones" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
    </div>
    <input type="hidden" name="sesion" value="<%= sesion.getId() %>">
    <input type="hidden" name="ejercicio" value="<%= ejercicio.getId() %>">
    <button style="margin-top: 30px" type="submit" class="btn btn-primary button-margin">Añadir serie</button>
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>