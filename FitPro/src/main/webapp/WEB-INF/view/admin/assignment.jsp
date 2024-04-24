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
<div class="container-fluid" >
    <div class="row">
        <div class="col-lg-5 ps-5 pt-5">
            <section id="Menus" class="menu-table pt-2">
                <table class="table caption-top text-center ">
                    <caption class="text-center text-white">List of users</caption>
                    <thead class="table-dark">
                    <tr>
                        <th class="id"></th>
                        <th class="nombre-menu">Nombre</th>
                        <th class="kcal">Kcal</th>
                    </tr>
                    </thead>
                    <tbody class = "table-group-divider table-secondary">
                    <%
                        for(User m : menus){
                    %>

                    <tr>
                        <td><%= m.getId() %></td>
                        <td><%= m.getNombre() %></td>
                        <td><%= m.getCalorias() %></td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </section>
    </div>
</div>
</body>
</html>