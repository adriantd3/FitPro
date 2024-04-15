<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // ESPACIO PARA DECLARACIONES EN JAVA

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ejercicios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="">
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<nav class="navbar navbar-light" style="justify-content: center">
    <form style="justify-items: center;display: flex;gap: 10px;">
        <input class="form-control mr-sm-2" type="search" style="width: 400px" placeholder="Introduzca el ejercicio" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
</nav>
<section class="ejercicio-filtros">
    <div>
        Grupo muscular:
        <select class="selectpicker" data-style="btn-primary" name="musculo">
            <option value="espalda">Espalda</option>
            <option value="pecho">Pecho</option>
            <option value="hombro">Hombro</option>
            <option value="brazo">Brazo</option>
            <option value="pierna">Pierna</option>
        </select>
    </div>
    <div>
        Categoria:
        <select class="selectpicker" data-style="btn-primary" name="categoria">
            <option value="fuerza">Fuerza/Resistencia</option>
            <option value="aerobica">Capacidad aerobica</option>
            <option value="velocidad">Velocidad/Potencia</option>
            <option value="estabilidad">Estabilidad</option>
            <option value="movilidad">Movilidad</option>
        </select>
    </div>
</section>

<ul style="margin-left: 40px">
    <li><h6>Ejercicio 1</h6></li>
    <li><h6>Ejercicio 2</h6></li>
    <li><h6>Ejercicio 3</h6></li>
    <li><h6>Ejercicio 4</h6></li>
</ul>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>