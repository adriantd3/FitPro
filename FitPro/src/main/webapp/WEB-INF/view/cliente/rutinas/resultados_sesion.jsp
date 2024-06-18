<%@ page import="uma.fitpro.dto.DesempenyoSesionDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    DesempenyoSesionDTO desempenyoSesion = (DesempenyoSesionDTO) request.getAttribute("desempenyo_sesion");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fecha = desempenyoSesion.getFecha().format(formatter);
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - Resultados Sesion</title>
</head>
<body>
<header>
    <a href="desempenyos_sesion?id=<%=desempenyoSesion.getIdSesion()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">
        <%=desempenyoSesion.getNombreSesion()%> - Resultados del <%=fecha%>
    </h1>
</header>
<div class="text-center text-light pt-2">
    <form:form method="post" action="filtro_series" modelAttribute="filtro">
        <form:hidden path="sesionId"/>
        <form:hidden path="desSesionId"/>

        Nombre <form:input path="ejercicio"/>
        Grupo Muscular <form:select path="grupoMuscular">
                            <form:option value="0" label="Ninguno"/>
                            <form:options items="${grupo_muscular}" itemLabel="grupoMuscular" itemValue="id"/>
                        </form:select>
        Tipo de ejercicio <form:select path="tipoEjercicio">
                            <form:option value="<%=0%>" label="Ninguno"/>
                            <form:options items="${tipo_ejercicio}" itemLabel="tipo" itemValue="id"/>
                        </form:select>
        <br>
        <form:button>Filtrar</form:button>
    </form:form>
</div>

<div class="container-fluid p-2">
    <div class="d-flex justify-content-between">
        <div class="w-50 m-4">
            <h1 class="header-text text-center">Resultado objetivo</h1>
            <jsp:include page="tablas_series.jsp">
                <jsp:param name="dict" value="sesion"/>
            </jsp:include>
        </div>
        <div class="w-50 m-4">
            <h1 class="header-text text-center">Resultado obtenido</h1>
            <jsp:include page="tablas_series.jsp">
                <jsp:param name="dict" value="des"/>
            </jsp:include>
        </div>
    </div>
</div>
</body>
</html>
