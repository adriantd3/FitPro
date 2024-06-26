<%@ page import="uma.fitpro.dto.MenuDTO" %>
<%@ page import="uma.fitpro.dto.ComidaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uma.fitpro.ui.FiltroMenu" %>
<%@ page import="uma.fitpro.ui.FiltroComida" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 15/04/2024
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<MenuDTO> menus = (List<MenuDTO>) request.getAttribute("menus");
    MenuDTO menu = (MenuDTO) request.getAttribute("menu");
    FiltroMenu filtroMenu = (FiltroMenu) request.getAttribute("filtroMenu");
    FiltroComida filtroComida = (FiltroComida) request.getAttribute("filtroComida");
    List<ComidaDTO> comidasMenu = (List<ComidaDTO>) request.getAttribute("comidasMenu");
    List<ComidaDTO> comidas = (List<ComidaDTO>) request.getAttribute("comidas");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/uuuu");
    comidas.removeAll(comidasMenu);
%>

<html>
<head>
    <title>Menus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./menus.css"%></style>

    <script>
        function selectMenu(menuId){
            document.getElementById("menuSelector").value=menuId;
            document.getElementById("formSelectMenu").submit();
        }

        function addComida(comidaId){
            //if(!document.getElementById("nombre").value.equals("")){ Este if hace que no funcione
                document.getElementById("addComidaSelector").value=comidaId;
                document.getElementById("formAddComida").submit();
            //}
        }

        function deleteComida(comidaId){
            document.getElementById("deleteComidaSelector").value=comidaId;
            document.getElementById("datosMenu").action="./eliminarComida";
            document.getElementById("datosMenu").submit();
        }

        function guardarMenu(){
            document.getElementById("datosMenu").action="./guardarMenu";
            document.getElementById("datosMenu").submit();
        }

        function filtrarMenu(){
            document.getElementById("filtroMenu").submit();
        }

        function filtrarComida(){
            document.getElementById("filtroComida").submit();
        }
    </script>
