<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uma.fitpro.ui.FiltroMenu" %>
<%@ page import="uma.fitpro.ui.FiltroComida" %>
<%@ page import="uma.fitpro.ui.FiltroDieta" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 20/05/2024
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
    Dieta dieta = (Dieta) request.getAttribute("dieta");
    List<Menu> menus = (List<Menu>) request.getAttribute("menus");
    List<OrdenMenuDieta> menusDieta = (List<OrdenMenuDieta>) request.getAttribute("menusDieta");
    Menu menu = (Menu) request.getAttribute("menu");
    List<Comida> comidasMenu = (List<Comida>) request.getAttribute("comidasMenu");
    FiltroDieta filtroDieta = (FiltroDieta) request.getAttribute("filtroDieta");
    FiltroMenu filtroMenu = (FiltroMenu) request.getAttribute("filtroMenu");
    for(OrdenMenuDieta m : menusDieta) {
        menus.remove(m.getMenu());
    }
%>

<html>
<head>
    <title>Dietas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./dietas.css"%></style>

    <script>
        function selectDieta(dietaId){
            document.getElementById("dietaSelector").value=dietaId;
            document.getElementById("formSelectDieta").submit();
        }

        function  selectMenuDieta(menuId){
            console.log(menuId);
            console.log(<%=dieta==null ? 0:dieta.getId()%>);
            document.getElementById("menuFromDietaSelector").value=menuId;
            document.getElementById("formSelectMenuFromDieta").submit();
        }

        function  selectMenu(menuId){
            console.log("SELECT")
            document.getElementById("menuSelector").value=menuId;
            document.getElementById("formSelectMenu").submit();
        }

        function addMenu(menuId){
            //if(!document.getElementById("nombre").value.equals("")){ Este if hace que no funcione
            console.log("ADD")
            document.getElementById("addMenuSelector").value=menuId;
            document.getElementById("formAddMenu").submit();
            //}
        }

        function deleteMenu(menuId, ordenMenu){
            
            //console.log(ordenMenuId)
            document.getElementById("deleteMenuSelector").value=menuId;
            document.getElementById("deleteMenuOrden").value=ordenMenu;
            document.getElementById("formDeleteMenu").submit();
        }

        function guardarDieta(){
            document.getElementById("datosDieta").action="./guardarDieta";
            document.getElementById("datosDieta").submit();
        }

        function filtrarDietas(){
            document.getElementById("filtroDietas").submit();
        }

        function filtrarMenus(){
            document.getElementById("filtroMenu").submit();
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Dietas</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Dietas" class="pt-2">

                <div class="table-responsive">
                    <table class="table dieta_table caption-top text-center table-hover">
                        <caption class="text-center text-white">Dietas</caption>
                        <thead class="header table-dark">
                        <form:form id="filtroDietas" method="get" action="/dietista/filtrarDietas" modelAttribute="filtroDieta">
                            <tr>
                                <th class="id"><button class="btn btn-dark" onclick="filtrarDietas">🔍</button></th>
                                <th class="nombre-dieta"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                                <th class="fecha">Fecha creación</th>
                            </tr>
                        </form:form>
                        </thead>
                        <tbody class = "table-group-divider table-secondary">
                        <form id="formSelectDieta" method="get" action="./dietas">
                            <input type="hidden" name="dietaId" id="dietaSelector">
                            <%
                                int dietaIndex = 0;
                                for(Dieta d : dietas){
                                    dietaIndex++;
                            %>
                            <tr class="dieta" onclick="selectDieta(<%= d.getId() %>)">
                                <td><%= dietaIndex %></td>
                                <td><%= d.getNombre() %></td>
                                <td><%= d.getFechaCreacion() %></td>
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
                <form id="datosDieta" method="post" action="./guardarDieta">
                    <div class="pt-4 row ps-3">
                        <span class="tag col-2 bg-secondary">Nombre</span>
                        <div class="col pe-5">
                            <%
                                String name = "";
                                if(dieta!=null){name=dieta.getNombre();}
                            %>
                            <input type="hidden" name="dietaId" value="<%= dieta==null ? 0:dieta.getId() %>">
                            <input id="nombre" type="text" class="form-control" name="nombre" value=<%= name %>>
                        </div>
                    </div>
                </form>
            </section>

                    <section id="ListaMenusDieta">
                            <div class="table-responsive menus_table">
                                <table class="table caption-top text-center table-hover">
                                    <caption class="text-center text-white">Menus de la Dieta</caption>
                                    <thead class="header table-dark">
                                    <tr>
                                        <th class="id">Orden</th>
                                        <th class="nombre-menu">Nombre</th>
                                        <th class="kcal">Kcal</th>
                                        <th class="table-button"></th>
                                    </tr>
                                    </thead>
                                    <form id="formSelectMenuFromDieta" method="get" action="./dietas">
                                        <input type="hidden" name="dietaId" value=<%= dieta!=null?dieta.getId():0 %>>
                                        <input type="hidden" name="menuId" id="menuFromDietaSelector">
                                    </form>
                                        <tbody class = "table-secondary">
                                        <form id="formDeleteMenu" method="post" action="./eliminarMenuDieta">
                                            <input type="hidden" name="menuId" id="deleteMenuSelector">
                                            <input type="hidden" name="ordenMenu" id="deleteMenuOrden">
                                            <input type="hidden" name="dietaId" value="<%= dieta==null ? 0:dieta.getId() %>">
                                            <%

                                                for(OrdenMenuDieta m : menusDieta){

                                            %>
                                            <tr>
                                                <td onclick="selectMenu(<%= m.getMenu().getId() %>)"><%= m.getId().getOrden() %></td>
                                                <td onclick="selectMenu(<%= m.getMenu().getId() %>)"><%= m.getMenu().getNombre() %></td>
                                                <td onclick="selectMenu(<%= m.getMenu().getId() %>)"><%= m.getMenu().getCalorias() %></td>
                                                <td><button  class="btn btn-danger" onclick="deleteMenu(<%= m.getMenu().getId() %>, <%= m.getId().getOrden()%>)">-</button></td>
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
                        if(menu!=null){
                    %>
                    <div class="comidas_table">
                        <table class="table caption-top text-center table-hover">
                            <caption class="text-center text-white"><%= "Comidas de "+menu.getNombre() %></caption>
                            <thead class="header table-dark">
                            <tr>
                                <th class="id"></th>
                                <th class="nombre-comida">Nombre</th>
                                <th class="kcal">Kcal</th>
                            </tr>
                            </thead>
                            <tbody class = "table-secondary">
                            <%
                                if(menu!=null){
                                    int comidasMenuIndex = 0;
                                    for(Comida comida : comidasMenu){
                                        comidasMenuIndex++;
                            %>
                            <tr>
                                <td><%= comidasMenuIndex %></td>
                                <td><%= comida.getNombre() %></td>
                                <td><%= comida.getCalorias() %></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <%
                        }
                    %>
                    <div class="dietaButtons">
                        <button type="submit" class="btn btn-success me-3 saveButton" onclick="guardarDieta()">Guardar</button>
                        <form method="get" action="./limpiarDieta">
                            <button type="submit" class="btn btn-primary me-3">Limpiar</button>
                        </form>
                        <form method="post" action="./borrarDieta">
                            <button type="<%= dieta==null ? "button":"submit" %>" class="btn btn-danger me-3" name="dietaId" value="<%= dieta==null ? 0:dieta.getId() %>">Borrar</button>
                        </form>
                    </div>
                </div>

                <div class="col-md">
                    <section id="Menus">

                        <div class="table-responsive">
                            <table class="table caption-top text-center table-hover">
                                <caption class="header text-center text-white">Menus</caption>
                                <thead class="header table-dark">
                                <form:form id="filtroMenus" method="get" action="/dietista/filtrarMenusDieta" modelAttribute="filtroMenu">
                                    <form:input type="hidden" path="dietaId" value="<%=dieta!=null? dieta.getId() : 0%>"/>
                                    <tr>
                                        <th class="id"><button class="btn btn-dark" onclick="filtrarMenus">🔍</button></th>
                                        <th class="nombre-menu"><form:input path="nombre" class="form-control" data-bs-theme="dark" placeholder="Nombre"/></th>
                                        <th class="kcal"><form:input path="kcal" class="form-control" data-bs-theme="dark" placeholder="Kcal"/></th>
                                        <th class="table-button"></th>
                                    </tr>
                                </form:form>
                                </thead>
                                <tbody class = "table-secondary">
                                <form id="formSelectMenu" method="get" action="./dietas">
                                    <input type="hidden" name="dietaId" value=<%= dieta!=null ? dieta.getId() : 0 %>>
                                    <input type="hidden" name="menuId" id="menuSelector">
                                </form>
                                <form id="formAddMenu" method="post" action="./anyadirMenuDieta">
                                    <input type="hidden" name="menuId" id="addMenuSelector">
                                    <input type="hidden" name="dietaId" value="<%= dieta!=null ? dieta.getId() : 0 %>">
                                    <%
                                        int menuIndex = 0;
                                        for(Menu m : menus){
                                            menuIndex++;
                                    %>
                                    <tr>
                                        <td onclick="selectMenu(<%= m.getId() %>)"><%= menuIndex %></td>
                                        <td onclick="selectMenu(<%= m.getId() %>)"><%= m.getNombre() %></td>
                                        <td onclick="selectMenu(<%= m.getId() %>)"><%= m.getCalorias() %></td>
                                        <td><button  class="btn btn-primary" onclick="addMenu(<%= m.getId() %>)">+</button></td>
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