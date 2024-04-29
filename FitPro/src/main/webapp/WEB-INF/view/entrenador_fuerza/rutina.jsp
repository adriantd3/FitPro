<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Usuario cliente = (Usuario) session.getAttribute("cliente");
    Rutina rutina = (Rutina) request.getAttribute("rutina");
    List<Sesion> sesionesRutina = (List<Sesion>) request.getAttribute("sesiones");
%>
<html>
<head>
    <title>Rutina</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/rutina?=<%if(cliente != null){%><%=cliente.getId()%><%}%>'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center"><%=rutina.getNombre()%></h1> <!-- Controlar nombre rutina  -->
</header>
<section class="mt-3 ms-3 h-100">
    <button class=" btn btn-primary top-50"
            onclick="window.location.href='/entrenador_fuerza/crear-sesion?rutina=<%=rutina.getId()%>'">Añadir Sesion
    </button>
    <ul class="list-group m-3">
            <%
            for(Sesion sesion : sesionesRutina){
        %>
        <button onclick="window.location.href='/entrenador_fuerza/crear-sesion?rutina=<%=rutina.getId()%>&sesion=<%=sesion.getId()%>'" class="list-button list-group-item">
            <%=sesion.getNombre()%>
        </button>
            <%
            }
        %>
</section>
</body>
</html>
