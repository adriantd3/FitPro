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
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
        <h1 class="header-text text-center">FitPro</h1>
    </header>
    <div class="form-wrapper">
        <form  method="post" action="/home">
            <span>Usuario:</span>
            <input type="text" name="mail"> </br>
            <span>Contraseña:</span>
            <input type="text" name="password"> </br>
            <button name="login">Login</button> </br>
            <span> <%= errorMsg  %></span>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>