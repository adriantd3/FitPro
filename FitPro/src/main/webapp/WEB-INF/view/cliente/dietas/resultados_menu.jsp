<%@ page import="uma.fitpro.dto.DesempenyoMenuDTO" %>
<%@ page import="uma.fitpro.dto.DesempenyoComidaDTO" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    DesempenyoMenuDTO desempenyoMenu = (DesempenyoMenuDTO) request.getAttribute("desempenyo_menu");
    List<DesempenyoComidaDTO> des_comidas = (List<DesempenyoComidaDTO>) request.getAttribute("des_comidas");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fecha = desempenyoMenu.getFechaCreacion().format(formatter);
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../../styles/common.css"%>
    </style>
    <title>Cliente - Resultados Menú</title>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">
        <%=desempenyoMenu.getNombreMenu()%> - Resultados del <%=fecha%>
    </h1>
</header>
<div class="text-center text-light pt-2">
    <form:form method="post" action="filtro_menu" modelAttribute="filtro">
        <form:hidden path="desMenuId"/>

        Nombre <form:input path="nombre"/>
        Calorías > <form:input path="calorias" size="5" maxlength="5" type="number"/>
        Terminado <form:select path="comido">
                    <form:option value="${null}" label="Ambos"/>
                    <form:option value="${true}" label="Sí"/>
                    <form:option value="${false}" label="No"/>
                </form:select>
        Gustado <form:select path="gustado">
                    <form:option value="${null}" label="Ambos"/>
                    <form:option value="${true}" label="Sí"/>
                    <form:option value="${false}" label="No"/>
                </form:select>
        <br>
        <form:button>Filtrar</form:button>
    </form:form>
</div>
<section id="table-container">
    <h4 class="text-center text-light mt-5">Resultado obtenido</h4>
    <div class="d-flex justify-content-center p-3">
        <%
            if(!des_comidas.isEmpty()){

        %>
        <table class="table table-striped text-center w-50" id="tabla_comidas">
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
                <td><%=desComida.isComido() ? "Sí" : "No"%></td>
                <td><%=desComida.isGustado() ? "Sí" : "No"%></td>
            </tr>
            <%
                    numSerie++;
                }
            %>
            </tbody>
        </table>
        <%
            } else {
        %>
            <h4 class="text-center text-light">No hay resultados</h4>
        <%
            }
        %>
    </div>
</section>
</body>
</html>
