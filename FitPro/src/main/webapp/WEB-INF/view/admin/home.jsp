<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
    <header>
        <img class="back-button ms-1 mt-1 " src="../assets/back_button.png" alt="LogOut" onclick="window.location.href = '/'">
        <h1 class="header-text text-center">Admin Home</h1>
    </header>
    <div class="form-wrapper">
        <form method="post" action="/admin/users?id=0">
            <button type="submit" class="btn btn-primary">Users</button>
        </form>
        <form method="post" action="/admin/exercises?id=0">
            <button type="submit" class="btn btn-primary">Exercises</button>
        </form>
        <form method="post" action="/admin/food?id=0">
            <button type="submit" class="btn btn-primary">Food</button>
        </form>
        <form method="post" action="/admin/assignment?id=0">
            <button type="submit" class="btn btn-primary">Assignment</button>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>