<%@ page import="uma.fitpro.entity.DesempenyoSesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String desempenyoSesion = (String) request.getAttribute("desempenyo_sesion_fecha");
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - ResultadosSesion</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Resultados entrenamiento - <%=desempenyoSesion%>
    </h1>
</header>
<div class="text-center text-light pt-2">
    <form:form method="post" action="/cliente/filtro_series" modelAttribute="filtro">
        Nombre <form:input path="ejercicio"/>
        Peso > <form:input path="peso" size="5" maxlength="5" type="number"/>
        Repeticiones > <form:input path="repeticiones" size="3" maxlength="3" type="number"/>
        Distancia > <form:input path="distancia" size="5" maxlength="5" type="number"/>
        Duracion > <form:input path="duracion" size="5" maxlength="5" type="number"/>
        Descanso ><form:input type="number" path="descanso" maxlength="5"/>
        Grupo muscular<form:select path="grupoMuscular">
                            <form:option value="<%=0%>" label="Ninguno"/>
                            <form:options items="${grupo_muscular}" itemLabel="grupoMuscular" itemValue="id"/>
                        </form:select>
        Tipo de ejercicio<form:select path="tipoEjercicio">
                            <form:option value="<%=0%>" label="Ninguno"/>
                            <form:options items="${tipo_ejercicio}" itemLabel="tipo" itemValue="id"/>
                        </form:select>
        <br>
        <form:button>Filtrar</form:button>
    </form:form>
</div>


<jsp:include page="filtro.jsp"></jsp:include>
<div class="container-fluid p-2">
    <div class="d-flex justify-content-between">
        <div class="w-50 m-4">
            <h1 class="header-text text-center">Sesión original</h1>
            <jsp:include page="tablas_series.jsp">
                <jsp:param name="dict" value="sesion"/>
            </jsp:include>
        </div>
        <div class="w-50 m-4">
            <h1 class="header-text text-center">Sesión de entrenamiento</h1>
            <jsp:include page="tablas_series.jsp">
                <jsp:param name="dict" value="des"/>
            </jsp:include>
        </div>
    </div>
</div>
</body>
</html>
