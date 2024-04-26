<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Set<Usuario> clientes = (Set<Usuario>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/home'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center">Clientes</h1> <!-- Meter usuario por modelo -->
</header>

<ul class="list-group">

    <%
        for(Usuario cliente : clientes){
    %>
    <button type="button" onclick="window.location.href='/entrenador_fuerza/crud-rutina?cliente=<%=cliente.getId()%>'" class="list-button list-group-item m-3">
        <%=cliente.getNombre()+ " " + cliente.getApellidos()%>
    </button>
    <%
        }
    %>
</ul>
</body>
</html>
