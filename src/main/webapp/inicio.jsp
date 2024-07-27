<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>UBP - Votación de Libros</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="./style/style.css">
  <script type="text/javascript" src="./js/scripts.js"></script>
</head>
<body>
<div class="container mt-3">
  <h1>Votación de Libros</h1>

  <div id="iError" class="d-none alert alert-danger"></div>

  <form id="iForm" method="post">
    <div class="mb-3">
      <label for="email" class="form-label">Correo *</label>
      <input type="email" class="form-control" id="email" name="email" placeholder="Ingresa tu correo" required>
    </div>
    <div class="mb-3">
      <label for="telefono" class="form-label">Teléfono *</label>
      <input type="text" class="form-control" id="telefono" name="telefono" placeholder="Ingresa tu teléfono" required>
    </div>
    <div class="mb-3">
      <label for="legajo" class="form-label">Legajo</label>
      <input type="number" class="form-control" id="legajo" name="legajo" placeholder="Ingresa tu legajo">
    </div>
    <div class="mb-3">
      <label for="genero" class="form-label">Género Literario *</label>
      <select class="form-select" id="genero" name="genero" required onchange="buscar()">
        <option selected value="">Selecciona un género</option>
        <c:forEach var="genero" items="${generos}">
          <option value="${genero.id}">${genero.nombre}</option>
        </c:forEach>

      </select>
    </div>
  </form>

  <div id="iList" class="mt-3"></div>

</div>

</body>
</html>
