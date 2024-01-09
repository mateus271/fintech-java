<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastrar Lançamento</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Cadastro de Lançamento</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="LancamentoController" method="post">
				<input type="hidden" value="cadastrar" name="acao">
	            <div class="form-group">
				    <label for="id_fatura">ID Fatura</label>
				    <input type="number" name="id_fatura" id="id_fatura" class="form-control">
				</div>
				<div class="form-group">
				    <label for="nome_lancamento">Nome do Lançamento</label>
				    <input type="text" name="nome_lancamento" id="nome_lancamento" class="form-control">
				</div>
				<div class="form-group">
				    <label for="valor_lancamento">Valor do Lançamento</label>
				    <input type="number" step="0.01" name="valor_lancamento" id="valor_lancamento" class="form-control">
				</div>
				<div class="form-group">
				    <label for="periodicidade">Periodicidade (ocorrências por mês)</label>
				    <input type="number" name="periodicidade" id="periodicidade" class="form-control">
				</div>
				<div class="form-group">
				    <label>Pagamento ou Estorno</label>
				    <div>
				        <input type="radio" name="pagamento_ou_estorno" id="active_false" value="false">
				        <label for="active_false">Pagamento (Débito)</label>
				    </div>
				    <div>
				        <input type="radio" name="pagamento_ou_estorno" id="active_true" value="true">
				        <label for="active_true">Estorno (Crédito)</label>
				    </div>
				</div>
				<div class="form-group">
				    <label for="data_ocorrencia">Data de Ocorrência</label>
				    <input type="date" name="data_ocorrencia" id="data_ocorrencia" class="form-control">
				</div>
				<div class="form-group">
				    <label for="cd_tipo">Código do Tipo de Lançamento</label>
				    <input type="number" name="cd_tipo" id="cd_tipo" class="form-control">
				</div>
	            <input type="submit" value="Salvar" class="btn btn-primary">
        	</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
	
</html>