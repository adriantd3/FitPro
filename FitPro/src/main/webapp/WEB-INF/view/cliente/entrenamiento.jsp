<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Map<Ejercicio, List<DesempenyoSerie>> sesion_dict = (Map<Ejercicio, List<DesempenyoSerie>>) request.getAttribute("series_dict");
    Map<Integer, List<String>> params = UtilityFunctions.getEjercicioParametros();
    Sesion sesion = (Sesion) session.getAttribute("sesion");
    Integer desempenyoSesionId = (Integer) request.getAttribute("desempenyo_sesion_id");
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Cliente - DesempeñoSesión</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Entrenamiento en curso : <%=sesion.getNombre()%>
    </h1>
</header>
<section id="table-container">
    <div class="p-3" style="width: 70%">
        <%
            int countEjercicio = 0;
            for (Ejercicio ejercicio : sesion_dict.keySet()) {
                countEjercicio++;
                int tipo = ejercicio.getTipo().getId();
                List<String> cols = params.get(tipo);
        %>
        <section class="ejercicio<%=countEjercicio%>">
            <a href="/cliente/ejercicio?id=<%=ejercicio.getId()%>"
               class="text-primary fs-4 ejercicio"><%=ejercicio.getNombre()%>
            </a>
            <table class="table table-striped text-center" id="TableEjer<%=ejercicio.getId()%>">
                <thead class="table-dark">
                <tr>
                    <th scope="col">Serie</th>
                    <th scope="col"><%=cols.get(0)%>
                    </th>
                    <th scope="col"><%=cols.get(1)%>
                    </th>
                </tr>
                </thead>
                <tbody>
                <%
                    int numSerie = 1;
                    for (DesempenyoSerie serie : sesion_dict.get(ejercicio)) {
                %>
                <tr style="">
                    <th scope="row"><%=numSerie%>
                    </th>
                    <td><%=serie.getMetrica1()%></td>
                    <td><%=serie.getMetrica2()%></td>
                    <td style="box-shadow: none;background-color: #434343;border-bottom-width: 0px;">
                        <form action="/cliente/borrar_serie" method="post" style="height: 8px;"
                              id="BorrarSerie<%=serie.getId()%>">
                            <input type="hidden" name="id" value="<%=serie.getId()%>">
                            <input type="hidden" name="desempenyo_sesion_id" value="<%=desempenyoSesionId%>">
                            <button type="submit" class="btn btn-link" name="borrar">Borrar</button>
                        </form>
                    </td>
                    <td style="box-shadow: none;background-color: #434343;border-bottom-width: 0px;">
                        <form action="/cliente/editar_serie" method="post" style="height: 8px"
                              id="EditarSerie<%=serie.getId()%>">
                            <input type="hidden" name="id" value="<%=serie.getId()%>">
                            <button type="submit" class="btn btn-link" name="editar">Editar</button>
                        </form>
                    </td>
                </tr>
                <%
                        numSerie++;
                    }
                %>
                </tbody>
            </table>
            <div class="text-center">
                <form action="/cliente/nueva_serie" method="post" id="NuevaSerieEjer<%=ejercicio.getId()%>">
                    <input type="hidden" name="desempenyo_sesion_id" value="<%=desempenyoSesionId%>">
                    <input type="hidden" name="ejercicio_id" value="<%=ejercicio.getId()%>">
                    <button type="submit" class="btn btn-link" name="nueva_serie">Añadir nueva serie</button>
                </form>
            </div>
        </section>
        <%
            }
        %>
        <section/>
        <div class="d-flex" id="buttons">
            <form action="/cliente/terminar_entrenamiento" method="post" style="margin-right: 2rem">
                <input type="hidden" name="desempenyo_sesion_id" value="<%=desempenyoSesionId%>">
                <button type="submit" class="btn btn-success" name="terminar">Terminar entrenamiento</button>
            </form>
            <form action="/cliente/cancelar_entrenamiento" method="post">
                <input type="hidden" name="desempenyo_sesion_id" value="<%=desempenyoSesionId%>">
                <button type="submit" class="btn btn-danger" name="cancelar">Cancelar entrenamiento</button>
            </form>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>