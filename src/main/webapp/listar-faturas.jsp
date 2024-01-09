<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listar Faturas</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
	        <h1> Faturas </h1>
	        <table class="table table-striped">
	            <tr>
	                <th>ID da Fatura</th>
	                <th>ID da Conta</th>
	                <th>Valor da Fatura</th>
	                <th>Data da Fatura</th>
	                <th>Data de Fechamento da Fatura</th>
	                 <th></th>
	            </tr>
	            <tbody>
	                <c:forEach items="${listaFaturas}" var="fatura">
	                    <tr>
	                        <td>${fatura.id_fatura}</td>
							<td>${fatura.id_conta}</td>
							<td>${fatura.nr_valor_fatura}</td>
							<td>
								<tags:localDate date="${fatura.dt_fatura}"/>
							</td>
							<td>
								<tags:localDate date="${fatura.dt_fechamento_fatura}"/>
							</td>
							<td>
		                        <c:url value="FaturaController" var="link">
									<c:param name="acao" value="iniciar-edicao"/>
									<c:param name="idFatura" value="${fatura.id_fatura}"/>
								</c:url>
								<a class="btn btn-primary btn-xs" href="${link}">Editar</a>
								<button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#modalExcluir" onclick="codigoExcluir.value=${fatura.id_fatura}">
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
							Deseja realmente excluir a fatura?
					</div>
					<div class="modal-footer">
						<form action="FaturaController" method="post">
							<input type="hidden" name="acao" value="excluir">
							<input type="hidden" name="idFatura" id="codigoExcluir">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>