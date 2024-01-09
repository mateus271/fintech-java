<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Editar Transação</title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
			<h1>Edição de Transação</h1>
			<c:if test="${ not empty msg }">
				<div class="alert alert-success">${msg}</div>
			</c:if>
			<c:if test="${ not empty erro }">
				<div class="alert alert-danger">${erro}</div>
			</c:if>
			<form action="TransacaoController" method="post">
				<input type="hidden" value="editar" name="acao">
				<input type="hidden" value="${ transacao.id_transacao }" name="id_transacao">
	            <div class="form-group">
				    <label for="id_conta">ID Conta</label>
				    <input type="number" name="id_conta" id="id_conta" class="form-control" value="${transacao.id_conta}">
				</div>
                <div class="form-group">
                    <label for="valor_transacao">Valor da Transação</label>
                    <input type="number" step="0.01" name="valor_transacao" id="valor_transacao" class="form-control" value="${transacao.nr_valor}">
                </div>
				<div class="form-group">
				    <label for="nome_transacao">Nome da Transação</label>
				    <input type="text" name="nome_transacao" id="nome_transacao" class="form-control" value="${transacao.nome_transacao}">
				</div>
				<div class="form-group">
                    <label for="data_ocorrencia">Data de Ocorrência</label>
                    <input type="date" name="data_ocorrencia" id="data_ocorrencia" class="form-control" value="${transacao.dt_ocorrencia}" >
                </div>
				<div class="form-group">
				    <label for="periodicidade">Periodicidade (ocorrências por mês)</label>
				    <input type="number" name="periodicidade" id="periodicidade" class="form-control" value="${transacao.nr_periodicidade}">
				</div>
				<div class="form-group">
				    <label>Receita ou Débito</label>
                    <div>
				        <input type="radio" name="receita_ou_debito" id="active_true" value="true" ${lancamento.cd_pagamento_ou_estorno ? 'checked' : ''}>
				        <label for="active_true">Receita</label>
				    </div>
				    <div>
				        <input type="radio" name="receita_ou_debito" id="active_false" value="false" ${!lancamento.cd_pagamento_ou_estorno ? 'checked' : ''}>
				        <label for="active_false">Débito</label>
				    </div>
				</div>
				<div class="form-group">
				    <label for="cd_tipo">Código do Tipo de Transação</label>
				    <input type="number" name="cd_tipo" id="cd_tipo" class="form-control" value="${transacao.cd_tipo}">
				</div>
	            <input type="submit" value="Salvar" class="btn btn-primary">
	            <a href="TransacaoController?acao=listar" class="btn btn-danger">Cancelar</a>
        	</form>
		</div>
		<%@ include file="footer.jsp" %>
	</body>
	
</html>