<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.ui.FiltroCliente" %>
<%@ page import="uma.fitpro.ui.FiltroDieta" %>
<%@ page import="uma.fitpro.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 22/05/2024
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
    Usuario cliente = (Usuario) request.getAttribute("cliente");
    List<Dieta> dietasCliente = (List<Dieta>) request.getAttribute("dietasCliente");
    Dieta dieta = (Dieta) request.getAttribute("dieta");
    List<OrdenMenuDieta> menusDieta = (List<OrdenMenuDieta>) request.getAttribute("menusDieta");
    List<DesempenyoMenu> desempenyosMenu = (List<DesempenyoMenu>) request.getAttribute("desempenyosMenu");

    FiltroCliente filtroCliente = (FiltroCliente) request.getAttribute("filtroCliente");
%>

<html>
<head>
    <title>DesempenyoComidasMenu</title>
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

        function filtrarDesempenyosMenu(){
            document.getElementById("filtroDesempenyosMenu").submit();
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="back">
    <h1 class="header-text text-center">Clientes</h1>
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
                        <tr>
                            <th class="id"><button class="btn btn-dark" onclick="filtrarClientes">🔍</button></th>
                            <th class="nombre-cliente"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                            <th class="apellidos-cliente"><form:input path="apellidos" class="form-control" data-bs-theme="dark" placeholder="Apellidos"/></th>
                        </tr>
                    </form:form>
                    </thead>
                    <tbody class = "table-secondary">
                    <form id="formSelectCliente" method="get" action="./asignarDietasClientes">
                        <input type="hidden" name="clienteId" id="clienteSelector">
                        <%
                            for(Usuario c : clientes){
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
                <div class="table-responsive dietasCliente_table">
                    <table class="table caption-top text-center table-hover">
                        <caption class="text-center text-white">Dietas del Cliente</caption>
                        <thead class="header table-dark">
                        <tr>
                            <th class="id">Orden</th>
                            <th class="nombre-dieta">Nombre</th>
                            <th class="fecha">Fecha</th>
                        </tr>
                        </thead>
                        <form id="formSelectDietaFromCliente" method="get" action="./desempenyoClientes">
                            <input type="hidden" name="clienteId" value=<%= cliente!=null?cliente.getId():0 %>>
                            <input type="hidden" name="dietaId" id="dietaFromClienteSelector">
                        </form>
                        <tbody class = "table-secondary">
                            <%
                                int dietasClienteIndex = 0;
                                for(Dieta d : dietasCliente){
                                    dietasClienteIndex++;

                            %>
                            <tr onclick="selectDietaFromCliente(<%= d.getId() %>)">
                                <td><%= dietasClienteIndex %></td>
                                <td><%= d.getNombre() %></td>
                                <td><%= d.getFechaCreacion() %></td>
                            </tr>
                            <%
                                }
                            %>
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
                        <th class="id">DíaSemana</th>
                        <th class="nombre-menu">Nombre</th>
                        <th class="kcal">Kcal</th>
                    </tr>
                    </thead>
                    <tbody class = "table-secondary">
                    <%
                        int ordenMenuDietaIndex = 0;
                        for(OrdenMenuDieta ordenMenuDieta : menusDieta){
                            ordenMenuDietaIndex++;
                    %>
                    <tr>
                        <td><%= ordenMenuDietaIndex %></td>
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
            <section id="DesempenyosMenu">

                <div class="table-responsive">
                    <table class="table caption-top text-center table-hover">
                        <caption class="header text-center text-white">Desempeños Menu</caption>
                        <thead class="header table-dark">
                        <form:form id="filtroDesempenyoMenu" method="get" action="/dietista/filtrarDesempenyosMenuCliente" modelAttribute="filtroDesempenyoMenu">
                            <form:input type="hidden" path="clienteId" value="<%=cliente!=null? cliente.getId() : 0%>"/>
                            <form:input type="hidden" path="dietaId" value="<%=dieta!=null? dieta.getId() : 0%>"/>
                            <form:input type="hidden" path="menuId" value="<%=desempenyosMenu.isEmpty()? 0 : desempenyosMenu.get(0).getMenu().getId()%>"/>
                            <tr>
                                <th class="id"><button class="btn btn-dark" onclick="filtrarDesempenyosMenu">🔍</button></th>
                                <th class="nombre-dieta">Nombre</th>
                                <th class="kcal">Kcal</th>
                                <th class="fecha"><form:input path="fecha" class="form-control" data-bs-theme="dark" placeholder="Fecha"/></th>
                                <th class="kcal">Terminado</th>
                            </tr>
                        </form:form>
                        </thead>
                        <tbody class = "table-secondary">
                        <form id="formSelectDesempenyoMenu" method="get" action="./desempenyoMenuClientes">
                            <input type="hidden" name="clienteId" value=<%= cliente!=null ? cliente.getId() : 0 %>>
                            <input type="hidden" name="desempenyoMenu" id="desempenyoMenuSelector">
                        </form>
                            <%
                                int desempenyoIndex = 0;
                                for(DesempenyoMenu desempenyoMenu : desempenyosMenu){
                                    desempenyoIndex++;
                            %>
                            <tr>
                                <td><%= desempenyoMenu %></td>
                                <td><%= desempenyoMenu.getMenu().getNombre() %></td>
                                <td><%= desempenyoMenu.getMenu().getCalorias() %></td>
                                <td><%= desempenyoMenu.getFechaCreacion() %></td>
                                <td><%= desempenyoMenu.getTerminado() == (byte) 1?"✔":"❌" %></td>
                            </tr>
                            <%
                                }
                            %>
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

