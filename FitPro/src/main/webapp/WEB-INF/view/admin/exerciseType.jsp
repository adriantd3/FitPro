<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page import="uma.fitpro.dto.ComidaDTO" %>
<%@ page import="uma.fitpro.dto.TipoEjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<TipoEjercicioDTO> tipos = (List<TipoEjercicioDTO>) request.getAttribute("tipos");
    TipoEjercicioDTO tipo = (TipoEjercicioDTO) request.getAttribute("tipo");

    String filtroNombre = (String) request.getAttribute("filtroNombre");
    String filtroMetrica1 = (String) request.getAttribute("filtroMetrica1");
    String filtroMetrica2 = (String) request.getAttribute("filtroMetrica2");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./admin.css"%></style>
    <script>
        function rellenarDatos(id) {
            window.location.href = "/admin/exercisetype?id="+id;
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="../assets/back_button.png" alt="back" onclick="window.location.href = '/home'">
    <h1 class="header-text text-center">Tipos de ejercicios</h1>
</header>
<div class="user-wrapper">
    <table class="table-food table caption-top text-center">
        <caption class="text-center text-white">Lista de tipos</caption>
        <thead class="table-dark">
        <tr>
            <form method="post" action=/admin/exercisetype/filter>
                <th scope="col"><button type="submit">🔍</button></th>
                <th scope="col"><input value="<%=filtroNombre%>" name="nombre" type="text" placeholder="Nombre"></th>
                <th scope="col"><input value="<%=filtroMetrica1%>" name="metrica1" type="text" placeholder="Métrica 1"></th>
                <th scope="col"><input value="<%=filtroMetrica2%>" name="metrica2" type="text" placeholder="Métrica 2"></th>
            </form>
        </tr>
        </thead>
        <tbody>
        <% for(TipoEjercicioDTO t : tipos){ %>
        <tr role="button" class="<%= tipo!=null && t.getId() == tipo.getId() ? "selected-row" : ""%>" onclick=rellenarDatos(<%=t.getId()%>)>
            <td><%=t.getId()%></td>
            <td><%=t.getTipo()%></td>
            <td><%=t.getMetrica1()%></td>
            <td><%=t.getMetrica2()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <form class="user-form" method="post" action="/admin/add-exercisetype">
        <input name="Id" type="hidden" value=<%=tipo == null ? "0" : tipo.getId()%>>
        <table class="table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input name="Nombre" type="text" placeholder="Nombre" value="<%=tipo == null ? "" : tipo.getTipo()%>"></td>
                <td>Métrica 1:<input name="Metrica1" type="text" placeholder="Métrica1" value="<%=tipo == null || tipo.getMetrica1() == null ? "" : tipo.getMetrica1()%>"></td>
                <td>Métrica 2:<input name="Metrica2" type="text" placeholder="Métrica2" value="<%=tipo == null || tipo.getMetrica1() == null ? "" : tipo.getMetrica2()%>"></td>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
    <div class="form-buttons">
        <form method="post" action="/admin/delete-exercisetype">
            <input name="Id" type="hidden" value=<%=tipo == null ? "0" : tipo.getId()%>>
            <button <%= tipo != null ? "" : "disabled" %> type="submit" class="btn btn-primary etype-delete-button">Eliminar</button>
        </form>
        <button type="submit" class="btn btn-primary etype-clean-button" onclick="rellenarDatos(0)">Limpiar</button>
    </div>
</div>
</body>
</html>