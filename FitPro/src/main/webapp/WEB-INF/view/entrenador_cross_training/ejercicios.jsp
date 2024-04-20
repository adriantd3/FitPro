<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="uma.fitpro.entity.TipoEjercicio" %>
<%@ page import="uma.fitpro.entity.GrupoMuscular" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    List<TipoEjercicio> tipos = (List<TipoEjercicio>) request.getAttribute("tipos");
    List<GrupoMuscular> grupos = (List<GrupoMuscular>) request.getAttribute("grupos");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ejercicios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="">
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<nav class="navbar navbar-light" style="justify-content: center">
    <form method="get" action="/entrenador_cross_training/ejercicios">
        <section class="section-ejercicios">
            <input class="form-control mr-sm-2" type="search" style="width: 400px" placeholder="Introduzca el ejercicio" aria-label="Search" name="ejercicio" value="<%= request.getParameter("ejercicio") %>">
            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Buscar</button>
        </section>
        <section class="ejercicio-filtros">
            <div style="color: beige">
                Grupo muscular:
                <select class="selectpicker" data-style="btn-primary" name="musculo">
                    <%
                        for (GrupoMuscular g : grupos){

                    %>
                    <option value="<%= g.getGrupoMuscular() %>"><%= g.getGrupoMuscular() %></option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div style="color: beige">
                Categoria:
                <select class="selectpicker" data-style="btn-primary" name="tipo">
                    <%
                        for (TipoEjercicio t : tipos){

                    %>
                    <option value="<%= t.getTipo() %>"><%= t.getTipo() %></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </section>
    </form>
</nav>

<ul style="margin: 40px;">
    <%
        for (Ejercicio e : ejercicios){


    %>
    <li style="color: beige"><h6><%= e.getNombre() + ", MUSCULO: " + e.getGrupoMuscular().getGrupoMuscular() + ", TIPO: " + e.getTipo().getTipo()%></h6></li>
    <%
        }
    %>
</ul>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>