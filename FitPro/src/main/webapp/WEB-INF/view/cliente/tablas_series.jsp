<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="uma.fitpro.entity.SerieInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String option = request.getParameter("dict");
    Map<Ejercicio, List<SerieInterface>> dict =
            (Map<Ejercicio, List<SerieInterface>>) request.getAttribute(option + "_dict");
    Map<Integer,List<String>> params = UtilityFunctions.getEjercicioParametros();
%>
<html>
<body>
<section id="table-container">
    <div>
        <%
            for (Ejercicio ejercicio : dict.keySet()) {
                int tipo = ejercicio.getTipo().getId();
                List<String> cols = params.get(tipo);
        %>

        <a href="cliente/ejercicio?id=<%=ejercicio.getId()%>" class="text-primary fs-4"><%=ejercicio.getNombre()%></a>
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
                <%
                    if(tipo == 1){
                %>
                <td><%=serie.getPeso()%></td>
                <td><%=serie.getRepeticiones()%></td>
                <%
                }else if(tipo == 2){
                %>
                <td><%=serie.getDistancia()%></td>
                <td><%=serie.getDuracion()%></td>
                <%
                }else if(tipo == 3){
                %>
                <td><%=serie.getDuracion()%></td>
                <td><%=serie.getDescanso()%></td>
                <%
                }else if(tipo == 4 || tipo == 5){
                %>
                <td><%=serie.getRepeticiones()%></td>
                <td><%=serie.getDescanso()%></td>
                <%
                    }
                %>
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
</section>
</body>
</html>
