<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.dto.*" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.ui.FiltroCliente" %>
<%@ page import="uma.fitpro.ui.FiltroDieta" %>
<%@ page import="uma.fitpro.utils.UtilityFunctions" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 22/05/2024
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UsuarioDTO> clientes = (List<UsuarioDTO>) request.getAttribute("clientes");
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("cliente");
    List<DietaDTO> dietas = (List<DietaDTO>) request.getAttribute("dietas");
    List<DietaDTO> dietasCliente = (List<DietaDTO>) request.getAttribute("dietasCliente");
    DietaDTO dieta = (DietaDTO) request.getAttribute("dieta");
    List<OrdenMenuDietaDTO> menusDieta = (List<OrdenMenuDietaDTO>) request.getAttribute("menusDieta");
    FiltroCliente filtroCliente = (FiltroCliente) request.getAttribute("filtroCliente");
    FiltroDieta filtroDieta = (FiltroDieta) request.getAttribute("filtroDieta");
    for(DietaDTO d : dietasCliente) {
        dietas.remove(d);
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
%>

<html>
<head>
    <title>AsignarDietasCliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./clientes.css"%></style>
    <script>
        function selectCliente(clienteId){
            document.getElementById("clienteSelector").value=clienteId;
            document.getElementById("formSelectCliente").submit();
        }
        function  selectDietaFromCliente(dietaId){
            document.getElementById("dietaFromClienteSelector").value=dietaId;
            document.getElementById("formSelectDietaFromCliente").submit();
        }

        function  selectDieta(dietaId){
            document.getElementById("dietaSelector").value=dietaId;
            document.getElementById("formSelectDieta").submit();
        }

        function addDieta(dietaId){
            document.getElementById("addDietaSelector").value=dietaId;
            document.getElementById("formAddDieta").submit();
        }

        function deleteDieta(dietaId){
            document.getElementById("deleteDietaSelector").value=dietaId;
            document.getElementById("formDeleteDieta").submit();
        }

        function filtrarClientes(){
            document.getElementById("filtroClientes").submit();
        }

        function filtrarDietas(){
            document.getElementById("filtroDietas").submit();
        }
    </script>
</head>
<body>
<header>
    <a href="/dietista/clientes?clienteId=<%= cliente.getId() %>">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="back">
    </a>
    <h1 class="header-text text-center">Cliente</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Clientes">
                <table class="table cliente_table caption-top text-center table-hover">
                    <caption class="text-center text-white">List of users</caption>
                    <thead class="table-dark">
                    <form:form id="filtroClientes" method="get" action="/dietista/filtrarClientes" modelAttribute="filtroCliente">
                        <form:hidden path="sourcePage" value="clientes_asignar_dietas"/>
                        <form:input type="hidden" path="clienteId" value="<%=cliente!=null? cliente.getId() : 0%>"/>
                        <tr>
                            <th class="id"><button class="btn btn-dark" onclick="filtrarClientes">🔍</button></th>
                            <th class="nombre-cliente"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                            <th class="apellidos-cliente"><form:input path="apellidos" class="form-control" data-bs-theme="dark" placeholder="Apellidos"/></th>
                        </tr>
                    </form:form>
                    </thead>
                    <tbody class = "table-secondary">
                    <form id="formSelectCliente" method="get" action="./asignarDietasCliente">
                        <input type="hidden" name="clienteId" id="clienteSelector">
                        <%
                            for(UsuarioDTO c : clientes){
                        %>

                        <tr class="cliente" onclick="selectCliente(<%= c.getId() %>)">
                            <td><%= c.getId() %></td>
                            <td><%= c.getNombre() %></td>
                            <td><%= c.getApellidos() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </form>
                    </tbody>
                </table>
            </section>
        </div>

        <div class="col-md">
            <section id="nombreCliente">
                <div class="pt-4 row ps-3 gap-0">
                        <label class="tag col-2 bg-secondary" for="nombre">Nombre:</label>
                        <div class="col">
                            <%
                                String name = "";
                                if(cliente!=null){name=cliente.getNombre() + " " + cliente.getApellidos();}
                            %>
                            <input type="hidden" name="clienteId" value="<%= cliente==null ? 0:cliente.getId() %>">
                            <input id="nombre" type="text" class="form-control" name="nombre" value="<%= name %>" disabled>
                        </div>
                </div>
            </section>

            <section id="ListaDietasCliente">
                <div class="table-responsive dietas_table">
                    <table class="table caption-top text-center table-hover">
                        <caption class="text-center text-white">Dietas del Cliente</caption>
                        <thead class="header table-dark">
                        <tr>
                            <th class="id">Orden</th>
                            <th class="nombre-dieta">Nombre</th>
                            <th class="fecha">Fecha</th>
                            <th class="table-button"></th>
                        </tr>
                        </thead>
                        <form id="formSelectDietaFromCliente" method="get" action="./asignarDietasCliente">
                            <input type="hidden" name="clienteId" value=<%= cliente!=null?cliente.getId():0 %>>
                            <input type="hidden" name="dietaId" id="dietaFromClienteSelector">
                        </form>
                        <tbody class = "table-secondary">
                        <form id="formDeleteDieta" method="post" action="./eliminarDietaCliente">
                            <input type="hidden" name="dietaId" id="deleteDietaSelector">
                            <input type="hidden" name="clienteId" value="<%= cliente==null ? 0:cliente.getId() %>">
                            <%
                                int dietasClienteIndex = 0;
                                for(DietaDTO d : dietasCliente){
                                    dietasClienteIndex++;

                            %>
                            <tr>
                                <td onclick="selectDietaFromCliente(<%= d.getId() %>)"><%= dietasClienteIndex %></td>
                                <td onclick="selectDietaFromCliente(<%= d.getId() %>)"><%= d.getNombre() %></td>
                                <td onclick="selectDietaFromCliente(<%= d.getId() %>)"><%= d.getFechaCreacion().format(formatter) %></td>
                                <td><button  class="btn btn-danger" onclick="deleteDieta(<%= d.getId() %>)">-</button></td>
                            </tr>
                            <%
                                }
                            %>
                        </form>
                        </tbody>
                    </table>
                </div>
            </section>

            <%
                if(dieta!=null){
            %>
            <div class="menus_table">
                <table class="table caption-top text-center table-hover">
                    <caption class="text-center text-white"><%= "Menús de "+dieta.getNombre() %></caption>
                    <thead class="header table-dark">
                    <tr>
                        <th class="id">Día</th>
                        <th class="nombre-menu">Nombre</th>
                        <th class="kcal">Kcal</th>
                    </tr>
                    </thead>
                    <tbody class = "table-secondary">
                    <%
                        for(OrdenMenuDietaDTO ordenMenuDieta : menusDieta){
                    %>
                    <tr>
                        <td><%= UtilityFunctions.getDayByNumber(ordenMenuDieta.getOrden()) %></td>
                        <td><%= ordenMenuDieta.getMenu().getNombre() %></td>
                        <td><%= ordenMenuDieta.getMenu().getCalorias() %></td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
            <%
                }
            %>
        </div>

        <div class="col-md">
            <section id="Dietas">

                <div class="table-responsive">
                    <table class="table caption-top text-center table-hover">
                        <caption class="header text-center text-white">Dietas</caption>
                        <thead class="header table-dark">
                        <form:form id="filtroDietas" method="get" action="/dietista/filtrarDietasCliente" modelAttribute="filtroDieta">
                            <form:input type="hidden" path="clienteId" value="<%=cliente!=null? cliente.getId() : 0%>"/>
                            <tr>
                                <th class="id"><button class="btn btn-dark" onclick="filtrarDietas">🔍</button></th>
                                <th class="nombre-dieta"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                                <th class="fecha">Fecha</th>
                                <th class="table-button"></th>
                            </tr>
                        </form:form>
                        </thead>
                        <tbody class = "table-secondary">
                        <form id="formSelectDieta" method="get" action="./asignarDietasCliente">
                            <input type="hidden" name="clienteId" value=<%= cliente!=null ? cliente.getId() : 0 %>>
                            <input type="hidden" name="dietaId" id="dietaSelector">
                        </form>
                        <form id="formAddDieta" method="post" action="./asignarDietaCliente">
                            <input type="hidden" name="dietaId" id="addDietaSelector">
                            <input type="hidden" name="clienteId" value="<%= cliente!=null ? cliente.getId() : 0 %>">
                            <%
                                int dietaIndex = 0;
                                for(DietaDTO d : dietas){
                                    dietaIndex++;
                            %>
                            <tr>
                                <td onclick="selectDieta(<%= d.getId() %>)"><%= dietaIndex %></td>
                                <td onclick="selectDieta(<%= d.getId() %>)"><%= d.getNombre() %></td>
                                <td onclick="selectDieta(<%= d.getId() %>)"><%= d.getFechaCreacion().format(formatter) %></td>
                                <td><button  class="btn btn-primary" onclick="addDieta(<%= d.getId() %>)" <%=cliente==null?"disabled":""%>>+</button></td>
                            </tr>
                            <%
                                }
                            %>
                        </form>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </div>
</div>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
