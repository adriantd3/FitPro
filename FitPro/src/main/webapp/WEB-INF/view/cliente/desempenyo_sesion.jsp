<%@ page import="uma.fitpro.entity.OrdenSesionRutina" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.utils.DateConversor" %>
<%@ page import="uma.fitpro.entity.DesempenyoSesion" %>
<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    List<DesempenyoSesion> desempenyoSesions = (List<DesempenyoSesion>) request.getAttribute("desempenyos");
    String nombreSesion = (String) request.getAttribute("sesion_name");
%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - DesempeñoSesion</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Desempeños Sesion - <%=nombreSesion%></h1>
</header>
<ul>
    <%
        for (DesempenyoSesion desempenyoSesion : desempenyoSesions){
    %>
    <li>
        <a href="/cliente/resultados_sesion?id=<%=desempenyoSesion.getId()%>">
            <%=desempenyoSesion.getFecha().toString()%>
        </a>
    </li>
    <%
        }
    %>
</ul>
<button type="button" class="btn btn-primary" onclick="window.location.href='cliente/nuevo_desempenyo'">Nuevo entrenamiento</button>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>