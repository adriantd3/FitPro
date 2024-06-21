<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioDTO cliente = (UsuarioDTO) session.getAttribute("cliente");
    RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
    Map<Integer, SesionDTO> sesionesRutina = (Map<Integer, SesionDTO>) request.getAttribute("sesiones");
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
         onclick="window.location.href='/entrenador_fuerza/crud-rutina<%=(cliente != null ? "?cliente="+cliente.getId() : "")%>'"> <!-- Controlar pagina anterior por modelo -->
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
            for(Map.Entry<Integer, SesionDTO> diaSesion : sesionesRutina.entrySet()){
        %>
        <button onclick="window.location.href='/entrenador_fuerza/sesion?sesion=<%=diaSesion.getValue().getId()%>'" class="list-button list-group-item">
            <%=diaSesion.getValue().getNombre() + " | " + UtilityFunctions.getDayByNumber(diaSesion.getKey())%>
        </button>
            <%
            }
        %>
    </ul>
    <h1 style="color: white">Sesiones no asignadas</h1>
    <ul class="list-group m-3">
        <%
            for(SesionDTO sesion : sesionesTotales){
                if(!sesionesRutina.containsValue(sesion)){
        %>
        <button onclick="seleccionarNuevaSesion(<%=sesion.getId()%>)" class="list-button list-group-item" data-bs-toggle="modal" data-bs-target="#modal">
            <%=sesion.getNombre()%>
        </button>
        <%
                }
            }
        %>
    </ul>

    <div class="modal fade" id="modal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div style="background-color: #494949" class="modal-content" data-bs-theme="dark">
                <div class="modal-header">
                    <h1 style="color: white;" class="modal-title fs-5" id="exampleModalLabel">Asignar dia:</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form method="post" action="/entrenador_fuerza/asignar-sesion">
                    <div class="modal-body">

                            <input type="hidden" name="sesionNueva" id="sesionNueva" value="">
                            <%
                                for(int i = 1; i <= 7; i++){
                            %>
                            <input type="radio" class="btn-check" name="dia" id="dia<%=i%>" autocomplete="off" value="<%=i%>">
                            <label class="btn btn-outline-info mb-1" for="dia<%=i%>"><%=UtilityFunctions.getDayByNumber(i)%></label>
                            <%
                                }
                            %>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<footer class="m-3 fixed-bottom">
    <button class="btn btn-danger" onclick="window.location.href='/entrenador_fuerza/borrar-rutina?rutina=<%=rutina.getId()%>'">
        Borrar
    </button>
    <%
        if(cliente != null){
    %>
    <button class="btn btn-warning" onclick="window.location.href='/entrenador_fuerza/desasignar-rutina?rutina=<%=rutina.getId()%>'">
        Desasignar a <%=cliente.getNombre() + " " + cliente.getApellidos()%>
    </button>
    <%
        }
    %>
</footer>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    function seleccionarNuevaSesion(idSesion) {
        const input = document.getElementById("sesionNueva");
       // console.log(input.value);
        input.value = idSesion;

        console.log(input.value);
    }
</script>
</html>
