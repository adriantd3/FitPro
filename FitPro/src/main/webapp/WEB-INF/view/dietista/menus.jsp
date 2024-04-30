<%@ page import="uma.fitpro.entity.Menu" %>
<%@ page import="uma.fitpro.entity.Comida" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 15/04/2024
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Menu> menus = (List<Menu>) request.getAttribute("menus");
    Menu menu = (Menu) request.getAttribute("menu");
    List<Comida> comidasMenu = (List<Comida>) request.getAttribute("comidasMenu");
    List<Comida> comidas = (List<Comida>) request.getAttribute("comidas");
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
            document.forms[0].submit();
        }

        function addComida(comidaId){
            document.getElementById("comidaSelector").value=comidaId;
            document.getElementById("formAddComida").submit();
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Menús</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Menus" class="menu_table pt-2">
                <form method="get" action="./menus">
                    <input type="hidden" name="id" id="menuSelector">
                    <table class="table caption-top text-center table-hover">
                        <caption class="text-center text-white">Menús</caption>
                        <thead class="table-dark">
                            <tr>
                                <th class="id">º\</th>
                                <th class="nombre-menu">Nombre</th>
                                <th class="kcal">Kcal</th>
                            </tr>
                        </thead>
                        <tbody class = "table-group-divider table-secondary">
                        <%
                            for(Menu m : menus){
                        %>
                            <tr>
                                <td><%= m.getId() %></td>
                                <td><%= m.getNombre() %></td>
                                <td><%= m.getCalorias() %></td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </form>
            </section>
        </div>
        <div class="col-md">
            <section id="comidasMenu">
                <form id="guardar" method="post" action="./guardar">
                    <div class="pt-4 row ps-3">
                            <span class="tag col-2 bg-secondary">Nombre</span>
                            <div class="col pe-5">
                                <%
                                    String name = "";
                                    if(menu!=null){name=menu.getNombre();}
                                %>
                                <input id="nombre" type="text" class="form-control" name="nombre" value=<%= name %>>
                            </div>
                    </div>
                    <div class="row">
                        <div class="col-md">
                            <section id="ListaComidasMenu">
                                <table class="table caption-top text-center table-hover">
                                    <caption class="text-center text-white">Comidas del Menú</caption>
                                    <thead class="table-dark">
                                        <tr>
                                            <th class="id"></th>
                                            <th class="nombre-comida">Nombre</th>
                                            <th class="kcal">Kcal</th>
                                            <th class="table-button"></th>
                                        </tr>
                                    </thead>
                                    <tbody class = "table-secondary">
                                        <%
                                            if(menu!=null){
                                                for(Comida c : comidasMenu){
                                        %>
                                            <tr>
                                                <td><%= c.getId() %></td>
                                                <td><%= c.getNombre() %></td>
                                                <td><%= c.getCalorias() %></td>
                                                <td><button class="btn bg-danger">E</button></td>
                                            </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </section>
                        </div>
                    </div>
                    <div class="menuButtons">
                        <button type="submit" class="btn btn-success me-3 saveButton" name="id" value="<%= menu==null ? 0:menu.getId() %>" onclick="guardar()">Guardar</button>
                        <form method="get" action="./limpiar">
                            <button type="submit" class="btn btn-primary me-3">Limpiar</button>
                        </form>
                        <form method="post" action="./borrar">
                            <button type="submit" class="btn btn-danger me-3" name="id" value="<%= menu==null ? 0:menu.getId() %>">Borrar</button>
                        </form>
                    </div>
                </form>
            </section>
                <div class="col-md">
                    <section id="Comidas">
                        <form id="formAddComida" method="post" action="./anyadirComida">
                            <input type="hidden" name="comidaId" id="comidaSelector">
                            <input type="hidden" name="menuId" value="<%= menu==null ? 0:menu.getId() %>">

                            <table class="table caption-top text-center table-hover">
                                <caption class="text-center text-white">Comidas</caption>
                                <thead class="table-dark">
                                <tr>
                                    <th class="id"></th>
                                    <th class="nombre-comida">Nombre</th>
                                    <th class="kcal">Kcal</th>
                                    <th class="table-button"></th>
                                </tr>
                                </thead>
                                <tbody class = "table-secondary">
                                <%
                                    for(Comida comida : comidas){
                                %>
                                <tr>
                                    <td><%= comida.getId() %></td>
                                    <td><%= comida.getNombre() %></td>
                                    <td><%= comida.getCalorias() %></td>
                                    <td><button  class="btn btn-primary" onclick="addComida(<%= comida.getId() %>)">A</button></td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>

                        </form>
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
