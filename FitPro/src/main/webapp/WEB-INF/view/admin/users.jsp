<%@ page import="java.util.List" %>
<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="uma.fitpro.entity.Rol" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
    List<Rol> roles = (List<Rol>) request.getAttribute("roles");
    Usuario usuario = (Usuario) request.getAttribute("usuario");

    String filtroNombre = (String) request.getAttribute("filtroNombre");
    String filtroApellido = (String) request.getAttribute("filtroApellido");
    String filtroRol = (String) request.getAttribute("filtroRol");
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
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="back" onclick="window.location.href = '/home'">
    <h1 class="header-text text-center">Usuarios</h1>
</header>
<div class="user-wrapper">
    <table id="users" class="table-users table caption-top text-center table-hover ">
        <caption class="text-center text-white">Lista de usuarios</caption>
        <thead class="table-dark">
        <tr>
            <form method="post" action=/admin/user/filter>
                <th scope="col"><button name="filter" type="submit">🔍</button></th>
                <th scope="col"><input value="<%=filtroNombre%>" name="nombre" type="text" placeholder="Nombre"></th>
                <th scope="col"><input value="<%=filtroApellido%>" name="apellido" type="text" placeholder="Apellidos"></th>
                <th scope="col"><input value="<%=filtroRol%>" name="rol" type="text" placeholder="Rol"></th>
            </form>
        </tr>
        </thead>
        <tbody>
        <%  int i = 0;
            for(Usuario u : usuarios) { %>
            <tr role="button" name="<%="user"+i%>" class="<%= usuario!=null && u.getId() == usuario.getId() ? "selected-row" : ""%>" onclick=rellenarDatos(<%=u.getId()%>)>
                <td><%=u.getId()%></td>
                <td><%=u.getNombre()%></td>
                <td name="apellido"><%=u.getApellidos()%></td>
                <td><%=u.getRol().getNombre()%></td>
            </tr>
        <% i++;} %>
        </tbody>
    </table>
    <form class="user-form" method="post" action="/admin/add-users">
        <input name="Id" type="hidden" value=<%=usuario == null ? "0" : usuario.getId()%>>
        <table class="table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input name="Nombre" type="text" placeholder="Nombre" value="<%=usuario == null ? "" : usuario.getNombre()%>"></td>
                <td>Apellidos:<input name="Apellidos" type="text" placeholder="Apellidos" value="<%=usuario == null ? "" : usuario.getApellidos()%>"></td>
                <td>DNI:<input name="DNI" type="text"  placeholder="DNI" value=<%=usuario == null ? "" : usuario.getDni()%>></td>
                <td>Rol: <select name="Rol">
                    <% for(Rol rol : roles){ %>
                        <option <%=usuario != null && usuario.getRol().getId() == rol.getId() ? "selected" : ""%> value=<%=rol.getId()%>> <%=rol.getNombre()%> </option>
                    <% } %>
                </select></td>
            </tr>
            <tr>
                <td>Sexo:<br> <select name="Sexo">
                    <option <%=usuario != null && usuario.getSexo() == 1 ? "selected" : ""%> value=1> Hombre </option>
                    <option <%=usuario != null && usuario.getSexo() == 0 ? "selected" : ""%> value=0> Mujer </option>
                </select></td>
                <td>Edad:<input name="Edad" type="number" placeholder="Edad" value=<%=usuario == null ? "" : usuario.getEdad()%>></td>
                <td>Altura:<input name="Altura" type="number" placeholder="Altura" value=<%=usuario == null ? "" : usuario.getAltura()%>></td>
                <td>Peso:<input name="Peso" type="number" placeholder="Peso" value=<%=usuario == null ? "" : usuario.getPeso()%>></td>
            </tr>
            <tr>
                <td>Email:<input name="Email" type="text" placeholder="Email" value=<%=usuario == null ? "" : usuario.getCorreo()%>></td>
                <td>Contraseña:<input name="Contrasenya" type="text" placeholder="Contraseña" value=<%=usuario == null ? "" : usuario.getContrasenya()%>></td>
            </tr>
            </tbody>
        </table>
        <button type="submit" name="save-user" class="btn btn-primary">Guardar</button>
    </form>
    <div class="form-buttons">
        <form method="post" action="/admin/delete-user">
            <input name="Id" type="hidden" value=<%=usuario == null ? "0" : usuario.getId()%>>
            <button name="delete-user" <%= usuario != null ? "" : "disabled" %> type="submit" class="btn btn-primary user-delete-button">Eliminar</button>
        </form>
        <button type="submit" class="btn btn-primary user-clean-button" onclick="rellenarDatos(0)">Limpiar</button>
    </div>
</div>
</body>
</html>