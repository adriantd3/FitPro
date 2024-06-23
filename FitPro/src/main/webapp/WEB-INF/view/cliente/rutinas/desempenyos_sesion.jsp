<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.DesempenyoSesionDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @Author Adrian Torremocha Doblas - 100%
     */
%>
<!doctype html>
<%
    List<DesempenyoSesionDTO> desempenyoSesions = (List<DesempenyoSesionDTO>) request.getAttribute("desempenyos");
    SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");

    String rutaBack = "/cliente/rutinas";
    if(sesion.getRutinaId() != null){
        rutaBack = "sesiones_rutina?id=" + sesion.getRutinaId();
    }

%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - DesempeñosSesion</title>
</head>
<body>
<header>
    <a href="<%=rutaBack%>">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Desempeños Sesión - <%=sesion.getNombre()%></h1>
</header>
<div class="d-flex justify-content-center mt-4">
    <div>
        <%if(desempenyoSesions.isEmpty()){%>
        <h2 class="text-light">No hay desempeños registrados</h2>
        <%}%>
        <ul class="text-light">
            <%
                int countNT = 0;
                int countT = 0;
                for (DesempenyoSesionDTO desempenyoSesion : desempenyoSesions){
                    String terminado;
                    if(desempenyoSesion.isTerminado()){
                        terminado = "Terminado";
                        countT++;
                    }else{
                        terminado = "No terminado";
                        countNT++;
                    }
                    String idRes = "Ent" + (desempenyoSesion.isTerminado() ? "T" + countT: "NT" + countNT);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fecha = desempenyoSesion.getFecha().format(formatter);
            %>
            <li>
                <a class="text-primary fs-5" href="info_desempenyo_sesion?id=<%=desempenyoSesion.getId()%>" id="<%=idRes%>">
                    <%=fecha%> - <%=terminado%>
                </a>
            </li>
            <%
                }
            %>

        </ul>
        <form method="post" action="prev_desempenyo" class="text-center">
            <button type="submit" class="btn btn-primary ms-2" name="nuevo_entrenamiento">Nuevo entrenamiento</button>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>