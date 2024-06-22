<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SesionDTO sesion = (SesionDTO) request.getAttribute("sesion");
    SerieDTO serie = (SerieDTO) request.getAttribute("serie");
    Map<Integer, List<String>> ejercicioParametros = (Map<Integer, List<String>>) request.getAttribute("ejercicioParametros");
    String ejercicio = (String) request.getAttribute("ejercicio");
    Integer tipoEjercicio = (Integer) request.getAttribute("tipoEjercicio");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Editar serie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<h1 class="ejemplo">Editar serie de <%=ejercicio%></h1>
<form method="post" action="/entrenador_cross_training/guardar_serie">
    <%
        int n = 1;
        String value1 = serie.getMetrica1().toString();
        String value2 = serie.getMetrica2().toString();
        for (String elemento : ejercicioParametros.get(tipoEjercicio)) {

    %>
    <div  style="width: 300px; margin-left: 40px" class="input-group mb-3">
        <span class="input-group-text btn btn-success"><%=elemento%></span>
        <input type="number" step="0.1" required name="param<%=n%>" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<%=n == 1 ? value1: value2%>">
    </div>
    <%
            n++;
        }
    %>
    <input type="hidden" name="sesion" value="<%= sesion.getId() %>">
    <input type="hidden" name="serie" value="<%= serie.getId() %>">
    <div class="div-nueva-serie">
        <button type="button" class="btn btn-danger" onclick="window.location.href='/entrenador_cross_training/sesion?id=<%=sesion.getId()%>'">Cancelar</button>
        <button name="guardar_serie" type="submit" class="btn btn-primary">Guardar serie</button>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>