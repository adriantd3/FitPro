<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sesion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="">
    <h1 class="header-text text-center">Sesion (nombre de la sesion)</h1>
</header>
<section class="sesion-table-container">
    <table class="table table-striped table-dark">
        <thead>
        <tr><th style="background-color: transparent; color: darkblue;">Remo en polea</th></tr>
        <tr>
            <th scope="col">Serie</th>
            <th scope="col">Peso</th>
            <th scope="col">Repeticiones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>50kg</td>
            <td>12</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>55kg</td>
            <td>8</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>60kg</td>
            <td>6</td>
        </tr>
        </tbody>
    </table>
    <table class="table table-striped table-dark">
        <thead>
        <tr><th style="background-color: transparent; color: darkblue;">Remo en polea</th></tr>
        <tr>
            <th scope="col">Serie</th>
            <th scope="col">Peso</th>
            <th scope="col">Repeticiones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>50kg</td>
            <td>12</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>55kg</td>
            <td>8</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>60kg</td>
            <td>6</td>
        </tr>
        </tbody>
    </table>
    <table class="table table-striped table-dark">
        <thead>
        <tr><th style="background-color: transparent; color: darkblue;">Remo en polea</th></tr>
        <tr>
            <th scope="col">Serie</th>
            <th scope="col">Peso</th>
            <th scope="col">Repeticiones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>50kg</td>
            <td>12</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>55kg</td>
            <td>8</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>60kg</td>
            <td>6</td>
        </tr>
        </tbody>
    </table>
    <table class="table table-striped table-dark">
        <thead>
        <tr><th style="background-color: transparent; color: darkblue;">Remo en polea</th></tr>
        <tr>
            <th scope="col">Serie</th>
            <th scope="col">Peso</th>
            <th scope="col">Repeticiones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>50kg</td>
            <td>12</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>55kg</td>
            <td>8</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>60kg</td>
            <td>6</td>
        </tr>
        </tbody>
    </table>
</section>
<section class="sesion-buttons">
    <button type="button" class="btn btn-success" onclick="window.location.href='/entrenador-cross-training/clientes'">Guardar</button>
    <button type="button" class="btn btn-danger" onclick="window.location.href='/entrenador-cross-training/rutinas'">Borrar</button>
    <button type="button" class="btn btn-primary" onclick="window.location.href='/entrenador-cross-training/sesiones'">Añadir ejercicio</button>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>