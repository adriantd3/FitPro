<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
    String nombreFiltrado = "";
    if (request.getParameter("nombre")!=null) nombreFiltrado = request.getParameter("nombre");

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/home">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Clientes</h1>
</header>
<section class="scrollable-section">
    <section class="table-container">
        <table class="table table-striped table-dark">
            <thead>
            <form method="get" action="/entrenador_cross_training/filtrar_clientes">
                <tr>
                    <th scope="col" >#</th>
                    <th scope="col">
                        <div class="filter-flex">
                            <input type="text" placeholder="Nombre" name="nombre" value="<%= nombreFiltrado %>" class="form-control filter-input" data-bs-theme="dark" >
                            <button class="btn btn-dark">🔍</button>
                        </div>
                    </th>
                    <th scope="col" >Rutinas</th>
                </tr>
            </form>
            </thead>
            <tbody>
            <%
                int num = 1;
                for (Usuario u : clientes){


            %>
            <tr>
                <th scope="row"><%= num %></th>
                <td><%= u.getNombre() + " " + u.getApellidos()%></td>
                <td><button class="btn btn-secondary" name="rutinas_cliente" onclick="window.location.href='/entrenador_cross_training/rutinas_cliente?id=<%= u.getId()%>'">Rutinas</button></td>
            </tr>
            <%
                    num++;
                }
            %>

            </tbody>
        </table>
    </section>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>