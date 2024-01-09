<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Faça login no Fintech</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form class="form-inline my-2 my-lg-0" action="LoginController" method="post">
				<input class="form-control mr-sm-2" type="text" name="email" placeholder="E-mail">
				<input class="form-control mr-sm-2" type="password" name="senha" placeholder="Senha">
				<button class="btn btn-outline-success my-2 mysm-0" type="submit">Entrar</button>
			</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
</html>