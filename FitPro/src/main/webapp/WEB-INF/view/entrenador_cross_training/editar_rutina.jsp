<%// AUTOR: Ezequiel Sánchez García (100%)%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.SesionDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    Map<Integer, SesionDTO> diaSesion = (Map<Integer, SesionDTO>) request.getAttribute("diaSesion");
    Map<Integer, String> diasSemana = (Map<Integer, String>) request.getAttribute("diasSemana");
    List<SesionDTO> sesiones = (List<SesionDTO>) request.getAttribute("sesiones");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Editar rutina - <%=rutina.getNombre()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/rutinas">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center"><%=rutina.getNombre()%></h1>
</header>
<section class="scrollable-section">
    <section class="table-container">
        <table class="table table-striped table-dark table-width">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Día de la semana</th>
                <th scope="col">Sesión asociada</th>
            </tr>
            </thead>
            <tbody>
            <%
                int num = 1;
                for (Integer dia : diasSemana.keySet()){

            %>
            <tr>
                <th scope="row"><%=num%></th>
                <td><%= diasSemana.get(dia)%></td>
                <td>
                    <form method="post" action="/entrenador_cross_training/asociar_dia_sesion" class="form-editar-rutina">
                        <%
                            String color = "primary";
                            if (diaSesion.get(dia) == null){
                                color = "warning";
                            }
                        %>
                        <select class="selectpicker" data-live-search="true" data-style="btn-<%=color%>" name="nueva_sesion">
                            <option selected value=-1><h6>Sin sesión asignada</h6></option>
                            <%
                                String selected = "";
                                for (SesionDTO sesion : sesiones){
                                    if (diaSesion.get(dia) !=null){
                                        if (diaSesion.get(dia).equals(sesion)){
                                            selected = "selected";
                                        }else {
                                            selected = "";
                                        }
                                    }

                            %>
                            <option value=<%=sesion.getId()%> <%=selected%>><%=sesion.getNombre()%></option>
                            <%
                                }
                            %>
                        </select>
                        <input type="hidden" name="dia" value=<%=dia%>>
                        <input type="hidden" name="rutina" value=<%=rutina.getId()%>>
                        <input type="hidden" name="antigua_sesion" value=<%= diaSesion.get(dia) !=null ? diaSesion.get(dia).getId() : -1%>>
                        <button type="submit" class="btn btn-success button-image">
                            <img class="image-button" src="${pageContext.request.contextPath}/assets/tick.svg">
                        </button>
                    </form>
                </td>
            </tr>
            <%
                    num++;
                }
            %>

            </tbody>
        </table>
    </section>
</section>

<div class="sesion-buttons">
    <form method="post" action="/entrenador_cross_training/borrar_rutina">
        <input type="hidden" name="id" value="<%=rutina.getId()%>">
        <button class="btn btn-danger">Borrar rutina</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>
</body>
</html>