<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastrar Usuário</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Cadastro de Usuário</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="UsuarioController" method="post">
				<input type="hidden" value="cadastrar" name="acao">	
			    <div class="form-group">
			        <label for="nome_usuario">Nome</label>
			        <input type="text" name="nome" id="nome_usuario" class="form-control">
			    </div>
			    <div class="form-group">
			        <label for="email_usuario">Email</label>
			        <input type="email" name="email" id="email_usuario" class="form-control">
			    </div>
			    <div class="form-group">
			        <label for="senha">Senha</label>
			        <input type="password" name="senha" id="senha" class="form-control">
			    </div>
			    <input type="submit" value="Salvar" class="btn btn-primary">
			</form>
		</div>
		
		<%@ include file="footer.jsp" %>
	</body>
	
</html>