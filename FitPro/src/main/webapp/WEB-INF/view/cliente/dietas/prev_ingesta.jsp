<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="uma.fitpro.dto.MenuDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @Author Adrian Torremocha Doblas - 100%
     */
%>
<!doctype html>

<%
    MenuDTO menu = (MenuDTO) session.getAttribute("menu");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - DesempenyoComida</title>
</head>
<body>
<header>
    <a href="desempenyos_menu?id=<%=menu.getId()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Información de <%=menu.getNombre()%>
    </h1>
</header>
<div class="container-fluid d-flex justify-content-center mt-3">
    <div style="width: 30%">
        <jsp:include page="tabla_comidas.jsp"/>
        <form method="post" action="nueva_ingesta" class="text-center">
            <button type="submit" class="btn btn-primary" name="comenzar_ingesta">Comenzar ingesta</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>