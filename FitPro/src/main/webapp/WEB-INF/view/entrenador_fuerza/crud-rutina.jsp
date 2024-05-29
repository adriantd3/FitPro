<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 12/4/24
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
    List<Rutina> rutinasPrincipales;
    Usuario cliente = (Usuario) session.getAttribute("cliente");
    if(cliente != null) {
        rutinasPrincipales = new ArrayList<Rutina>(cliente.getRutinasCliente());
    }else{
        rutinasPrincipales = rutinas;
    }
%>
<html>
<head>
    <title>Lista de rutinas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/home'">
    <h1 class="header-text text-center">Rutinas de entrenamiento <%if(cliente != null){%><%="de "+cliente.getNombre()%><%}%></h1> <!-- Controlar si es de un usario para añadir "de usuario" y solo sus listas -->
</header>
<section class="mt-3 ms-3 h-100">
    <form method="post" action="/entrenador_fuerza/crear-rutina">
        <input type="text" name="nombreRutina" placeholder="Nombre de la rutina nueva..."/>
        <button type="submit" name="crearRutinaButton" class=" btn btn-primary top-50"
        >Añadir Rutina
        </button>
    </form>

    <% if(cliente != null){ %><h1 style="color: white">Rutinas asignadas</h1> <%}%>
    <ul class="list-group m-3">
        <%
            for(Rutina rutina : rutinasPrincipales){
        %>
        <button name=<%=rutina.getNombre()%> onclick="window.location.href='/entrenador_fuerza/rutina?rutina=<%=rutina.getId()%>'" class="list-button list-group-item">
            <%=rutina.getNombre()+ " " + rutina.getFechaCreacion()%>
        </button>
        <%
            }
        %>
    </ul>

    <% if(cliente != null){ %>
    <h1 style="color: white">Rutinas no asignadas</h1>
        <ul class="list-group m-3">
            <%
                for(Rutina rutina : rutinas){
            %>
            <button onclick="window.location.href='/entrenador_fuerza/guardar-rutina?rutina=<%=rutina.getId()%>'" class="list-button list-group-item">
                <%=rutina.getNombre()+ " " + rutina.getFechaCreacion()%>
            </button>
            <%
                }
            %>
        </ul>
    <%}%>
</section>
</body>
</html>
