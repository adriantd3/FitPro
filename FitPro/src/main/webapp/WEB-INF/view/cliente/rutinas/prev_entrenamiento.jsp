<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<%
    SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - DesempenyoSesion</title>
</head>
<body>
<header>
    <a href="desempenyos_sesion?id=<%=sesion.getId()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Información de <%=sesion.getNombre()%>
    </h1>
</header>
<div class="container-fluid d-flex justify-content-center">
    <div class="p-3" style="width: 40%">
        <jsp:include page="tablas_series.jsp">
            <jsp:param name="dict" value="sesion"/>
        </jsp:include>
        <form method="post" action="nuevo_desempenyo_sesion" class="text-center mt-4">
            <button type="submit" class="btn btn-primary" name="comenzar_entrenamiento">Comenzar entrenamiento</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>