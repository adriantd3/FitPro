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

<section class="mt-3 ms-3 h-100">
    <%
        for (Ejercicio ejercicio : tablas.keySet()){
    %>
    <table class="table caption-top text-center w-50 ">
        <a href="#" class="d-block fs-3"><%=ejercicio.getNombre()%></a>
        <thead class="table-dark">
        <tr>
            <th class="id">Serie</th>
            <th class="nombre-menu">Peso</th>
            <th class="kcal">Repeticiones</th>
        </tr>
        </thead>
        <tbody class = "table-group-divider table-secondary">
        <%
            for(Serie serie : tablas.get(ejercicio)){
        %>

        <tr onclick="">
            <td><%= serie.getId().getId()%></td>
            <td><%= serie.getPeso() %></td>
            <td><%= serie.getRepeticiones() %></td>
        </tr>

        <%
            }
        %>
        </tbody>
    </table>

    <button class="btn btn-primary mb-5">
        Añadir Serie
    </button>
    <%
        }
    %>
</section>

<footer class="m-3">
    <button class="btn btn-danger">
        Borrar
    </button>
    <button class="btn btn-primary" onclick="window.location.href='/entrenador_fuerza/asignar-ejercicio?sesion=<%=sesion.getId()%>'">
        Añadir Ejercicio
    </button>
</footer>

</body>
</html>
