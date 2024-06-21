<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="uma.fitpro.dto.*" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HashMap<OrdenSesionRutinaDTO,List<DesempenyoSesionDTO>> seguimientoRutina = (HashMap<OrdenSesionRutinaDTO, List<DesempenyoSesionDTO>>) request.getAttribute("seguimientoRutina");
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("cliente");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    Map<Integer,String> diasSemana = (Map<Integer, String>) request.getAttribute("diasSemana");
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
    <a href="/entrenador_cross_training/rutinas_cliente?id=<%=cliente.getId()%>">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Seguimiento de <%=cliente.getNombre()%> - <%=rutina.getNombre()%></h1>
</header>
<section class="scrollable-section" style="height: 650px">
    <% if (seguimientoRutina.keySet().isEmpty()) { %>
    <section class="table-container">
        <section class="mensaje-alerta"><h2>No hay sesiones que seguir en esta rutina</h2></section>
    </section>
    <% } %>
    <section class="sesion-table-container scrollable-content">
        <%
            String nombre_sesion = "";
            for (OrdenSesionRutinaDTO o : seguimientoRutina.keySet()){
                nombre_sesion = o.getNombreSesion() + " - " + diasSemana.get(o.getId());
        %>
        <div>
            <table name="tabla_ejercicio" style="width: 700px" class="table table-striped table-dark">
                <thead>
                <tr><th style="background-color: transparent; color: darkblue;width: 300px"><%= nombre_sesion %></th></tr>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Desempeño sesion</th>
                </tr>
                </thead>
                <tbody>
                <%
                    int cont = 1;
                    for (DesempenyoSesionDTO d : seguimientoRutina.get(o)){
                %>
                <tr>
                    <th scope="row"><%= cont %></th>
                    <td>
                        <button class="btn btn-secondary" onclick="window.location.href='/entrenador_cross_training/seguimiento_sesion?desempenyo_sesion=<%=d.getId()%>'"><%=d.getFecha()%> - <%=d.isTerminado() ? "Finalizado" : "Sin finalizar"%></button>
                    </td>

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