<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.DesempenyoSesionDTO" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page import="uma.fitpro.dto.DesempenyoSerieDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 15/6/24
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSesionDTO desempenyoSesion = (DesempenyoSesionDTO) request.getAttribute("desempenyoSesion");
    Map<EjercicioDTO, List<SerieDTO>> tablas = (Map<EjercicioDTO, List<SerieDTO>>) request.getAttribute("tablas");
%>
<html>
<head>
    <title>Desempeño en <%=desempenyoSesion.getNombreSesion()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
<img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
     onclick="window.location.href='/entrenador_fuerza/seguimiento'"> <!-- Controlar pagina anterior por modelo -->
<h1 class="header-text text-center">Desempeño en <%=desempenyoSesion.getNombreSesion()%></h1> <!-- Mostrar informacion relevante en la X-->
</header>

<section class="m-3 h-100 d-flex flex-column align-items-center">
    <%
        for(EjercicioDTO ejercicio : tablas.keySet()){
    %>
    <table style="border-spacing: 0" class="table caption-top text-center w-50 ">
        <a href="/cliente/rutinas/ejercicio?id=<%=ejercicio.getId()%>%>" class="d-block fs-3"><%=ejercicio.getNombre()%></a>
        <thead class="table-dark">
            <tr>
                <th class="nombre-menu">Peso</th>
                <th class="kcal">Repeticiones</th>
            </tr>
        </thead>
        <tbody>
        <%
            for(SerieDTO desempenyoSerie : tablas.get(ejercicio)){
        %>
        <tr>
            <td><%=desempenyoSerie.getMetrica1()%></td>
            <td><%=desempenyoSerie.getMetrica2()%></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</section>
</body>
</html>
