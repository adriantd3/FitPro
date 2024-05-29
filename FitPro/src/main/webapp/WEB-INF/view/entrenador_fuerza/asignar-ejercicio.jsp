<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dao.EjercicioRepository" %>
<%@ page import="uma.fitpro.entity.Serie" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Sesion sesion = (Sesion) request.getAttribute("sesion");
    Serie serie = (Serie) request.getAttribute("serie");
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    System.out.println(ejercicios.size());
    Ejercicio ejercicio = new Ejercicio();
%>
<html>
<head>
    <title>Ejercicios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/sesion?sesion=<%=sesion.getId()%>'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<form:form id="form" method="post" action="/entrenador_fuerza/guardar-ejercicio" modelAttribute="serie">
    <form:input type="hidden" path="ejercicio" id="ejercicio_id"/>
    <form:input type="hidden" path="sesion" id="sesion_id" value="<%=sesion.getId()%>"/>
</form:form>

<section class="mt-3 ms-3 h-100">
    <ul class="list-group m-3">
        <%
            for(Ejercicio ej : ejercicios){
        %>
        <button name=<%=ej.getNombre()%> onclick="submitForm(<%=ej.getId()%>)" class="list-button list-group-item" id=<%=ejercicio.getId()%>>
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
