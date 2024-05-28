<%@ page import="uma.fitpro.entity.DesempenyoSesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1 class="header-text text-center">Resultados entrenamiento - <%=desempenyoSesion%></h1>
</header>
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
