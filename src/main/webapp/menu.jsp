<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-dark bg-dark navbar-expand-lg" style="padding: 15px 20px;">
    <a class="navbar-brand" href="#">Fintech</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	        <li class="nav-item dropdown">
	            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				    Conta Bancária
				</a>
				<ul class="dropdown-menu">
				    <li><a class="dropdown-item" href="cadastrar-conta.jsp">Cadastrar Conta</a></li>
				    <li><a class="dropdown-item" href="ContaController?acao=listar">Listar Contas</a></li>
				</ul>
	        </li>
	        <li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			        Fatura
			    </a>
			    <ul class="dropdown-menu">
			        <li><a class="dropdown-item" href="cadastrar-fatura.jsp">Cadastrar Fatura</a></li>
			        <li><a class="dropdown-item" href="FaturaController?acao=listar">Listar Faturas</a></li>
			    </ul>
			</li>
			<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			        Lançamento
			    </a>
			    
			    <ul class="dropdown-menu">
			        <li><a class="dropdown-item" href="cadastrar-lancamento.jsp">Cadastrar Lançamento</a></li>
			        <li><a class="dropdown-item" href="LancamentoController?acao=listar">Listar Lançamentos</a></li>
			    </ul>
			</li>
			<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			        Transação
			    </a>
			    
			    <ul class="dropdown-menu">
			        <li><a class="dropdown-item" href="cadastrar-transacao.jsp">Cadastrar Transação</a></li>
			        <li><a class="dropdown-item" href="TransacaoController?acao=listar">Listar Transações</a></li>
			    </ul>
			</li>
			<li class="nav-item dropdown">
			    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			        Usuário
			    </a>
			    
			    <ul class="dropdown-menu">
			        <li><a class="dropdown-item" href="cadastrar-usuario.jsp">Cadastrar Usuário</a></li>
			        <li><a class="dropdown-item" href="UsuarioController?acao=listar">Listar Usuários</a></li>
			    </ul>
			</li>
			<li>
				<c:if test="${empty user }">
					<span class="navbar-text">
						<a href="login.jsp" class="btn btn-outlineprimary my-2 my-sm-0">Faça login</a>
					</span>
				</c:if>
				<c:if test="${not empty user }">
					<span class="navbar-text"> ${user}
						<a href="login" class="btn btn-outlineprimary my-2 my-sm-0">Sair</a>
					</span>
				</c:if>
			</li>	
	    </ul>
	</div>
</nav>
