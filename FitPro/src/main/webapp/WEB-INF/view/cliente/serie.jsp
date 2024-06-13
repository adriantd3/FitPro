<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSerie desSerie = (DesempenyoSerie) request.getAttribute("desSerie");
    int tipo = desSerie.getEjercicio().getTipo().getId();
    Map<Integer,List<String>> params = UtilityFunctions.getEjercicioParametros();
    List<String> cols = params.get(tipo);
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

    <form:label path="metrica1"><%=cols.get(0)%></form:label>
    <form:input path="metrica1" maxlength="6" id="input1"/><br>

    <form:label path="metrica2"><%=cols.get(1)%>></form:label>
    <form:input path="metrica2" maxlength="6" id="input2"/><br>

    <form:button name="guardar">Guardar</form:button>
</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
