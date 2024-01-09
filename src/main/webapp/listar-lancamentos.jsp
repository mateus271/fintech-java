<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listar Lançamentos</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
	        <h1> Lançamentos </h1>
	        <table class="table table-striped">
	            <tr>
					<th>ID Lançamento</th>
					<th>ID Fatura</th>
					<th>Nome Lançamento</th>
					<th>Valor Lançamento</th>
					<th>Periodicidade</th>
					<th>Pagamento ou Estorno</th>
					<th>Data de Ocorrência</th>
					<th>Código de Tipo</th>
					<th></th>
	            </tr>
	            <tbody>
	                <c:forEach items="${listaLancamentos}" var="lancamento">
	                    <tr>
	                        <td>${lancamento.id_lancamento}</td>
                            <td>${lancamento.id_fatura}</td>
                            <td>${lancamento.nome_lancamento}</td>
                            <td>${lancamento.nr_valor_lancamento}</td>
                            <td>${lancamento.ds_periodicidade}</td>
                            <td>${lancamento.cd_pagamento_ou_estorno ? 'Estorno (Crédito)' : 'Pagamento (Débito)'}</td>
                            <td>
                            	<tags:localDate date="${lancamento.dt_ocorrencia}"/>
							</td>
                            <td>${lancamento.cd_tipo}</td>
                            <td>
								<c:url value="LancamentoController" var="link">
									<c:param name="acao" value="iniciar-edicao"/>
									<c:param name="idLancamento" value="${lancamento.id_lancamento}"/>
								</c:url>
								<a class="btn btn-primary btn-xs" href="${link}">Editar</a>
								<button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#modalExcluir" onclick="codigoExcluir.value=${lancamento.id_lancamento}">
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
							Deseja realmente excluir o lançamento?
					</div>
					<div class="modal-footer">
						<form action="LancamentoController" method="post">
							<input type="hidden" name="acao" value="excluir">
							<input type="hidden" name="idLancamento" id="codigoExcluir">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
