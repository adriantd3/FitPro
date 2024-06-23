<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.DesempenyoSesionDTO" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.SerieDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uma.fitpro.dto.DesempenyoSerieDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DesempenyoSesionDTO desempenyoSesion = (DesempenyoSesionDTO) request.getAttribute("desempenyoSesion");
    Map<EjercicioDTO, List<DesempenyoSerieDTO>> tablas = (Map<EjercicioDTO, List<DesempenyoSerieDTO>>) request.getAttribute("tablas");
    Map<EjercicioDTO, List<SerieDTO>> tablasEsperadas = (Map<EjercicioDTO, List<SerieDTO>>) request.getAttribute("tablasEsperadas");
%>
<html>
<head>
    <title>Desempeño en <%=desempenyoSesion.getNombreSesion()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
<header class="sticky-top">
<img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
     onclick="window.location.href='/entrenador_fuerza/seguimiento'"> <!-- Controlar pagina anterior por modelo -->
<h1 class="header-text text-center">Desempeño en <%=desempenyoSesion.getNombreSesion()%></h1> <!-- Mostrar informacion relevante en la X-->
</header>

<section class="m-3 h-100 d-flex flex-column align-items-center">
    <%
        for(EjercicioDTO ejercicio : tablasEsperadas.keySet()){
    %>
    <table style="border-spacing: 0; border-collapse: collapse" class="table caption-top text-center w-50 ">
        <a href="/entrenador_fuerza/ejercicio/<%=ejercicio.getId()%>%>" class="d-block fs-3" target="_blank"><%=ejercicio.getNombre()%></a>
        <thead class="table-dark">
            <tr>
                <th class="nombre-menu">Peso</th>
                <th class="kcal">Repeticiones</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<SerieDTO> seriesEsperadas = tablasEsperadas.get(ejercicio);
            List<DesempenyoSerieDTO> seriesDesmpenyo = tablas.get(ejercicio) == null ? new ArrayList<>() : tablas.get(ejercicio);
            System.out.println("ESPERADO: " + seriesEsperadas);
            System.out.println("DESEMPEÑO: "+seriesDesmpenyo);
            for(int i = 0; i < seriesEsperadas.size(); i++){
                if(i < seriesDesmpenyo.size()) {
                    DesempenyoSerieDTO serie = seriesDesmpenyo.get(i);
                    SerieDTO serieEsperada = seriesEsperadas.get(i);
                    String color = "#77de77";
                    System.out.println(serie.getMetrica1() + " - " + serie.getMetrica2() + " vs " + serieEsperada.getMetrica1() + " - " + serieEsperada.getMetrica2());
                    if(serie.getMetrica1() < serieEsperada.getMetrica1() || serie.getMetrica2() < serieEsperada.getMetrica2()){
                        System.out.println("cambia color");
                        color = "#e27070";
                    }
        %>
        <tr>
            <td style="background-color: <%=color%>"><%=serie.getMetrica1()%></td>
            <td style="background-color: <%=color%>"><%=serie.getMetrica2()%></td>
        </tr>
        <%
            }else{
                    SerieDTO serie = seriesEsperadas.get(i);
        %>
        <tr>
            <td style="background-color: #888888" class="fst-italic"><%=serie.getMetrica1()%></td>
            <td style="background-color: #888888" class="fst-italic">No realizado</td>
        </tr>

        <%
                }
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</section>
</body>
</html>
