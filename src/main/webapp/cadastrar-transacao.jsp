<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastrar Transação</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Cadastro de Transação</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="TransacaoController" method="post">
				<input type="hidden" value="cadastrar" name="acao">
	            <div class="form-group">
				    <label for="id_conta">ID Conta</label>
				    <input type="number" name="id_conta" id="id_conta" class="form-control">
				</div>
                <div class="form-group">
                    <label for="valor_transacao">Valor da Transação</label>
                    <input type="number" step="0.01" name="valor_transacao" id="valor_transacao" class="form-control">
                </div>
				<div class="form-group">
				    <label for="nome_transacao">Nome da Transação</label>
				    <input type="text" name="nome_transacao" id="nome_transacao" class="form-control">
				</div>
				<div class="form-group">
                    <label for="data_ocorrencia">Data de Ocorrência</label>
                    <input type="date" name="data_ocorrencia" id="data_ocorrencia" class="form-control">
                </div>
				<div class="form-group">
				    <label for="periodicidade">Periodicidade (ocorrências por mês)</label>
				    <input type="number" name="periodicidade" id="periodicidade" class="form-control">
				</div>
				<div class="form-group">
				    <label>Receita ou Débito</label>
                    <div>
				        <input type="radio" name="receita_ou_debito" id="active_true" value="true">
				        <label for="active_true">Receita</label>
				    </div>
				    <div>
				        <input type="radio" name="receita_ou_debito" id="active_false" value="false">
				        <label for="active_false">Débito</label>
				    </div>
				</div>
				<div class="form-group">
				    <label for="cd_tipo">Código do Tipo de Transação</label>
				    <input type="number" name="cd_tipo" id="cd_tipo" class="form-control">
				</div>
	            <input type="submit" value="Salvar" class="btn btn-primary">
        	</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
	
</html>