<%@ page import="uma.fitpro.entity.Ejercicio" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Ejercicio ejercicio = (Ejercicio) request.getAttribute("ejercicio");
%>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        <%@ include file="../styles/common.css"%>
    </style>
    <title>Ejercicio</title>
</head>
<body style="color: white">
<header>
    <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="">
    <h1 class="header-text text-center">Información ejercicio: <%=ejercicio.getNombre()%></h1>
</header>
<div class="p-3">
    <p><b><u>Nombre:</u></b> <%=ejercicio.getNombre()%></p>

    <p><b><u>Descripción:</u></b></p>
    <textarea style="width: 500px" rows="3" readonly><%=ejercicio.getDescripcion()%></textarea>

    <p><b><u>Tipo:</u></b> <%=ejercicio.getTipo().getTipo()%></p>
    <p><b><u>Grupo muscular:</u></b> <%=ejercicio.getGrupoMuscular().getGrupoMuscular()%></p>

    <%
        if(ejercicio.getImagen()!=null){
    %>
        <p><b><u>Imagen de referencia:</u></b></p>
        <img src="<%=ejercicio.getImagen()%>" width="700" height="394">
    <%
        }
        if(ejercicio.getVideo() != null){

    %>
        <p><b><u>Video demostración:</u></b></p>
        <iframe width="700" height="394" src="<%=ejercicio.getVideo()%>"></iframe>
    <%
        }
    %>
</div>

</body>
</html>
