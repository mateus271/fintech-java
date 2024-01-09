<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edição de Conta Bancária</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Edição de Conta Bancária</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="ContaController" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${ conta.id_conta }" name="id_conta">
	            <div class="form-group">
				    <label for="id_usuario">ID Usuário</label>
				    <input type="number" name="id_usuario" id="id_usuario" class="form-control" value="${ conta.id_usuario }">
				</div>
				<div class="form-group">
				    <label for="cd_banco">Código Banco</label>
				    <input type="number" name="cd_banco" id="cd_banco" class="form-control" value="${ conta.cd_banco }">
				</div>
				<div class="form-group">
				    <label for="valor_saldo">Saldo</label>
				    <input type="number" step="0.01" name="valor_saldo" id="valor_saldo" class="form-control" value="${ conta.nr_valor_saldo }">
				</div>
				<div class="form-group">
				    <label for="cd_tipo_conta">Código Tipo Conta</label>
				    <input type="number" name="cd_tipo_conta" id="cd_tipo_conta" class="form-control" value="${ conta.cd_tipo_conta }">
				</div>
				<div class="form-group">
				    <label for="limite_credito">Limite de Crédito</label>
				    <input type="number" step="0.01" name="limite_credito" id="limite_credito" class="form-control" value="${ conta.nr_limite_credito }">
				</div>
	            <input type="submit" value="Salvar" class="btn btn-primary">
	            <a href="ContaController?acao=listar" class="btn btn-danger">Cancelar</a>
        	</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
	
</html>