</head>
<body>
<header>
    <a href="/dietista">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="back">
    </a>
    <h1 class="header-text text-center">Menús</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Menus" class="pt-2">

                    <div class="table-responsive">
                        <table class="table menu_table caption-top text-center table-hover">
                            <caption class="text-center text-white">Menús</caption>
                            <thead class="header table-dark">
                                <form:form id="filtroMenu" method="get" action="/dietista/filtrarMenu" modelAttribute="filtroMenu">
                                    <tr>
                                        <th class="menuId"><button class="btn btn-dark" onclick="filtrarMenu">🔍</button></th>
                                        <th class="nombre-menu"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                                        <th class="kcal"><form:input type="number" path="kcal" class="form-control" data-bs-theme="dark" placeholder="Kcal"/></th>
                                        <th class="fecha"><form:input type="date" path="fecha" class="form-control" data-bs-theme="dark" placeholder="Fecha Creacion"/></th>
                                    </tr>
                                </form:form>
                            </thead>
                            <tbody class = "table-group-divider table-secondary">
                                <form id="formSelectMenu" method="get" action="./menus">
                                    <input type="hidden" name="menuId" id="menuSelector">
                                    <%
                                        int menuIndex = 0;
                                        for(MenuDTO m : menus){
                                            menuIndex++;
                                    %>
                                        <tr class="menu" onclick="selectMenu(<%= m.getId() %>)">
                                            <td><%= menuIndex %></td>
                                            <td><%= m.getNombre() %></td>
                                            <td><%= m.getCalorias() %></td>
                                            <td><%= m.getFechaCreacion().format(formatter) %></td>
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

        <div class="col-md">
                    <section id="nombreInput">
                        <form id="datosMenu" method="post" action="./guardarMenu">
                            <div class="pt-4 row ps-3">
                                    <span class="tag col-2 bg-secondary">Nombre</span>
                                    <div class="col pe-5">
                                        <%
                                            String name = "";
                                            if(menu!=null){name=menu.getNombre();}
                                        %>
                                        <input type="hidden" name="menuId" value="<%= menu==null ? 0:menu.getId() %>">
                                        <input id="nombre" type="text" class="form-control" name="nombre" value=<%= name %>>
                                    </div>
                            </div>
                        </form>
                    </section>
                    <div class="row">

                        <div class="col-md">
                            <section id="ListaComidasMenu">
                                <form id="formDeleteComida" method="post" action="./eliminarComida">
                                    <input type="hidden" name="comidaId" id="deleteComidaSelector">
                                    <input type="hidden" name="menuId" value="<%= menu==null ? 0:menu.getId() %>">
                                    <div class="table-responsive">
                                        <table class="table caption-top text-center table-hover">
                                            <caption class="text-center text-white">Comidas del Menú</caption>
                                            <thead class="header table-dark">
                                                <tr>
                                                    <th class="menuId"></th>
                                                    <th class="nombre-comida">Nombre</th>
                                                    <th class="kcal">Kcal</th>
                                                    <th class="table-button"></th>
                                                </tr>
                                            </thead>
                                            <tbody class = "table-secondary">
                                                <%
                                                    if(menu!=null){
                                                        int comidasMenuIndex = 0;
                                                        for(ComidaDTO comida : comidasMenu){
                                                            comidasMenuIndex++;
                                                %>
                                                    <tr>
                                                        <td><%= comidasMenuIndex %></td>
                                                        <td><%= comida.getNombre() %></td>
                                                        <td><%= comida.getCalorias() %></td>
                                                        <td><button class="btn bg-danger" onclick="deleteComida(<%= comida.getId() %>)">-</button></td>
                                                    </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </form>
                            </section>
                            <div class="menuButtons">
                                <button type="submit" class="btn btn-success me-3 saveButton" onclick="guardarMenu()">Guardar</button>
                                <form method="get" action="./limpiarMenu">
                                    <button type="submit" class="btn btn-primary me-3" <%=menu==null?"disabled":""%>>Limpiar</button>
                                </form>
                                <form method="post" action="./borrarMenu">
                                    <button type="<%= menu==null ? "button":"submit" %>" class="btn btn-danger me-3" name="menuId" value="<%= menu==null ? 0:menu.getId() %>" <%=menu==null?"disabled":""%>>Borrar</button>
                                </form>
                            </div>
                    </div>

                <div class="col-md">
                    <section id="Comidas">

                            <div class="table-responsive">
                                <table class="table caption-top text-center table-hover">
                                    <caption class="header text-center text-white">Comidas</caption>
                                    <thead class="header table-dark">
                                    <form:form id="filtroComida" method="get" action="/dietista/filtrarComida" modelAttribute="filtroComida">
                                        <form:input type="hidden" path="menuId" value="<%=menu!=null? menu.getId() : 0%>"/>
                                        <tr>
                                            <th class="menuId"><button class="btn btn-dark" onclick="filtrarComida">🔍</button></th>
                                            <th class="nombre-menu"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                                            <th class="kcal"><form:input type="number" path="kcal" class="form-control" data-bs-theme="dark" placeholder="Kcal"/></th>
                                            <th class="table-button"></th>
                                        </tr>
                                    </form:form>
                                    </thead>
                                    <tbody class = "table-secondary">
                                    <form id="formAddComida" method="post" action="./anyadirComida">
                                        <input type="hidden" name="comidaId" id="addComidaSelector">
                                        <input type="hidden" name="menuId" value="<%= menu==null ? 0:menu.getId() %>">
                                    <%
                                        int comidaIndex = 0;
                                        for(ComidaDTO comida : comidas){
                                            comidaIndex++;
                                    %>
                                    <tr>
                                        <td><%= comidaIndex %></td>
                                        <td><%= comida.getNombre() %></td>
                                        <td><%= comida.getCalorias() %></td>
                                        <td><button  class="btn btn-primary" onclick="addComida(<%= comida.getId() %>)" <%=menu==null? "disabled":menu.getComidas().size()>=5?"disabled":""%>>+</button></td>
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
