<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoComidaDTO desComida = (DesempenyoComidaDTO) request.getAttribute("des_comida");
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Editar Desempeño Comida</title>
</head>
<body>
<header>
    <a href="ingesta?id=<%=desComida.getDesempenyoMenu()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Desempeño Comida: <%=desComida.getComida().getNombre()%></h1>
</header>
<form:form method="POST" action="guardar_des_comida" modelAttribute="des_comida" cssClass="text-light p-3 text-center">
    <form:hidden path="id"/>
    <form:hidden path="desempenyoMenu"/>

    <form:label path="comido" cssClass="fs-5">¿Te has comido el plato?</form:label><br>
    <form:radiobutton path="comido" value="true" label=" Sí  " />
    <form:radiobutton path="comido" value="false" label=" No" /><br>

    <form:label path="gustado" cssClass="fs-5 mt-2">¿Te ha gustado el plato?</form:label><br>
    <form:radiobutton path="gustado" value="true" label=" Sí  " />
    <form:radiobutton path="gustado" value="false" label=" No" /><br>

    <form:button name="guardar" class="btn btn-primary mt-3">Guardar</form:button>
</form:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
