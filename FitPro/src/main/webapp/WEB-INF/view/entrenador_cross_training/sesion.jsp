<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="uma.fitpro.entity.Serie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer, List<String>> ejercicioParametros = (Map<Integer, List<String>>) request.getAttribute("ejercicioParametros");
    Sesion sesion = (Sesion) request.getAttribute("sesion");
    TreeMap<Ejercicio, List<Serie>> mapa = (TreeMap<Ejercicio, List<Serie>>) request.getAttribute("mapa");
    session.setAttribute("sesion", sesion);
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sesion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/sesiones">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center"><%= sesion.getNombre()%></h1>
</header>
<section class="scrollable-section">
    <section class="sesion-table-container scrollable-content">
        <%
            String nombre_ejercicio = "";
            for (Ejercicio e : mapa.keySet()){
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
                    for (Serie s : mapa.get(e)){

                %>
                <tr>
                    <th scope="row"><%= cont %></th>
                    <%
                        if (e.getTipo().getId() == 1){
                    %>
                        <td><%= s.getPeso() %></td>
                        <td><%= s.getRepeticiones() %></td>
                    <%
                        }
                    %>
                    <%
                        if (e.getTipo().getId() == 2){
                    %>
                    <td><%= s.getDistancia() %></td>
                    <td><%= s.getDuracion() %></td>
                    <%
                        }
                    %>
                    <%
                        if (e.getTipo().getId() == 3){
                    %>
                    <td><%= s.getDuracion() %></td>
                    <td><%= s.getDescanso() %></td>
                    <%
                        }
                    %>
                    <%
                        if (e.getTipo().getId() == 4 || e.getTipo().getId() == 5){
                    %>
                    <td><%= s.getRepeticiones() %></td>
                    <td><%= s.getDescanso() %></td>
                    <%
                        }
                    %>

                    <td style="box-shadow: none;background-color: #434343;border-bottom-width: 0px;">
                        <form action="/entrenador_cross_training/borrar_serie" method="post">
                            <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
                            <input type="hidden" name="serie" value="<%=s.getId()%>">
                            <button type="submit" class="btn btn-link">Borrar</button>
                        </form>
                    </td>
                </tr>
                <%
                        cont++;
                    }
                %>
                </tbody>
            </table>
            <div style="display: flex;justify-content: center;">
                <button name="anyadir_serie" type="button" class="btn btn-primary" onclick="window.location.href='/entrenador_cross_training/anyadir_serie?sesion=<%=sesion.getId()%>&ejercicio=<%=e.getId()%>'">Añadir serie</button>
            </div>
        </div>
        <%
            }
        %>

    </section>
</section>

<section class="sesion-buttons">
    <button type="button" class="btn btn-success" onclick="window.location.href='/entrenador_cross_training/sesiones'">Guardar</button>
    <button type="button" class="btn btn-danger" onclick="window.location.href='/entrenador_cross_training/borrar_sesion?id=<%= sesion.getId()%>'">Borrar</button>
    <button name="anyadir_ejercicio" type="button" class="btn btn-primary" onclick="window.location.href='/entrenador_cross_training/ejercicios?ejercicio=&musculo=&tipo='">Añadir ejercicio</button>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>