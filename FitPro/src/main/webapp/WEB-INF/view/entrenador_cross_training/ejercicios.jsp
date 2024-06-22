<%// AUTOR: Ezequiel Sánchez García (100%)%>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.dto.EjercicioDTO" %>
<%@ page import="uma.fitpro.dto.TipoEjercicioDTO" %>
<%@ page import="uma.fitpro.dto.GrupoMuscularDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EjercicioDTO> ejercicios = (List<EjercicioDTO>) request.getAttribute("ejercicios");
    List<TipoEjercicioDTO> tipos = (List<TipoEjercicioDTO>) request.getAttribute("tipos");
    List<GrupoMuscularDTO> grupos = (List<GrupoMuscularDTO>) request.getAttribute("grupos");
    SesionDTO sesion = (SesionDTO) session.getAttribute("sesion");

    String musculo = "";
    if (request.getParameter("musculo") != null) {musculo = request.getParameter("musculo");}
    String tipo = "";
    if (request.getParameter("tipo") != null) {tipo = request.getParameter("tipo");}

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Añadir ejercicio - <%= sesion.getNombre()%></title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/sesion?id=<%=sesion.getId()%>">
        <img style="margin: 4px" class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center">Añadir ejercicio - <%= sesion.getNombre()%></h1>
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
                            String selected = "";
                            for (GrupoMuscularDTO g : grupos){
                                if (g.getGrupoMuscular().equals(musculo)){
                                    selected = "selected";
                                }else {
                                    selected = "";
                                }
                        %>
                        <option <%=selected%> value="<%= g.getGrupoMuscular() %>"><%= g.getGrupoMuscular() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div style="color: beige">
                    Categoria:
                    <select class="selectpicker" data-style="btn-primary" name="tipo">
                        <%
                            for (TipoEjercicioDTO t : tipos){
                                if (t.getTipo().equals(tipo)){
                                    selected = "selected";
                                }else {
                                    selected = "";
                                }

                        %>
                        <option <%=selected%> value="<%= t.getTipo() %>"><%= t.getTipo() %></option>
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
<h5 class="h5-ejercicios">Escoge el ejercicio a añadir:</h5>
<section class="ejercicios-scrollable-section">
    <div class="div-ejercicio-buttons">
        <%
            for (EjercicioDTO e : ejercicios){


        %>
        <form method="post" action="/entrenador_cross_training/anyadir_ejercicio">
            <input type="hidden" name="sesion" value="<%=sesion.getId()%>">
            <input type="hidden" name="ejercicio" value="<%=e.getId()%>">
            <button name="<%= e.getNombre() %>" type="submit" class="btn btn-secondary" ><%= e.getNombre() %></button>
        </form>
        <%
            }
        %>
    </div>
</section>

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