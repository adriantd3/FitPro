<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="uma.fitpro.dto.SerieInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * @Author Adrian Torremocha Doblas - 100%
     */
%>
<%
    String option = request.getParameter("dict");
    Map<EjercicioDTO, List<SerieInterface>> dict =
            (Map<EjercicioDTO, List<SerieInterface>>) request.getAttribute(option + "_dict");
%>
<html>
<body>
<section id="table-container">
        <%
            for (EjercicioDTO ejercicio : dict.keySet()) {
        %>

        <a href="ejercicio?id=<%=ejercicio.getId()%>" class="text-primary fs-4" target="_blank"><%=ejercicio.getNombre()%></a>
        <table class="table table-striped text-center">
            <thead class="table-dark">
            <tr>
                <th scope="col">Serie</th>
                <th scope="col"><%=ejercicio.getTipo().getMetrica1()%></th>
                <th scope="col"><%=ejercicio.getTipo().getMetrica2()%></th>
            </tr>
            </thead>
            <tbody>
            <%
                int numSerie = 1;
                for (SerieInterface serie : dict.get(ejercicio)) {
            %>
            <tr style="">
                <th scope="row"><%=numSerie%></th>
                <td><%=serie.getMetrica1()%></td>
                <td><%=serie.getMetrica2()%></td>
            </tr>
            <%
                    numSerie++;
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>
        <% if(dict.isEmpty()){ %>
        <h4 class="text-center text-light">No hay resultados</h4>
        <% } %>
</section>
</body>
</html>
