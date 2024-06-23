<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.ui.FiltroCliente" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 24/04/2024
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<UsuarioDTO> clientes = (List<UsuarioDTO>) request.getAttribute("clientes");
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("cliente");
    FiltroCliente filtroCliente = (FiltroCliente) request.getAttribute("filtroCliente");
%>

<html>
<head>
    <title>Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./clientes.css"%></style>
    <script>
        function selectCliente(clienteId){
            document.getElementById("clienteSelector").value=clienteId;
            document.getElementById("formSelectCliente").submit();
        }
        function filtrarClientes(){
            document.getElementById("filtroClientes").submit();
        }
    </script>
</head>
<body>
<header>
    <a href="/dietista/">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="back">
    </a>
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
                        <form:hidden path="sourcePage" value="clientes"/>
                        <tr>
                            <th class="id"><button class="btn btn-dark" onclick="filtrarClientes">🔍</button></th>
                            <th class="nombre-cliente"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                            <th class="apellidos-cliente"><form:input path="apellidos" class="form-control" data-bs-theme="dark" placeholder="Apellidos"/></th>
                        </tr>
                    </form:form>
                    </thead>
                    <tbody class = "table-secondary">
                    <form id="formSelectCliente" method="get" action="./clientes">
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

        <div class="col-lg-5 ps-5 pt-5">
        <section id="nombreCliente">
            <div class="pt-4 row ps-3 gap-0">
                <form id="datosMenu" method="post" action="./guardarMenu">
                    <label class="tag col-2 bg-secondary" for="nombre">Nombre:</label>
                    <div class="col">
                        <%
                            String name = "";
                            if(cliente!=null){name=cliente.getNombre() + " " + cliente.getApellidos();}
                        %>
                        <input type="hidden" name="clienteId" value="<%= cliente==null ? 0:cliente.getId() %>">
                        <input id="nombre" type="text" class="form-control" name="nombre" value="<%= name %>" disabled>
                    </div>
                </form>
            </div>
        </section>
        <section id="opciones">
            <div class="d-flex flex-column justify-content-center align-items-center">
                <button class="btn  btn-secondary"
                        onclick="window.location.href='/dietista/asignarDietasCliente?clienteId=<%=cliente!=null?cliente.getId():0%>'"
                    <%=cliente==null?"disabled":""%>>
                    Asignar Dietas
                </button><br/>
                <button class="btn  btn-secondary"
                        onclick="window.location.href='/dietista/desempenyoCliente?clienteId=<%=cliente!=null?cliente.getId():0%>'"
                        <%=cliente==null?"disabled":""%>>
                    Desempeño
                </button>
            </div>
        </section>
        </div>
    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
