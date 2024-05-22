<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
    List<Usuario> todos_trabajadores = (List<Usuario>) request.getAttribute("todos_trabajadores");
    Set<Usuario> cliente_trabajadores = (Set<Usuario>) request.getAttribute("cliente_trabajadores");
    Usuario cliente = (Usuario) request.getAttribute("cliente");
    Usuario trabajador_propio = (Usuario) request.getAttribute("trabajador_propio");
    Usuario trabajador_nuevo = (Usuario) request.getAttribute("trabajador_nuevo");

    String filtroNombre = (String) request.getAttribute("filtroNombre");
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
            window.location.href = "/admin/assignment?id="+id;
        }

        function seleccionarEntrenadorPropio(id, id_trabajadorPropio) {
            window.location.href = "/admin/assignment?id="+id+"&id_propio="+id_trabajadorPropio;
        }

        function seleccionarEntrenadorNuevo(id, id_trabajadorNuevo) {
            window.location.href = "/admin/assignment?id="+id+"&id_nuevo="+id_trabajadorNuevo;
        }
    </script>
</head>
<body>
<header>
    <form>
        <img class="back-button ms-1 mt-1 " src="../assets/back_button.png" alt="Back" onclick="window.location.href = '/home'">
    </form>
    <h1 class="header-text text-center">Asignación</h1>
</header>
<div class="user-wrapper assignment-gap">
    <div>
        <table class="table-assignment table caption-top text-center">
            <caption class="text-center text-white">Lista de Clientes</caption>
            <thead class="table-dark">
            <tr>
                <form method="post" action=/admin/assignment/filter>
                    <th scope="col"><button type="submit">🔍</button></th>
                    <th scope="col"><input value="<%=filtroNombre%>" name="nombre" type="text" placeholder="Nombre"></th>
                </form>
            </tr>
            </thead>
            <tbody>
            <% for(Usuario c : clientes){ %>
            <tr role="button" class="<%= cliente!=null && c.getId() == cliente.getId() ? "selected-row" : ""%>" onclick=rellenarDatos(<%=c.getId()%>)>
                <td><%=c.getId()%></td>
                <td><%=c.getNombre()%></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% if(cliente != null) { %>
    <div class="assignment-wrapper">
        <div class="trabajador-table">
        <table class="table caption-top text-center">
            <caption class="text-center text-white">Lista de trabajadores asignados</caption>
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Trabajador del cliente</th>
                <th scope="col">Rol</th>
            </tr>
            </thead>
            <tbody>
            <% for(Usuario c : cliente_trabajadores){ %>
            <tr role="button" class="<%= trabajador_propio!=null && c.getId() == trabajador_propio.getId() ? "selected-row" : ""%>" onclick="seleccionarEntrenadorPropio(<%=cliente.getId()%>,<%=c.getId()%>)">
                <td><%=c.getId()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getRol().getNombre()%></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        </div>
        <div class="assignment-buttons">
            <form action="/admin/add_trabajador_propio" method="post">
                <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
                 <% if (trabajador_nuevo != null ) { %> <input type="hidden" name="trabajadorId" value="<%=trabajador_nuevo.getId()%>"> <% } %>
                <button class="swap-button" type="submit" <%= trabajador_nuevo != null ? "" : "disabled" %>> ⬅️ </button>
            </form>
            <form action="/admin/delete_trabajador_propio" method="post">
                <input type="hidden" name="clienteId" value="<%=cliente.getId()%>">
                <% if (trabajador_propio != null ) { %> <input type="hidden" name="trabajadorId" value="<%=trabajador_propio.getId()%>"> <% } %>
                <button class="swap-button" type="submit" <%= trabajador_propio != null ? "" : "disabled" %>> ➡️ </button>
            </form>
        </div>
        <div class="trabajador-table">
        <table class="table caption-top text-center">
            <caption class="text-center text-white">Lista de trabajadores sin asignar</caption>
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Trabajador</th>
                <th scope="col">Rol</th>
            </tr>
            </thead>
            <tbody>
            <% for(Usuario c : todos_trabajadores){ %>
            <tr role="button" class="<%= trabajador_nuevo!=null && c.getId() == trabajador_nuevo.getId() ? "selected-row" : ""%>" onclick="seleccionarEntrenadorNuevo(<%=cliente.getId()%>,<%=c.getId()%>)">
                <td><%=c.getId()%></td>
                <td><%=c.getNombre()%></td>
                <td><%=c.getRol().getNombre()%></td>
            </tr>
            <% } %>
            </tbody>
        </table>
        </div>
    </div>
    <% } %>
</div>
</body>
</html>