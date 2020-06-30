<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="es">
<head>
<title>CRUD</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">



<!-- este no es obligatorio , ya que importamos el CDN de bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">MI primer CRUD</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" href="<%=request.getContextPath()%>/list"
							class="nav-link">Usuarios</a>
						</li>

						<!-- <li class="nav-item">
							<a class="nav-link" href="#">Features</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">Pricing</a>
						</li> -->
					</ul>
				</div>
			</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Lista de usuarios</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Agregar usuario</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Email</th>
						<th>telefono</th>
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<!-- aca se recorre la lista usuario con la ayuda de la libreria jstl que impotamos en WEB-INF/lib -->
					<c:forEach var="usuario" items="${listaUsuarios}">

						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.nombre}" /></td>
							<td><c:out value="${usuario.email}" /></td>
							<td><c:out value="${usuario.telefono}" /></td>
							<td><a class="btn btn-warning" href="edit?id=<c:out value='${usuario.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								 <a class="btn btn-danger" onclick="return confirm('¿Esta seguro de eliminar el registro?')" href="delete?id=<c:out value='${usuario.id}' />">Borrar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
