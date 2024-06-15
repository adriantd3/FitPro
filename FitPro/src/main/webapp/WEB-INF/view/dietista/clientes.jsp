<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 24/04/2024
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
%>

<html>
<head>
    <title>Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./menus.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Clientes</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Menus" class="menu-table pt-2">
                <table class="table caption-top text-center ">
                    <caption class="text-center text-white">List of users</caption>
                    <thead class="table-dark">
                    <tr>
                        <th class="id">º\</th>
                        <th class="nombre-menu">Nombre</th>
                        <th class="kcal">Apellidos</th>
                    </tr>
                    </thead>
                    <tbody class = "table-group-divider table-secondary">
                    <%
                        for(Usuario cliente : clientes){
                    %>

                    <tr>
                        <td><%= cliente.getId() %></td>
                        <td><%= cliente.getNombre() %></td>
                        <td><%= cliente.getApellidos() %></td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </section>
        </div>


    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
