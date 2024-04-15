<%--
  Created by IntelliJ IDEA.
  User: jabr3
  Date: 15/04/2024
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./menus.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Header Text</h1>
</header>

<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Menus" class="menu-table pt-2">
                <table class="table caption-top text-center ">
                    <caption class="text-center">List of users</caption>
                    <thead class="table-dark">
                    <tr>
                        <th class="id"></th>
                        <th class="nombre-menu">Nombre</th>
                        <th class="kcal">Kcal</th>
                    </tr>
                    </thead>
                    <tbody class = "table-group-divider table-secondary">
                    <tr>
                        <td>1</td>
                        <td>x</td>
                        <td>x</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>x</td>
                        <td>x</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>x</td>
                        <td>x</td>
                    </tr>
                    <tr>
                        <td>
                            4
                        </td>
                        <td>x</td>
                        <td>x</td>
                    </tr>
                    </tbody>
                </table>
            </section>
        </div>
        <div class="col-md">
            <section id="nombre">
                <div class="pt-4 row ps-3">
                        <span class="tag col-2 bg-secondary">Nombre</span>
                        <div class="col pe-5">
                            <input type="text" class="form-control" name="nombre">
                        </div>
                </div>
            </section>
            <div class="row">
                <div class="col-md">
                    <section id="ComidasMenu" class="menu-table">
                        <table class="table caption-top text-center ">
                            <caption class="text-center">Comidas del Menú</caption>
                            <thead class="table-dark">
                            <tr>
                                <th class="id"></th>
                                <th class="nombre-comida">Nombre</th>
                                <th class="kcal">Kcal</th>
                                <th class="table-button"></th>
                            </tr>
                            </thead>
                            <tbody class = "table-secondary">
                            <tr>
                                <td style="height: 30px;">1</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-danger border-dark">E</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-danger border-dark">E</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-danger border-dark">E</td>
                            </tr>
                            <tr>
                                <td>
                                    4
                                </td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-danger border-dark">E</td>
                            </tr>
                            </tbody>
                        </table>
                    </section>

                    <section id="buttons">
                        <button type="button" class="btn btn-success me-3">Guardar</button>
                        <button type="button" class="btn btn-primary me-3">Limpiar</button>
                        <button type="button" class="btn btn-danger me-3">Borrar</button>

                    </section>
                </div>
                <div class="col-md">
                    <section id="Comidas" class="menu-table">
                        <table class="table caption-top text-center ">
                            <caption class="text-center">Comidas</caption>
                            <thead class="table-dark">
                            <tr>
                                <th class="id"></th>
                                <th class="nombre-comida">Nombre</th>
                                <th class="kcal">Kcal</th>
                                <th class="table-button"></th>
                            </tr>
                            </thead>
                            <tbody class = "table-secondary">
                            <tr>
                                <td style="height: 30px;">1</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-primary border-dark">A</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-primary border-dark">A</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-primary border-dark">A</td>
                            </tr>
                            <tr>
                                <td>
                                    4
                                </td>
                                <td>x</td>
                                <td>x</td>
                                <td class=" bg-primary border-dark">A</td>
                            </tr>
                            </tbody>
                        </table>
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
