<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Comida> comidas = (List<Comida>) request.getAttribute("comidas");
    Comida comida = (Comida) request.getAttribute("comida");
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
            window.location.href = "/admin/food?id="+id;
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Comidas</h1>
</header>
<div class="user-wrapper">
    <table class="table-food table table-dark">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Calorias</th>
        </tr>
        </thead>
        <tbody>
        <% for(Comida c : comidas){ %>
        <tr class="<%= comida!=null && c.getId() == comida.getId() ? "selected-row" : ""%>" onclick=rellenarDatos(<%=c.getId()%>)>
            <td><%=c.getId()%></td>
            <td><%=c.getNombre()%></td>
            <td><%=c.getCalorias()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <form class="user-form" method="post" action="/admin/add-food">
        <input name="Id" type="hidden" value=<%=comida == null ? "0" : comida.getId()%>>
        <table class="table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input name="Nombre" type="text" placeholder="Nombre" value="<%=comida == null ? "" : comida.getNombre()%>"></td>
                <td>Calorías:<input name="Calorias" type="text" placeholder="Calorias" value="<%=comida == null || comida.getCalorias() == null ? "" : comida.getCalorias()%>"></td>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
    <div class="form-buttons">
        <form method="post" action="/admin/delete-food">
            <input name="Id" type="hidden" value=<%=comida == null ? "0" : comida.getId()%>>
            <button <%= comida != null ? "" : "disabled" %> type="submit" class="btn btn-primary food-delete-button">Eliminar</button>
        </form>
        <button type="submit" class="btn btn-primary food-clean-button" onclick="rellenarDatos(0)">Limpiar</button>
    </div>
</div>
</body>
</html>