<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    List<GrupoMuscular> grupos = (List<GrupoMuscular>) request.getAttribute("grupos");
    List<TipoEjercicio> tipos = (List<TipoEjercicio>) request.getAttribute("tipos");
    Ejercicio ejercicio = (Ejercicio) request.getAttribute("ejercicio");

    String filtroNombre = (String) request.getAttribute("filtroNombre");
    String filtroTipo = (String) request.getAttribute("filtroTipo");
    String filtroGrupoMuscular = (String) request.getAttribute("filtroGrupoMuscular");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
    <style><%@ include file="./admin.css"%></style>
    <script>
        function rellenarDatos(id) {
            window.location.href = "/admin/exercises?id="+id;
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="../assets/back_button.png" alt="Back" onclick="window.location.href = '/home'">
    <h1 class="header-text text-center">Ejercicios</h1>
</header>
<div class="user-wrapper">
    <table class="table-exercises table caption-top text-center">
        <caption class="text-center text-white">Lista de ejercicios</caption>
        <thead class="table-dark">
        <tr>
            <form method="post" action=/admin/exercise/filter>
                <th scope="col"><button type="submit">🔍</button></th>
                <th scope="col"><input value="<%=filtroNombre%>" name="nombre" type="text" placeholder="Nombre"></th>
                <th scope="col"><input value="<%=filtroTipo%>" name="tipo" type="text" placeholder="Tipo"></th>
                <th scope="col"><input value="<%=filtroGrupoMuscular%>" name="grupoMuscular" type="text" placeholder="Grupo Muscular"></th>
            </form>
        </tr>
        </thead>
        <tbody>
        <% for(Ejercicio e : ejercicios){ %>
        <tr role="button" class="<%= ejercicio!=null && e.getId() == ejercicio.getId() ? "selected-row" : ""%>" onclick=rellenarDatos(<%=e.getId()%>)>
            <td><%=e.getId()%></td>
            <td><%=e.getNombre()%></td>
            <td><%=e.getTipo().getTipo()%></td>
            <td><%=e.getGrupoMuscular().getGrupoMuscular()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <form class="user-form" method="post" action="/admin/add-exercise">
        <input name="Id" type="hidden" value=<%=ejercicio == null ? "0" : ejercicio.getId()%>>
        <table class="table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input name="Nombre" type="text" placeholder="Nombre" value="<%=ejercicio == null ? "" : ejercicio.getNombre()%>"></td>
                <td>Imagen:<input name="Imagen" type="text" placeholder="Imagen" value="<%=ejercicio == null || ejercicio.getImagen() == null ? "" : ejercicio.getImagen()%>"></td>
                <td>Vídeo:<input name="Video" type="text"  placeholder="Vídeo" value="<%=ejercicio == null || ejercicio.getVideo() == null ? "" : ejercicio.getVideo()%>"></td>
            </tr>
            <tr>
                <td>Tipo: <select name="Tipo">
                    <% for(TipoEjercicio tipoEjercicio : tipos){ %>
                    <option <%=ejercicio != null && ejercicio.getTipo().getId() == tipoEjercicio.getId() ? "selected" : ""%> value=<%=tipoEjercicio.getId()%>> <%=tipoEjercicio.getTipo()%> </option>
                    <% } %>
                </select></td>
                <td>Grupo Mucular: <select name="Grupo">
                    <% for(GrupoMuscular grupoMuscular : grupos){ %>
                    <option <%=ejercicio != null && ejercicio.getGrupoMuscular().getId() == grupoMuscular.getId() ? "selected" : ""%> value=<%=grupoMuscular.getId()%>> <%=grupoMuscular.getGrupoMuscular()%> </option>
                    <% } %>
                </select></td>
                <td>Descripción:<textarea name="Descripcion" placeholder="Descripción"> <%=ejercicio == null || ejercicio.getDescripcion() == null ? "" : ejercicio.getDescripcion()%> </textarea></td>
            </tr>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
    <div class="form-buttons">
        <form method="post" action="/admin/delete-exercise">
            <input name="Id" type="hidden" value=<%=ejercicio == null ? "0" : ejercicio.getId()%>>
            <button <%= ejercicio != null ? "" : "disabled" %> type="submit" class="btn btn-primary exercise-delete-button">Eliminar</button>
        </form>
        <button type="submit" class="btn btn-primary exercise-clean-button" onclick="rellenarDatos(0)">Limpiar</button>
    </div>
</div>
</body>
</html>