<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style> <%@ include file="styles/common.css"%></style>
</head>
<body>
    <header>
        <img hidden class="back-button ms-1 mt-1 " src="assets/back_button.png" alt="">
        <h1 class="header-text text-center">Fit Pro</h1>
    </header>
    <form method="post" action="/entrenadorfuerza-home">

        Usuario: <input type="text" name="user"> </br>
        Contraseña: <input type="text" name="password"> </br>
        <button>Login</button>
    </form>
</body>
</html>