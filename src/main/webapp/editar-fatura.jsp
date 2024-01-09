<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edição de fatura</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Editar Fatura de Cartão</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="FaturaController" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${fatura.id_fatura}" name="id_fatura">
	            <div class="form-group">
				    <label for="id_conta">ID Conta Bancária</label>
				    <input type="number" name="id_conta" id="id_conta" class="form-control" value="${fatura.id_conta}">
				</div>
				<div class="form-group">
				    <label for="valor_fatura">Valor da Fatura</label>
				    <input type="number" step="0.01" name="valor_fatura" id="valor_fatura" class="form-control" value="${fatura.nr_valor_fatura}">
				</div>
				<div class="form-group">
				    <label for="data_fatura">Mês de referência da fatura</label>
				    <input type="date" name="data_fatura" id="data_fatura" class="form-control" value="${fatura.dt_fatura}">
				</div>
				<div class="form-group">
				    <label for="data_fechamento">Data de Fechamento da Fatura</label>
				    <input type="date" name="data_fechamento" id="data_fechamento" class="form-control" value="${fatura.dt_fechamento_fatura}">
				</div>
	            <input type="submit" value="Salvar" class="btn btn-primary">
	            <a href="FaturaController?acao=listar" class="btn btn-danger">Cancelar</a>
        	</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
	
</html>