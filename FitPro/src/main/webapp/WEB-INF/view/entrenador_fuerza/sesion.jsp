<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    RutinaDTO rutina = (RutinaDTO) session.getAttribute("rutina");
    SesionDTO sesion = (SesionDTO) request.getAttribute("sesion");
    HashMap<EjercicioDTO, List<SerieDTO>> tablas = (HashMap<EjercicioDTO, List<SerieDTO>>) request.getAttribute("tablas");
    SerieDTO serie = (SerieDTO) request.getAttribute("serie");
    String nombreEjercicio = (String) request.getAttribute("nombreEjercicio");
%>
<html>
<head>
    <title>Sesion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
         onclick="window.location.href='/entrenador_fuerza/rutina?rutina=<%=rutina.getId()%>'"> <!-- Controlar pagina anterior por modelo -->
    <h1 class="header-text text-center"><%=sesion.getNombre()%></h1> <!-- Mostrar informacion relevante en la X-->
</header>

<section class="mt-3 ms-3 h-100 d-flex">
    <div class="w-50">
    <%
        for (EjercicioDTO ejercicio : tablas.keySet()){
    %>

        <table style="border-spacing: 0" class="table caption-top text-center w-100 ">
            <a href="/cliente/rutinas/ejercicio?id=<%=ejercicio.getId()%>" class="d-block fs-3"><%=ejercicio.getNombre()%></a>
            <thead class="table-dark">
            <tr>
                <th class="nombre-menu">Peso</th>
                <th class="kcal">Repeticiones</th>
                <th style="background-color: transparent;border-bottom-width: 0"></th>
                <th style="background-color: transparent;border-bottom-width: 0"></th>
            </tr>
            </thead>
            <tbody style="border-top: 0 !important;" class = "table-group-divider table-secondary">
            <%
                for(SerieDTO s : tablas.get(ejercicio)){
            %>

            <tr onclick="">
                <td><%= s.getMetrica1()%></td>
                <td><%= s.getMetrica2()%></td>
                <td style="background-color: transparent !important; border-bottom-width: 0">
                    <a style="color: yellow" href="/entrenador_fuerza/editar-serie?serie=<%=s.getId()%>">Editar Serie</a>
                </td>
                <td style="background-color: transparent !important; border-bottom-width: 0">
                    <a style="color: red" href="/entrenador_fuerza/eliminar-serie?serie=<%=s.getId()%>">Eliminar Serie</a>
                </td>
            </tr>

            <%
                }
            %>
            </tbody>
        </table>

        <button class="btn btn-primary mb-5"
                onclick="window.location.href=
                    '/entrenador_fuerza/anyadir-serie?sesion=<%=sesion.getId()%>&ejercicio=<%=ejercicio.getId()%>'">
            Añadir Serie
        </button>
        <%
            }
        %>
    </div>
    <%
        if(serie != null){
    %>
    <div class="w-50 align-items-center d-flex flex-column">
        <h1 class="pb-2" style="color: white"> Serie de <%=nombreEjercicio%></h1>
        <form:form method="post" action="/entrenador_fuerza/guardar-serie" modelAttribute="serie">
            <form:label cssStyle="color: white; width: 110px" path="metrica1" >Peso: </form:label>
            <form:input path="metrica1" type="number"/>
            <br>
            <form:label cssStyle="color: white; width: 110px" path="metrica2" >Repeticiones: </form:label>
            <form:input path="metrica2" type="number"/>
            <br>

            <form:input type="hidden" path="id" value="<%=serie.getId()%>"/>
            <form:input type="hidden" path="sesion" value="<%=serie.getSesion()%>"/>
            <form:input type="hidden" path="ejercicio" value="<%=serie.getEjercicio()%>"/>

            <input class="btn btn-success" type="submit" value="Guardar">
        </form:form>
    </div>
    <%
        }
    %>
</section>

<footer class="m-3 fixed-bottom">
    <button class="btn btn-danger" onclick="window.location.href='/entrenador_fuerza/borrar-sesion?sesion=<%=sesion.getId()%>'">
        Borrar
    </button>
    <button class="btn btn-primary" onclick="window.location.href='/entrenador_fuerza/asignar-ejercicio?sesion=<%=sesion.getId()%>'">
        Añadir Ejercicio
    </button>
</footer>

</body>
</html>
