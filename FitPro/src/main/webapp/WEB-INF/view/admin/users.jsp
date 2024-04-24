<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="uma.fitpro.entity.Rol" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
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
            window.location.href = "/admin/users?id="+id;
        }
    </script>
</head>
<body>
<header>
    <img class="back-button ms-1 mt-1 " src="./assets/image.png" alt="">
    <h1 class="header-text text-center">Usuarios</h1>
</header>
<div class="user-wrapper">
    <table class="table-users table table-striped table-dark table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Rol</th>
        </tr>
        </thead>
        <tbody>
        <% for(Usuario u : usuarios){ %>
            <tr onclick=rellenarDatos(<%=u.getId()%>)>
                <td><%=u.getId()%></td>
                <td><%=u.getNombre()%></td>
                <td><%=u.getApellidos()%></td>
                <td><%=u.getRol().getNombre()%></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <form class="user-form">
        <table class="table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input type="text" placeholder="Nombre" value=<%=usuario == null ? "" : usuario.getNombre()%>></td>
                <td>Apellidos:<input type="text" placeholder="Apellidos" value=<%=usuario == null ? "" : usuario.getApellidos()%>></td>
                <td>DNI:<input type="text" placeholder="DNI" value=<%=usuario == null ? "" : usuario.getDni()%>></td>
                <td>Rol: <select>
                    <% for(Rol rol : roles){ %>
                        <option <%=usuario != null && usuario.getRol().getId() == rol.getId() ? "selected" : ""%> value=<%=rol.getId()%>> <%=rol.getNombre()%> </option>
                    <% } %>
                </select></td>
            </tr>
            <tr>
                <td>Sexo:<br> <select>
                    <option <%=usuario != null && usuario.getSexo() == 1 ? "selected" : ""%> value=1> Hombre </option>
                    <option <%=usuario != null && usuario.getSexo() == 0 ? "selected" : ""%> value=0> Mujer </option>
                </select></td>
                <td>Edad:<input type="text" placeholder="Edad" value=<%=usuario == null ? "" : usuario.getEdad()%>></td>
                <td>Altura:<input type="text" placeholder="Altura" value=<%=usuario == null ? "" : usuario.getAltura()%>></td>
                <td>Peso:<input type="text" placeholder="Peso" value=<%=usuario == null ? "" : usuario.getPeso()%>></td>
            </tr>
            <tr>
                <td>Email:<input type="text" placeholder="Email" value=<%=usuario == null ? "" : usuario.getCorreo()%>></td>
                <td>Contraseña:<input type="text" placeholder="Contraseña" value=<%=usuario == null ? "" : usuario.getContrasenya()%>></td>
            </tr>
            </tbody>
        </table>
        <div class="form-buttons">
            <form method="post" action="/admin/agregar-users">
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
            <form method="post" action="/admin/elimina-users?id=0">
                <button <%= usuario == null ? "" : "disabled" %> type="submit" class="btn btn-primary">Eliminar</button>
            </form>
            <form method="post" action="/admin/users?id=0">
                <button type="submit" class="btn btn-primary">Limpiar</button>
            </form>
        </div>
    </form>
</div>
</body>
</html>