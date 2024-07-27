
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Libros Candidatos</h2>

<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">Título</th>
        <th scope="col">Autor</th>
        <th scope="col">Votos</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="libro" items="${libros}">
        <tr>
            <td>${libro.titulo}</td>
            <td>${libro.autor}</td>
            <td id="voto${libro.nroMaterial}">${libro.votos}</td>
            <td>
                <button type="button" class="btn btn-primary" onclick="save(${libro.nroMaterial})">Votar</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>