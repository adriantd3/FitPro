<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioDTO user = (UsuarioDTO) session.getAttribute("user");
%>
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
<header>
    <%
        String bienvenida = "";
        if (user.isSexo()){
            bienvenida = "Bienvenido, ";
        }else {
            bienvenida = "Bienvenida, ";
        }
    %>
    <a href="/salir">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center"><%= bienvenida + user.getNombre()%></h1>
</header>
    <section class="button-container">
        <button type="button" class="btn btn-secondary" name="clientes" onclick="window.location.href='/entrenador_cross_training/clientes'">Clientes</button><br/><br/>
        <button type="button" class="btn btn-secondary" name="rutinas" onclick="window.location.href='/entrenador_cross_training/rutinas'">Rutinas</button><br/><br/>
        <button type="button" class="btn btn-secondary" name="sesiones" onclick="window.location.href='/entrenador_cross_training/sesiones'">Sesiones</button><br/><br/>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>