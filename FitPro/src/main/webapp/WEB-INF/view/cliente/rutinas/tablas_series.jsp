<%@ page import="uma.fitpro.dto.SerieInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String option = request.getParameter("dict");
    Map<EjercicioDTO, List<SerieInterface>> dict =
            (Map<EjercicioDTO, List<SerieInterface>>) request.getAttribute(option + "_dict");
    Map<Integer,List<String>> params = UtilityFunctions.getEjercicioParametros();
%>
<html>
<body>
<section id="table-container">
        <%
            for (EjercicioDTO ejercicio : dict.keySet()) {
                int tipo = ejercicio.getTipo().getId();
                List<String> cols = params.get(tipo);
        %>

        <a href="ejercicio?id=<%=ejercicio.getId()%>" class="text-primary fs-4"><%=ejercicio.getNombre()%></a>
        <table class="table table-striped text-center">
            <thead class="table-dark">
            <tr>
                <th scope="col">Serie</th>
                <th scope="col"><%=cols.get(0)%></th>
                <th scope="col"><%=cols.get(1)%></th>
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
