<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
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

        <tr onclick="window.location.href='/admin/users_cambiar?id=1'">
            <th>2</th>
            <td>David</td>
            <td>Garcia Sanchez</td>
            <td>admin</td>
        </tr>

        </tbody>
    </table>
    <form class="user-form">
        <table class="table-users table table-borderless">
            <tbody>
            <tr>
                <td>Nombre:<input type="text" placeholder="Nombre" value=David></td>
                <td>Apellidos:<input type="text" placeholder="Apellidos" value=Garcia Sanchez></td>
                <td>DNI:<input type="text" placeholder="DNI" value=25915268B></td>
                <td>Rol: <select >

                    <option value=1> admin</option>

                    <option value=2> entrenador_fuerza</option>

                    <option value=3> entrenador_cross_training</option>

                    <option value=4> dietista</option>

                    <option value=5> cliente</option>

                </select></td>
            </tr>
            <tr>
                <td>Sexo: <select>
                    <option value=1> Hombre </option>
                    <option value=0> Mujer </option>
                </select></td>
                <td>Edad:<input type="text" placeholder="Edad" value=19></td>
                <td>Altura:<input type="text" placeholder="Altura" value=1.9></td>
                <td>Peso:<input type="text" placeholder="Peso" value=87.0></td>
            </tr>
            <tr>
                <td></td>
                <td>Email:<input type="text" placeholder="Email" value=david@gmail.com></td>
                <td></td>
                <td>Contraseña:<input type="text" placeholder="Contraseña" value=david></td>
            </tr>
            </tbody>
        </table>
        <div class="form-buttons">
            <button>Guardar</button>
            <button onclick="">Borrar</button>
            <button onclick=>Limpiar</button>
        </div>
    </form>
</div>
</body>
</html>