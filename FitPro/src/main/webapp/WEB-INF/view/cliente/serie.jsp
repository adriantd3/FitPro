<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSerie desSerie = (DesempenyoSerie) request.getAttribute("desSerie");
    int tipo = desSerie.getEjercicio().getTipo().getId();
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Editar Serie</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Serie</h1>
</header>
<form:form method="POST" action="/cliente/guardar_serie" modelAttribute="desSerie">
    <form:hidden path="id"/>
    <form:hidden path="desempenyoSesion"/>
    <form:hidden path="ejercicio"/>
    <%
        if(tipo == 1){
    %>
        <form:label path="peso">Peso (kg)</form:label>
        <form:input path="peso" maxlength="6" name="input1"/><br>
        <form:label path="repeticiones">Repeticiones</form:label>
        <form:input path="repeticiones" maxlength="3" name="input2"/><br>
    <%
    }else if(tipo == 2){
    %>
        <form:label path="distancia">Distancia(m)</form:label>
        <form:input path="distancia" maxlength="5" name="input1"/><br>
        <form:label path="duracion">Duración (seg)</form:label>
        <form:input path="duracion" maxlength="4" name="input2"/><br>
    <%
        } else if (tipo == 3) {
    %>
        <form:label path="duracion">Duración (seg)</form:label>
        <form:input path="duracion" maxlength="3" name="input1"/><br>
        <form:label path="descanso">Descanso (seg)</form:label>
        <form:input path="descanso" maxlength="3" name="input2"/><br>
    <%
        } else if (tipo == 4 || tipo == 5) {

    %>
        <form:label path="repeticiones">Repeticiones</form:label>
        <form:input path="repeticiones" maxlength="3" name="input1"/><br>
        <form:label path="descanso">Descanso (seg)</form:label>
        <form:input path="descanso" maxlength="3" name="input2"/><br>
    <%
        }
    %>
    <form:button name="guardar">Guardar</form:button>
</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
