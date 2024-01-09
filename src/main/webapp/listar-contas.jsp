<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listar contas</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
	        <h1> Contas </h1>
	        <table class="table table-striped">
	            <tr>
	                <th>ID da Conta</th>
	                <th>ID do Usuário</th>
	                <th>Código do Banco</th>
	                <th>Saldo</th>
	                <th>Tipo de Conta</th>
	                <th>Limite de Crédito</th>
	                <th></th>
	            </tr>
	            <tbody>
	                <c:forEach items="${listaContas}" var="conta">
	                    <tr>
	                        <td>${conta.id_conta}</td>
	                        <td>${conta.id_usuario}</td>
	                        <td>${conta.cd_banco}</td>
	                        <td>${conta.nr_valor_saldo}</td>
	                        <td>${conta.cd_tipo_conta}</td>
	                        <td>${conta.nr_limite_credito}</td>
	                        <td>
		                        <c:url value="ContaController" var="link">
									<c:param name="acao" value="iniciar-edicao"/>
									<c:param name="idConta" value="${conta.id_conta}"/>
								</c:url>
								<a class="btn btn-primary btn-xs" href="${link}">Editar</a>
								<button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#modalExcluir" onclick="codigoExcluir.value=${conta.id_conta}">
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
							Deseja realmente excluir a conta?
					</div>
					<div class="modal-footer">
						<form action="ContaController" method="post">
							<input type="hidden" name="acao" value="excluir">
							<input type="hidden" name="idConta" id="codigoExcluir">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>