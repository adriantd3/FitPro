<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.ComidaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ComidaDTO> comidas = (List<ComidaDTO>) request.getAttribute("menu_comidas");
%>
<html>
<body>
<section id="table-container">
    <table class="table table-striped text-center">
        <thead class="table-dark">
        <tr>
            <th scope="col">Plato</th>
            <th scope="col">Nombre</th>
            <th scope="col">Calorías</th>
        </tr>
        </thead>
        <tbody>
        <%
            int numSerie = 1;
            for (ComidaDTO comida : comidas) {
        %>
        <tr style="">
            <th scope="row"><%=numSerie%></th>
            <td><%=comida.getNombre()%></td>
            <td><%=comida.getCalorias()%></td>
        </tr>
        <%
                numSerie++;
            }
        %>
        </tbody>
    </table>
    <% if(comidas.isEmpty()){ %>
    <h4 class="text-center text-light">No hay resultados</h4>
    <% } %>
</section>
</body>
</html>
