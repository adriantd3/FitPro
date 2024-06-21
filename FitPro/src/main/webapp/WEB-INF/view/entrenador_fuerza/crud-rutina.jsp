<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.entity.Rutina" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutinas");
    List<RutinaDTO> rutinasPrincipales;
    UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
    UsuarioDTO entrenador = (UsuarioDTO) session.getAttribute("user");
    if(cliente != null) {
        rutinasPrincipales = (List<RutinaDTO>) session.getAttribute("rutinasCliente");
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
         onclick="window.location.href='/entrenador_fuerza/<%=(cliente != null ? "clientes" : "")%>' ">
    <h1 class="header-text text-center">Rutinas de entrenamiento <%if(cliente != null){%><%="de "+cliente.getNombre()%><%}%></h1> <!-- Controlar si es de un usario para añadir "de usuario" y solo sus listas -->
</header>
<section class="mt-3 ms-3 h-100">
    <div class="d-flex justify-content-between">
        <form method="post" action="/entrenador_fuerza/crear-rutina" class="p-2" style="border: 1px solid white; border-radius: 10px">
            <input type="text" name="nombreRutina" placeholder="Nombre de la rutina nueva..."/>
            <button type="submit" class=" btn btn-primary top-50"
                    onclick="window.location.href='/entrenador_fuerza/rutina?rutina='"
            >Añadir Rutina
            </button>
        </form>
        <form:form cssStyle="border: 1px solid white; border-radius: 10px" cssClass="me-2 p-2"
                   method="post" action="/entrenador_fuerza/crud-rutina/filtro" modelAttribute="filtroRutina">
            <form:label cssStyle="color: white" path="nombre">Nombre: </form:label>
            <form:input cssClass="me-3" path="nombre"/>

            <form:label cssStyle="color: white" path="fechaCreacion">Fecha: </form:label>
            <form:input cssClass="me-3" path="fechaCreacion"/>

            <form:label cssStyle="color: white" path="numeroSesiones">Sesiones: </form:label>
            <form:select path="numeroSesiones">
                <form:options items="${[0,1,2,3,4,5]}"/>
            </form:select>

            <input type="hidden" name="cliente" value="<%=cliente != null ? cliente.getId() : -1%>">

            <input type="submit" value="Filtrar" class="btn btn-info">
        </form:form>
    </div>

    <% if(cliente != null){ %><h1 style="color: white">Rutinas asignadas</h1> <%}%>
    <ul class="list-group m-3">
        <%
            for(RutinaDTO rutina : rutinasPrincipales){
        %>
        <a href="/entrenador_fuerza/rutina?rutina=<%=rutina.getId()%>" class="list-button list-group-item">
            <%=rutina.getNombre()+ " " + rutina.getFechaCreacion()%>
        </a>
        <%
            }
        %>
    </ul>

    <% if(cliente != null){ %>
    <h1 style="color: white">Rutinas no asignadas</h1>
        <ul class="list-group m-3">
            <%
                for(RutinaDTO rutina : rutinas){
                    if(rutina.getEntrenador().getId().equals(entrenador.getId())){
            %>
            <a href="/entrenador_fuerza/guardar-rutina?rutina=<%=rutina.getId()%>" class="list-button list-group-item">
                <%=rutina.getNombre()+ " " + rutina.getFechaCreacion()%>
            </a>
            <%
                    }
                }
            %>
        </ul>
    <%}%>
</section>
<% if(cliente != null){ %>
    <footer class="fixed-bottom p-3">
        <button class="btn btn-primary" onclick="window.location.href='/entrenador_fuerza/seguimiento'">
            Seguimiento
        </button>
    </footer>
<% } %>
</body>
</html>
