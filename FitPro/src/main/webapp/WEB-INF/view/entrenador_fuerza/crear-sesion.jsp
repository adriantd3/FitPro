<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="uma.fitpro.entity.Serie" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Rutina rutina = (Rutina) session.getAttribute("rutina");
    Sesion sesion = (Sesion) request.getAttribute("sesion");
    HashMap<Ejercicio, List<Serie>> tablas = new HashMap<>();
    for(Serie serie : sesion.getSeries()){
        tablas.computeIfAbsent(serie.getEjercicio(), k -> new ArrayList<>());
        tablas.get(serie.getEjercicio()).add(serie);
    }
    Serie serie = (Serie) request.getAttribute("serie");
%>
<html>
<head>
    <title>Sesion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/rutina?rutina=<%=rutina.getId()%>'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center"><%=sesion.getNombre()%></h1> <!-- Mostrar informacion relevante en la X-->
</header>

<section class="mt-3 ms-3 h-100 d-flex">
    <div class="w-50">
    <%
        for (Ejercicio ejercicio : tablas.keySet()){
    %>

        <table style="border-spacing: 0" class="table caption-top text-center w-100 ">
            <a href="#" class="d-block fs-3"><%=ejercicio.getNombre()%></a>
            <thead class="table-dark">
            <tr>
                <th class="nombre-menu">Peso</th>
                <th class="kcal">Repeticiones</th>
                <th style="background-color: transparent;border-bottom-width: 0"></th>
                <th style="background-color: transparent;border-bottom-width: 0"></th>
            </tr>
            </thead>
            <tbody style="border-top: 0 !important;" class = "table-group-divider table-secondary">
            <%
                for(Serie s : tablas.get(ejercicio)){
            %>

            <tr onclick="">
                <td><%= s.getPeso() %></td>
                <td><%= s.getRepeticiones() %></td>
                <td style="background-color: transparent !important; border-bottom-width: 0">
                    <a style="color: yellow" href="/entrenador_fuerza/editar-serie?serie=<%=s.getId()%>">Editar Serie</a>
                </td>
                <td style="background-color: transparent !important; border-bottom-width: 0">
                    <a style="color: red" href="/entrenador_fuerza/eliminar-serie?serie=<%=s.getId()%>">Eliminar Serie</a>
                </td>
            </tr>

            <%
                }
            %>
            </tbody>
        </table>

        <button class="btn btn-primary mb-5"
                onclick="window.location.href=
                    '/entrenador_fuerza/anyadir-serie?sesion=<%=sesion.getId()%>&ejercicio=<%=ejercicio.getId()%>'">
            Añadir Serie
        </button>
        <%
            }
        %>
    </div>
    <%
        if(serie != null){
    %>
    <div class="w-50 align-items-center d-flex flex-column">
        <h1 class="pb-2" style="color: white"> Serie de <%=serie.getEjercicio().getNombre()%></h1>
        <form:form method="post" action="/entrenador_fuerza/guardar-serie" modelAttribute="serie">
            <form:label cssStyle="color: white; width: 110px" path="peso" >Peso: </form:label>
            <form:input path="peso" type="number"/>
            <br>
            <form:label cssStyle="color: white; width: 110px" path="repeticiones" >Repeticiones: </form:label>
            <form:input path="repeticiones" type="number"/>
            <br>

            <form:input type="hidden" path="id" value="<%=serie.getId()%>"/>
            <form:input type="hidden" path="sesion" value="<%=serie.getSesion().getId()%>"/>
            <form:input type="hidden" path="ejercicio" value="<%=serie.getEjercicio().getId()%>"/>
            <form:input type="hidden" path="distancia" value="0"/>
            <form:input type="hidden" path="duracion" value="0"/>

            <input class="btn btn-success" type="submit" value="Guardar">
        </form:form>
    </div>
    <%
        }
    %>
</section>

<footer class="m-3 fixed-bottom">
    <button class="btn btn-danger">
        Borrar
    </button>
    <button class="btn btn-primary" onclick="window.location.href='/entrenador_fuerza/asignar-ejercicio?sesion=<%=sesion.getId()%>'">
        Añadir Ejercicio
    </button>
</footer>

</body>
</html>
