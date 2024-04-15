<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
    <h1 class="ejemplo">Bienvenido, (nombre del entrenador)</h1>
    <section class="button-container">
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/clientes'">Clientes</button><br/><br/>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/rutinas'">Rutinas</button><br/><br/>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/sesiones'">Sesiones</button><br/><br/>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/ejercicios'">Ejercicios (solo mientras desarrollo)</button><br/><br/>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/sesion'">Sesion (solo mientras desarrollo)</button>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>