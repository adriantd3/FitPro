<%@ page import="uma.fitpro.entity.OrdenSesionRutina" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    String rutina = (String) request.getAttribute("nombre_rutina");
    List<OrdenSesionRutina> ordenSesionRutinas = (List<OrdenSesionRutina>) request.getAttribute("ordenSesionRutinas");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - Sesiones</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center"><%=rutina%> - Sesiones</h1>
</header>
<ul>
    <%
        for (OrdenSesionRutina ordenSesionRutina : ordenSesionRutinas) {
            String dayOfWeek = UtilityFunctions.getDayByNumber(ordenSesionRutina.getId().getOrden());
    %>
    <li>
        <a href="/cliente/desempenyo_sesion?id=<%=ordenSesionRutina.getSesion().getId()%>&nombre_sesion<%=ordenSesionRutina.getSesion().getNombre()%>">
            <%=ordenSesionRutina.getSesion().getNombre()%> - <%=dayOfWeek%>
        </a>
    </li>
    <%
        }
    %>
</ul>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>