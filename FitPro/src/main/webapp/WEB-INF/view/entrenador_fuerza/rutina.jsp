<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
    RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
    List<SesionDTO> sesionesRutina = (List<SesionDTO>) request.getAttribute("sesiones");
    List<SesionDTO> sesionesTotales = (List<SesionDTO>) request.getAttribute("sesionesTotales");
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
         onclick="window.location.href='/entrenador_fuerza/crud-rutina'"> <!-- Controlar pagina anterior por modelo -->
        <h1 class="header-text text-center"><%=(rutina != null ? rutina.getNombre() : "Crear rutina")%></h1> <!-- Controlar nombre rutina  -->
</header>
<section class="mt-3 ms-3 h-100">
    <form method="post" action="/entrenador_fuerza/crear-sesion">
        <input type="text" name="nombreSesion" placeholder="Nombre de la sesión nueva..."/>
        <button type="submit" class=" btn btn-primary top-50"
        >Añadir sesión
        </button>
    </form>
    <h1 style="color: white">Sesiones asignadas</h1>
    <ul class="list-group m-3">
            <%
            for(SesionDTO sesion : sesionesRutina){
        %>
        <button onclick="window.location.href='/entrenador_fuerza/sesion?sesion=<%=sesion.getId()%>'" class="list-button list-group-item">
            <%=sesion.getNombre() + " | " + UtilityFunctions.getDayByNumber(sesion.getId())%>
        </button>
            <%
            }
        %>
    </ul>
    <h1 style="color: white">Sesiones no asignadas</h1>
    <ul class="list-group m-3">
        <%
            for(SesionDTO sesion : sesionesTotales){
                if(!sesionesRutina.contains(sesion)){
        %>
        <button onclick="window.location.href='/entrenador_fuerza/asignar-sesion?sesion=<%=sesion.getId()%>'" class="list-button list-group-item">
            <%=sesion.getNombre()%>
        </button>
        <%
                }
            }
        %>

    </ul>
</section>
<footer class="m-3 fixed-bottom">
    <button class="btn btn-danger" onclick="window.location.href='/entrenador_fuerza/borrar-rutina?rutina=<%=rutina.getId()%>'">
        Borrar
    </button>
    <%
        if(cliente != null){
    %>
    <button class="btn btn-warning" onclick="window.location.href='/entrenador_fuerza/desasignar?rutina=<%=rutina.getId()%>'">
        Desasignar a <%=cliente.getNombre() + " " + cliente.getApellidos()%>
    </button>
    <%
        }
    %>
</footer>
</body>
</html>
