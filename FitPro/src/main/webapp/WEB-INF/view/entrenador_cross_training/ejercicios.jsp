<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page import="uma.fitpro.entity.TipoEjercicio" %>
<%@ page import="uma.fitpro.entity.GrupoMuscular" %>
<%@ page import="uma.fitpro.entity.Sesion" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    List<TipoEjercicio> tipos = (List<TipoEjercicio>) request.getAttribute("tipos");
    List<GrupoMuscular> grupos = (List<GrupoMuscular>) request.getAttribute("grupos");
    Sesion sesion = (Sesion) session.getAttribute("sesion");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ejercicios</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/sesion?id=<%=sesion.getId()%>">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<div class="div-ejercicio-filtros">
    <nav class="navbar navbar-light" style="
    width: 900px;
    border: 2px solid #73726e;
    border-radius: 10px;background-color: #73726e;">
        <form method="get" action="/entrenador_cross_training/ejercicios">
            <section class="section-ejercicios">
                <input class="form-control mr-sm-2" type="search" style="width: 400px" placeholder="Introduzca el ejercicio" aria-label="Search" name="ejercicio" value="<%= request.getParameter("ejercicio") %>">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </section>
            <section class="ejercicio-filtros">
                <div style="color: beige">
                    Grupo muscular:
                    <select class="selectpicker" data-live-search="true" data-style="btn-primary" name="musculo">
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
                <div style="color: beige">
                    <%
                        String checked = "";
                        if (request.getParameter("aplicar") !=null) {
                            checked = "checked";
                        }
                    %>
                    Aplicar filtros: <input type="checkbox" name="aplicar" value="1" <%=checked%>>
                </div>
            </section>
        </form>
    </nav>
</div>

<ul style="margin: 40px;">
    <%
        for (Ejercicio e : ejercicios){


    %>
    <li>
        <form method="post" action="/entrenador_cross_training/anyadir_ejercicio">
            <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
            <input type="hidden" name="ejercicio" value="<%=e.getId()%>">
            <button type="submit" class="btn btn-link"><%= e.getNombre() + ", MUSCULO: " + e.getGrupoMuscular().getGrupoMuscular() + ", TIPO: " + e.getTipo().getTipo()%></button>
        </form>
    </li>
    <%
        }
    %>
</ul>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>

<script>
    $(document).ready(function() {
        $('.selectpicker').selectpicker();
    });
</script>
</body>
</html>