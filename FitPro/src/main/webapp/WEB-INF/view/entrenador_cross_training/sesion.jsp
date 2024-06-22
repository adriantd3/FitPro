<%// AUTOR: Ezequiel Sánchez García (100%)%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer, List<String>> ejercicioParametros = (Map<Integer, List<String>>) request.getAttribute("ejercicioParametros");
    SesionDTO sesion = (SesionDTO) request.getAttribute("sesion");
    TreeMap<EjercicioDTO, List<SerieDTO>> mapa = (TreeMap<EjercicioDTO, List<SerieDTO>>) request.getAttribute("mapa");
    session.setAttribute("sesion", sesion);
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Editar sesion - <%=sesion.getNombre()%></title>
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
    <% if (mapa.keySet().isEmpty()) { %>
    <section class="table-container">
        <section class="mensaje-alerta"><h2>Aún no hay ejercicios en esta sesión</h2></section>
    </section>
    <% } %>
    <section class="sesion-table-container scrollable-content">
        <%
            for (EjercicioDTO e : mapa.keySet()){
        %>
        <div>
            <table name="tabla_ejercicio" style="width: 700px" class="table table-striped table-dark">
                <thead>
                <tr>
                    <th style="background-color: transparent;width: 300px">
                        <a style="color: darkblue" href="ejercicio?id=<%=e.getId()%>" target="_blank"><%=e.getNombre()%>
                        </a>
                    </th>
                </tr>
                <tr>
                    <th scope="col">Serie</th>
                    <th scope="col"><%=ejercicioParametros.get(e.getTipo().getId()).get(0)%></th>
                    <th scope="col"><%=ejercicioParametros.get(e.getTipo().getId()).get(1)%></th>
                </tr>
                </thead>
                <tbody>
                <%
                    int cont = 1;
                    for (SerieDTO s : mapa.get(e)){

                %>
                <tr>
                    <th scope="row"><%= cont %></th>
                    <td><%= s.getMetrica1() %></td>
                    <td><%= s.getMetrica2() %></td>

                    <td style="box-shadow: none;background-color: #434343;border-bottom-width: 0px;">
                        <div style="display: flex;gap: 10px">
                            <form action="/entrenador_cross_training/editar_serie" method="get">
                                <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
                                <input type="hidden" name="serie" value="<%=s.getId()%>">
                                <button type="submit" class="btn btn-warning button-image">
                                    <img class="image-button" src="${pageContext.request.contextPath}/assets/editar_button.svg">
                                </button>
                            </form>
                            <form action="/entrenador_cross_training/borrar_serie" method="post">
                                <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
                                <input type="hidden" name="serie" value="<%=s.getId()%>">
                                <button type="submit" class="btn btn-danger button-image">
                                    <img class="image-button" src="${pageContext.request.contextPath}/assets/delete_button.svg">
                                </button>
                            </form>
                        </div>
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
    <form method="post" action="/entrenador_cross_training/borrar_sesion">
        <input type="hidden" name="id" value="<%=sesion.getId()%>">
        <button class="btn btn-danger">Borrar sesión</button>
    </form>
    <button name="anyadir_ejercicio" type="button" class="btn btn-primary" onclick="window.location.href='/entrenador_cross_training/ejercicios?ejercicio=&musculo=&tipo='">Añadir ejercicio</button>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>