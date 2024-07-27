<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>UBP - Votación de Libros</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h1 class="mb-4">Votación de Libros</h1>
  <form>
    <div class="form-group">
      <label for="email">Correo *</label>
      <input type="email" class="form-control" id="email" placeholder="Ingresa tu correo">
    </div>
    <div class="form-group">
      <label for="telefono">Teléfono *</label>
      <input type="tel" class="form-control" id="telefono" placeholder="Ingresa tu teléfono">
    </div>
    <div class="form-group">
      <label for="legajo">Legajo</label>
      <input type="text" class="form-control" id="legajo" placeholder="Ingresa tu legajo">
    </div>
    <div class="form-group">
      <label for="genero">Géneros Literarios *</label>
      <select class="form-control" id="genero">
        <option selected>Selecciona un género</option>
        <option value="1">Ficción</option>
        <option value="2">No Ficción</option>
        <option value="3">Ciencia Ficción</option>
        <option value="4">Fantasía</option>
        <option value="5">Misterio</option>
        <option value="6">Biografía</option>
        <option value="7">Historia</option>
      </select>
    </div>
  </form>
  <h2 class="mt-5">Libros Candidatos</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Título</th>
      <th>Autor</th>
      <th>Votos</th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Breve Historia del Tiempo</td>
      <td>Stephen Hawking</td>
      <td>3</td>
      <td><button class="btn btn-primary">Votar</button></td>
    </tr>
    <tr>
      <td>Los Pilares de la Tierra</td>
      <td>Ken Follett</td>
      <td>2</td>
      <td><button class="btn btn-primary">Votar</button></td>
    </tr>
    </tbody>
  </table>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
