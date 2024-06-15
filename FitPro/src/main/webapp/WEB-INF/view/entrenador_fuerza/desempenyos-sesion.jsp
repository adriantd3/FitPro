<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="uma.fitpro.entity.DesempenyoSesion" %>
<%@ page import="uma.fitpro.entity.DesempenyoSerie" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 15/6/24
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSesion desempenyoSesion = (DesempenyoSesion) request.getAttribute("desempenyoSesion");
    HashMap<Ejercicio, List<DesempenyoSerie>> tablas = new HashMap<>();
    for(DesempenyoSerie desempenyoSerie : desempenyoSesion.getDesempenyoSeries()){
        tablas.computeIfAbsent(desempenyoSerie.getEjercicio(), k -> new ArrayList<>());
        tablas.get(desempenyoSerie.getEjercicio()).add(desempenyoSerie);
    }
%>
<html>
<head>
    <title>Desempeño en <%=desempenyoSesion.getSesion().getNombre()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
<img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
     onclick="window.location.href='/entrenador_fuerza/seguimiento'"> <!-- Controlar pagina anterior por modelo -->
<h1 class="header-text text-center">Desempeño en <%=desempenyoSesion.getSesion().getNombre()%></h1> <!-- Mostrar informacion relevante en la X-->
</header>

<section class="m-3 h-100 d-flex flex-column align-items-center">
    <%
        for(Ejercicio ejercicio : tablas.keySet()){
    %>
    <table style="border-spacing: 0" class="table caption-top text-center w-50 ">
        <a href="/cliente/ejercicio?id=<%=ejercicio.getId()%>" class="d-block fs-3"><%=ejercicio.getNombre()%></a>
        <thead class="table-dark">
            <tr>
                <th class="nombre-menu">Peso</th>
                <th class="kcal">Repeticiones</th>
            </tr>
        </thead>
        <tbody>
        <%
            for(DesempenyoSerie desempenyoSerie : tablas.get(ejercicio)){
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
