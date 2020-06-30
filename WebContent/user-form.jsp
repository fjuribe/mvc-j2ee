<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="es">
<head>
<title>CRUD</title>
 <meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${usuario != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${usuario == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${usuario != null}">
            			Editar usuario
            		</c:if>
						<c:if test="${usuario == null}">
            			Agregar nuevo usuario
            		</c:if>
					</h2>
				</caption>

				<c:if test="${usuario != null}">
					<input type="hidden" name="id" value="<c:out value='${usuario.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>usuario nombre</label> <input type="text"
						value="<c:out value='${usuario.nombre}' />" class="form-control"
						name="nombre" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>usuario Email</label> <input type="email"
						value="<c:out value='${usuario.email}' />" class="form-control"
						name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>usuario telefono</label> <input type="number"
						value="<c:out value='${usuario.telefono}' />" class="form-control"
						name="telefono">
				</fieldset>

				<button type="submit" class="btn btn-success">Guardar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
