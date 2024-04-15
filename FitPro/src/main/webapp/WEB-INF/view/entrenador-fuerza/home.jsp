<%--
  Created by IntelliJ IDEA.
  User: victorperez
  Date: 11/4/24
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
    <header>
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
            onclick="window.location.href='/'"> <!-- Controlar pagina anterior por modelo -->
        <h1 class="header-text text-center">Bienvenido, <%="Usuario"%></h1> <!-- Meter usuario por modelo -->
    </header>
    <section class="position-relative top-25 start-50 w-25">
        <button type="button" class="btn btn-secondary translate-middle-x mb-2 mt-2 w-100"
                onclick="window.location.href='/entrenador-fuerza/crud-rutina'">CRUD Rutinas</button>
        <br>
        <button type="button" class="btn btn-secondary translate-middle-x w-100"
                onclick="window.location.href='/entrenador-fuerza/clientes'">Clientes</button>
    </section>
</body>
</html>
