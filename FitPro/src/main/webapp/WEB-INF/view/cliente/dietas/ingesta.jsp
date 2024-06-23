<%//AUTOR: Adrián Torremocha(100%)%>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.DesempenyoMenuDTO" %>
<%@ page import="uma.fitpro.dto.DesempenyoComidaDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DesempenyoMenuDTO desempenyoMenu = (DesempenyoMenuDTO) request.getAttribute("desempenyo_menu");
    List<DesempenyoComidaDTO> des_comidas = (List<DesempenyoComidaDTO>) request.getAttribute("des_comidas");
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - DesempeñoComida</title>
</head>
<body>
<header>
    <a href="desempenyos_menu?id=<%=desempenyoMenu.getMenuId()%>" >
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    </a>
    <h1 class="header-text text-center">Ingesta en curso : <%=desempenyoMenu.getNombreMenu()%>
    </h1>
</header>
<section id="table-container" class="d-flex justify-content-center mt-3">
    <div style="width: 35%">
        <table class="table table-striped text-center" id="tabla_comidas">
            <thead class="table-dark">
            <tr>
                <th scope="col">Plato</th>
                <th scope="col">Nombre</th>
                <th scope="col">Calorías</th>
                <th scope="col">Terminado</th>
                <th scope="col">Gustado</th>
            </tr>
            </thead>
            <tbody>
            <%
                int numSerie = 1;
                for (DesempenyoComidaDTO desComida : des_comidas) {
            %>
            <tr style="">
                <th scope="row"><%=numSerie%></th>
                <td><%=desComida.getComida().getNombre()%></td>
                <td><%=desComida.getComida().getCalorias()%></td>
                <td><%=desComida.isComido() ? "✔" : "❌"%></td>
                <td><%=desComida.isGustado() ? "✔" : "❌"%></td>
                <td style="box-shadow: none;background-color: #434343;border-bottom-width: 0px;">
                    <form action="editar_des_comida" method="post" style="height: 8px">
                        <input type="hidden" name="id" value="<%=desComida.getId()%>">
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
        <section/>
        <div class="d-flex" id="buttons">
            <form action="terminar_ingesta" method="post" style="margin-right: 2rem">
                <input type="hidden" name="id" value="<%=desempenyoMenu.getId()%>">
                <button type="submit" class="btn btn-success" name="terminar">Terminar ingesta</button>
            </form>
            <form action="cancelar_ingesta" method="post">
                <input type="hidden" name="id" value="<%=desempenyoMenu.getId()%>">
                <button type="submit" class="btn btn-danger" name="cancelar">Cancelar ingesta</button>
            </form>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>