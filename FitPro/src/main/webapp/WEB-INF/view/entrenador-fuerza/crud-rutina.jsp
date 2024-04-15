<%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:07
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
         onclick="window.location.href='/entrenador-fuerza/home'">
    <h1 class="header-text text-center">Rutinas de entrenamiento</h1> <!-- Controlar si es de un usario para añadir "de usuario" y solo sus listas -->
</header>
<section class="mt-3 ms-3 h-100">
    <button class=" btn btn-primary top-50"
            onclick="window.location.href='/entrenador-fuerza/rutina'">Añadir Rutina</button>
</section>
</body>
</html>
