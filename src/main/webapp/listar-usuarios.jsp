<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Listar Usuários</title>
		<%@ include file="header.jsp" %>
	</head>
	<body>
		<%@ include file="menu.jsp" %>
		<div class="container" style="margin-top: 20px;">
	        <h1> Usuários </h1>
	        <table class="table table-striped">
	            <tr>
					<th>ID Usuário</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Senha</th>
					<th></th>
	            </tr>
	            <tbody>
	                <c:forEach items="${listaUsuarios}" var="usuario">
	                    <tr>
	                        <td>${usuario.id_usuario}</td>
							<td>${usuario.nome_usuario}</td>
							<td>${usuario.ds_email_usuario}</td>
							<td>${usuario.ds_senha}</td>
							<td>
								<c:url value="UsuarioController" var="link">
									<c:param name="acao" value="iniciar-edicao"/>
									<c:param name="idUsuario" value="${usuario.id_usuario}"/>
								</c:url>
								<a class="btn btn-primary btn-xs" href="${link}">Editar</a>
								<button type="button" class="btn btn-danger btn-xs" data-bs-toggle="modal" data-bs-target="#modalExcluir" onclick="codigoExcluir.value=${usuario.id_usuario}">
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
							Deseja realmente excluir o usuário?
					</div>
					<div class="modal-footer">
						<form action="UsuarioController" method="post">
							<input type="hidden" name="acao" value="excluir">
							<input type="hidden" name="idUsuario" id="codigoExcluir">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-danger">Excluir</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>