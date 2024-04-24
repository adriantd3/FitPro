<%@ page import="uma.fitpro.entity.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 24/04/2024
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario user = (Usuario) session.getAttribute("user");
%>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./home.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1" src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Bienvenido, <%= user.getNombre() %></h1>
</header>

<div id="menu">
    <form method="post" action="/dietista/menus">
        <button type="submit" class="btn  btn-secondary">Menús</button><br/>
    </form>
    <form method="post" action="/dietista/menus">
        <button type="submit" class="btn  btn-secondary">Dietas</button><br/>
    </form>
    <form method="post" action="/dietista/clientes">
        <button type="submit" class="btn  btn-secondary">Clientes</button>
    </form>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
