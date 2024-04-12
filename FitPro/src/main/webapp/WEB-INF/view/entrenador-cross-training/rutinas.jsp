<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <img class="back-button ms-1 mt-1 " src="assets/image.png" alt="">
    <h1 class="header-text text-center">Rutinas</h1>
</header>
<table border="1">
    <thead>
    <tr>
        <th>Rutina</th>
        <th>Editar rutina</th>
        <th>Borrar rutina</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Rutina 1</td>
        <td><a href="">Editar</a></td>
        <td><a href="">Borrar</a></td>
    </tr>
    <tr>
        <td>Rutina 2</td>
        <td><a href="">Editar</a></td>
        <td><a href="">Borrar</a></td>
    </tr>
    <tr>
        <td>Rutina 3</td>
        <td><a href="">Editar</a></td>
        <td><a href="">Borrar</a></td>
    </tr>
    </tbody>

</table><br/>
<button class="btn btn-primary">Añadir rutina</button>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>