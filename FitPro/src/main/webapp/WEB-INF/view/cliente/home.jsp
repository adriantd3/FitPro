<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("user");
%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - Índice</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Bienvenido, <%=cliente.getNombre()%></h1>
</header>
<section id="buttons" class="position-relative start-50 w-25 mt-4">
    <button type="button" class="btn btn-secondary w-100 translate-middle-x"
            onclick="window.location.href='cliente/rutinas'" name="rutinas">Rutinas
    </button>
    <br/>
    <button type="button" class="btn btn-secondary w-100 translate-middle-x mt-3"
            onclick="window.location.href='cliente/dietas'" name="dietas">Dietas
    </button>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>