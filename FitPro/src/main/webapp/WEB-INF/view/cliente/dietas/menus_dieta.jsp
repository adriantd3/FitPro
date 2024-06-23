<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page import="uma.fitpro.dto.DietaDTO" %>
<%@ page import="uma.fitpro.dto.OrdenMenuDietaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @Author Adrian Torremocha Doblas - 100%
     */
%>
<!doctype html>
<%
    DietaDTO dieta = (DietaDTO) request.getAttribute("dieta");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - Dietas</title>
</head>
<body>
<header>
    <a href="/cliente/dietas">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center"><%=dieta.getNombre()%> - Menús</h1>
</header>
<div class="d-flex justify-content-center mt-3">
    <%if(dieta.getOrdenMenuDietaList().isEmpty()){%>
    <h2 class="text-light">No hay menús registrados</h2>
    <%}%>
    <ul class="text-light fs-5">
        <%
            for (OrdenMenuDietaDTO ordenMenuDieta : dieta.getOrdenMenuDietaList()) {
                String dayOfWeek = UtilityFunctions.getDayByNumber(ordenMenuDieta.getOrden());
        %>
        <li>
            <a href="desempenyos_menu?id=<%=ordenMenuDieta.getMenu().getId()%>&dieta_id=<%=dieta.getId()%>" class="text-primary">
                <%=ordenMenuDieta.getNombreMenu()%> - <%=dayOfWeek%>
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
