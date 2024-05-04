<%@ page import="uma.fitpro.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<%
    Map<Ejercicio, List<Serie>> sesion_dict = (Map<Ejercicio, List<Serie>>) request.getAttribute("sesion_dict");
    String tipo = (String) request.getAttribute("tipo");
    Sesion sesion = (Sesion) session.getAttribute("sesion");
%>

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - DesempenyoSesion</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Información de sesión: <%=sesion.getNombre()%>
    </h1>
</header>
<section id="table-container">
    <div class="w-50 p-3">
        <%
            String col1 = tipo.equals("FUERZA") ? "Peso" : "Distancia";
            String col2 = tipo.equals("FUERZA") ? "Repeticiones" : "Duración(m)";

            for (Ejercicio ejercicio : sesion_dict.keySet()) {
        %>

        <a href="cliente/ejercicio?id=<%=ejercicio.getId()%>" class="text-primary fs-4"><%=ejercicio.getNombre()%>
        </a>
        <table class="table table-striped text-center">
            <thead class="table-dark">
            <tr>
                <th scope="col">Serie</th>
                <th scope="col"><%=col1%></th>
                <th scope="col"><%=col2%></th>
            </tr>
            </thead>
            <tbody>
            <%
                int numSerie = 1;
                for (Serie serie : sesion_dict.get(ejercicio)) {

            %>
            <tr>
                <th scope="row"><%=numSerie%></th>
                <td><%=serie.getPeso()%></td>
                <td><%=serie.getRepeticiones()%></td>
            </tr>
            <%
                    numSerie++;
                }
            %>
            </tbody>
        </table>

        <%
            }
        %>
        <form method="post" action="nuevo_desempenyo_sesion">
            <button type="submit" class="btn btn-primary">Nuevo entrenamiento</button>
        </form>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>