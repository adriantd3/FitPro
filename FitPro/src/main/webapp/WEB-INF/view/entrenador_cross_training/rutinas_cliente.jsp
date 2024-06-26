<%// AUTOR: Ezequiel Sánchez García (100%)%>
<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="uma.fitpro.dto.RutinaDTO" %>
<%@ page import="uma.fitpro.dto.UsuarioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutinas");
    List<RutinaDTO> todasLasRutinas = (List<RutinaDTO>) request.getAttribute("todasLasRutinas");
    UsuarioDTO cliente = (UsuarioDTO) request.getAttribute("cliente");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rutinas de <%=cliente.getNombre()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">
    <style><%@include file="css/common.css"%></style>
</head>
<body>
<header>
    <a href="/entrenador_cross_training/clientes">
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back.png" alt="" onclick="">
    </a>
    <h1 class="header-text text-center"><%= cliente!= null ? "Rutinas de " + cliente.getNombre() : "Rutinas"%></h1>
</header>
<section class="scrollable-section">
    <% if (rutinas.isEmpty()) { %>
    <section class="table-container">
        <section class="mensaje-alerta"><h2><%=cliente.getNombre()%> no tiene ninguna rutina asignada</h2></section>
    </section>
    <% }else { %>
    <section class="table-container">
        <table class="table table-striped table-dark table-width">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Rutina</th>
                <th scope="col">Fecha</th>
                <th scope="col">Seguimiento</th>
                <th scope="col">Borrar</th>
            </tr>
            </thead>
            <tbody>
            <%
                int num = 1;
                for (RutinaDTO r : rutinas){
            %>
            <tr>
                <th scope="row"><%= num %></th>
                <td name="nombre"><%= r.getNombre() %></td>
                <td><%=r.getFechaCreacion()%></td>
                <td>
                    <button class="btn btn-info button-image" onclick="window.location.href='/entrenador_cross_training/seguimiento_cliente?cliente=<%=cliente.getId()%>&rutina=<%=r.getId()%>'">
                        <img class="image-button" src="${pageContext.request.contextPath}/assets/seguimiento.svg">
                    </button>
                </td>
                <td>
                    <form action="/entrenador_cross_training/borrar_rutina_cliente" method="post">
                        <input type="hidden" name="rutina" value="<%=r.getId()%>">
                        <input type="hidden" name="cliente" value="<%=cliente.getId()%>">
                        <button type="submit" class="btn btn-danger button-image">
                            <img class="image-button" src="${pageContext.request.contextPath}/assets/delete_button.svg">
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
    <% } %>
</section>

<div class="sesion-buttons">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#asignarRutina">
        Añadir rutina
    </button>
</div>


<div class="modal fade" id="asignarRutina" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div style="background-color: #696767;color: white" class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Asignar nueva rutina</h5>
            </div>
            <div class="modal-body">
                <form method="post" action="/entrenador_cross_training/asignar_rutina_cliente">
                    <%
                        String color = "light";
                        if (todasLasRutinas.size() == 0) {
                            color = "warning";
                        }
                    %>
                    Rutina a asignar:
                    <select class="selectpicker" data-live-search="true" data-style="btn-<%=color%>" name="rutina">
                        <%
                            if (todasLasRutinas.size() == 0){

                        %>
                            <option>No hay rutinas para asignar</option>
                        <%
                            }
                        %>
                        <%
                            for (RutinaDTO r : todasLasRutinas){
                        %>
                        <option value="<%=r.getId()%>"><%=r.getNombre()%> - <%=r.getFechaCreacion()%></option>
                        <%
                            }
                        %>
                    </select>
                    <input type="hidden" name="cliente" value="<%=cliente.getId()%>">

                    <div style="display: flex; justify-content: flex-end; margin-top: 20px">
                        <button type="submit" <%=todasLasRutinas.size()==0 ? "disabled" : ""%> class="btn btn-success">Asignar rutina</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>
</body>
</html>