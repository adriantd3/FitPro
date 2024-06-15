<%@ page import="uma.fitpro.entity.Usuario" %>
<%@ page import="uma.fitpro.entity.DesempenyoSesion" %><%--
  Created by IntelliJ IDEA.
  User: victor
  Date: 15/6/24
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario cliente = (Usuario) session.getAttribute("cliente");
%>
<html>
<head>
    <title>Seguimiento de <%=cliente.getNombre() + " " + cliente.getApellidos()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style><%@ include file="../styles/common.css"%></style>
</head>
<body>
    <header>
        <img class="back-button ms-1 mt-1 " src="${pageContext.request.contextPath}/assets/back_button.png" alt="<-"
             onclick="window.location.href='/entrenador_fuerza/crud-rutina?cliente=<%=cliente.getId()%>'">
        <h1 class="header-text text-center">Seguimiento de <%=cliente.getNombre() + " " + cliente.getApellidos()%></h1> <!-- Controlar si es de un usario para añadir "de usuario" y solo sus listas -->
    </header>
    <section class="mt-3 ms-3 h-100">
        <ul class="list-group m-3">
            <%
                for(DesempenyoSesion desempenyoSesion : cliente.getDesempenyoSesions()){
                    String disabled = "";
                    if(desempenyoSesion.getTerminado() != 1){
                        disabled = "disabled";
                    }
            %>
            <button <%=disabled%> onclick="window.location.href='/entrenador_fuerza/desempenyos-sesion/<%=desempenyoSesion.getId()%>'" class="list-button list-group-item">
                <%=desempenyoSesion.getSesion().getNombre() + " " + desempenyoSesion.getFecha()%>
            </button>
            <%
                }
            %>
        </ul>
    </section>
</body>
</html>
