<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer, List<String>> ejercicioParametros = (Map<Integer, List<String>>) request.getAttribute("ejercicioParametros");
    DesempenyoSesionDTO desempenyoSesion = (DesempenyoSesionDTO) request.getAttribute("desempenyoSesion");
    Map<EjercicioDTO, List<DesempenyoSerieDTO>> mapa = (Map<EjercicioDTO, List<DesempenyoSerieDTO>>) request.getAttribute("mapa");
    RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
    UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Seguimiento cliente - <%=cliente.getNombre()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/seguimiento_cliente?cliente=<%=cliente.getId()%>&rutina=<%=rutina.getId()%>">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Seguimiento <%= desempenyoSesion.getNombreSesion()%> - <%=cliente.getNombre()%></h1>
</header>
<section class="scrollable-section">
    <% if (mapa.keySet().isEmpty()) { %>
    <section class="table-container">
        <section class="mensaje-alerta"><h2>No hay ejercicios en esta sesion</h2></section>
    </section>
    <% } %>
    <section class="sesion-table-container scrollable-content">
        <%
            String nombre_ejercicio = "";
            for (EjercicioDTO e : mapa.keySet()){
                nombre_ejercicio = e.getNombre();
        %>
        <div>
            <table name="tabla_ejercicio" style="width: 700px" class="table table-striped table-dark">
                <thead>
                <tr><th style="background-color: transparent; color: darkblue;width: 300px"><%= nombre_ejercicio %></th></tr>
                <tr>
                    <th scope="col">Serie</th>
                    <th scope="col"><%=ejercicioParametros.get(e.getTipo().getId()).get(0)%></th>
                    <th scope="col"><%=ejercicioParametros.get(e.getTipo().getId()).get(1)%></th>
                </tr>
                </thead>
                <tbody>
                <%
                    int cont = 1;
                    for (DesempenyoSerieDTO s : mapa.get(e)){

                %>
                <tr>
                    <th scope="row"><%= cont %></th>
                    <td><%= s.getMetrica1() %></td>
                    <td><%= s.getMetrica2() %></td>
                </tr>
                <%
                        cont++;
                    }
                %>
                </tbody>
            </table>
        </div>
        <%
            }
        %>

    </section>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>