<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.OrdenSesionRutinaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @Author Adrian Torremocha Doblas - 100%
     */
%>
<!doctype html>
<%
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - Sesiones</title>
</head>
<body>
<header>
    <a href="/cliente/rutinas">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center"><%=rutina.getNombre()%> - Sesiones</h1>
</header>
<div class="d-flex justify-content-center mt-4">
    <%if(rutina.getOrdenSesionRutinaList().isEmpty()){%>
    <h2 class="text-light">No hay sesiones registradas</h2>
    <%}%>
    <ul class="text-light">
        <%
            for (OrdenSesionRutinaDTO ordenSesionRutina : rutina.getOrdenSesionRutinaList()) {
                String dayOfWeek = UtilityFunctions.getDayByNumber(ordenSesionRutina.getId());
        %>
        <li>
            <a class="text-primary fs-5" href="desempenyos_sesion?id=<%=ordenSesionRutina.getIdSesion()%>&rutina_id=<%=rutina.getId()%>">
                <%=ordenSesionRutina.getNombreSesion()%> - <%=dayOfWeek%>
            </a>
        </li>
        <%
            }
        %>
    </ul>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>