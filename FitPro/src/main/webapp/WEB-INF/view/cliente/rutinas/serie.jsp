<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.*" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSerieDTO desSerie = (DesempenyoSerieDTO) request.getAttribute("desSerie");
    Integer tipo = (Integer) request.getAttribute("tipo_ejercicio");

    List<String> cols = UtilityFunctions.getEjercicioParametros().get(tipo);
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Editar Serie</title>
</head>
<body>
<header>
    <a href="entrenamiento?id=<%=desSerie.getDesempenyoSesion()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Serie</h1>
</header>
<form:form method="POST" action="guardar_serie" modelAttribute="desSerie" cssClass="text-light fs-5 m-3">
    <form:hidden path="id"/>
    <form:hidden path="desempenyoSesion"/>
    <form:hidden path="ejercicio"/>

    <form:label path="metrica1"><%=cols.get(0)%></form:label>
    <form:input path="metrica1" maxlength="6" id="input1" type="number"/><br>

    <form:label path="metrica2"><%=cols.get(1)%>></form:label>
    <form:input path="metrica2" maxlength="6" id="input2" type="number"/><br>

    <form:button name="guardar" class="btn btn-primary mt-2">Guardar</form:button>
</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
