<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<%
    String errorMsg = (String) request.getAttribute("error");
    if( errorMsg==null ) errorMsg="";
%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
        <%@ include file="login.css"%>
    </style>
    <title>Login</title>
</head>
<body>
    <header>
        <h1 class="header-text text-center">FitPro</h1>
    </header>
    <div class="form-wrapper">
        <form  method="post" action="/autenticar">
            <table>
                <tr>
                    <td><span>Usuario:</span></td>
                    <td><input type="text" name="mail"></td>
                </tr>
                <tr>
                    <td><span>Contraseña:</span></td>
                    <td> <input type="password" name="password"></td>
                </tr>
            </table>

            <button name="login" class="btn btn-secondary mt-1">Login</button> </br>
            <span> <%= errorMsg  %></span>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>