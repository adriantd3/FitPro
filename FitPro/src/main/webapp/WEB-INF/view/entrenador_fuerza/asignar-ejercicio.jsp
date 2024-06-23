<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %>
<%@ page import="uma.fitpro.ui.FiltroEjercicio" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    SesionDTO sesion = (SesionDTO) request.getAttribute("sesion");
    List<EjercicioDTO> ejercicios = (List<EjercicioDTO>) request.getAttribute("ejercicios");
    EjercicioDTO ejercicio = new EjercicioDTO();
%>
<html>
<head>
    <title>Ejercicios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header class="sticky-top">
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/sesion?sesion=<%=sesion.getId()%>'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<form id="form" method="post" action="/entrenador_fuerza/guardar-ejercicio" modelAttribute="serie">
    <input type="hidden" name="ejercicio" id="ejercicio_id"/>
    <input type="hidden" name="sesion" id="sesion_id" value="<%=sesion.getId()%>"/>
</form>

<section class="mt-3 ms-3 h-100">
    <div class="d-flex justify-content-start">
        <form:form method="post" action="/entrenador_fuerza/asignar-ejercicio/filtro"
                   modelAttribute="filtroEjercicio" cssClass="p-2" cssStyle="border: 1px solid white; border-radius: 10px">
            <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
            <form:label path="nombre" cssStyle="color: white">Nombre: </form:label>
            <form:input cssClass="me-3" type="text" path="nombre" id="nombre"/>

            <form:label path="grupoMuscular" cssStyle="color: white">Grupo Muscular: </form:label>
            <form:input type="text" path="grupoMuscular" id="grupoMuscular"/>
            <input type="submit" value="Filtrar" class="btn btn-info">
        </form:form>
    </div>
    <ul class="list-group m-3">
        <%
            for(EjercicioDTO ej : ejercicios){
        %>
        <button onclick="submitForm(<%=ej.getId()%>)" class="list-button list-group-item" id=<%=ejercicio.getId()%>>
            <%=ej.getNombre()%>
        </button>
            <%
            }
        %>
</section>

<script>

    function submitForm(id){
        const input_id = document.getElementById("ejercicio_id");
        input_id.value = id;

        console.log(document.getElementById("ejercicio_id").value);
        console.log(document.getElementById("sesion_id").value);

        const form = document.getElementById("form");
        form.submit();

    }
</script>
</body>
</html>
