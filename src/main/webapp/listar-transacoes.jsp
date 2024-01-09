<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listar Transações</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
	        <h1> Transações </h1>
	        <table class="table table-striped">
	            <tr>
					<th>ID Transação</th>
					<th>ID Conta</th>
					<th>Nome da Transação</th>
					<th>Valor da Transação</th>
					<th>Data de ocorrência</th>
					<th>Periodicidade</th>
					<th>Receita ou débito</th>
					<th>Código de Tipo</th>
					<th></th>
	            </tr>
	            <tbody>
	                <c:forEach items="${listaTransacoes}" var="transacao">
	                    <tr>
	                        <td>${transacao.id_transacao}</td>
							<td>${transacao.id_conta}</td>
							<td>${transacao.nome_transacao}</td>
							<td>${transacao.nr_valor}</td>
							    <td>
							    <tags:localDate date="${transacao.dt_ocorrencia}"/>
							</td>
							<td>${transacao.nr_periodicidade}</td>
							<td>${lancamento.cd_pagamento_ou_estorno ? 'Receita' : 'Débito'}</td>
							<td>${transacao.cd_tipo}</td>
							<td>
								<c:url value="TransacaoController" var="link">
									<c:param name="acao" value="iniciar-edicao"/>
									<c:param name="idTransacao" value="${transacao.id_transacao}"/>
								</c:url>
								<a class="btn btn-primary btn-xs" href="${link}">Editar</a>
								<button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#modalExcluir" onclick="codigoExcluir.value=${transacao.id_transacao	}">
									Excluir
								</button>
							</td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	    </div>
		<%@ include file="footer.jsp" %>
		
		<!-- Modal -->
		<div class="modal fade" id="modalExcluir" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar exclusão</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
							Deseja realmente excluir a transação?
					</div>
					<div class="modal-footer">
						<form action="TransacaoController" method="post">
							<input type="hidden" name="acao" value="excluir">
							<input type="hidden" name="idTransacao" id="codigoExcluir">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>