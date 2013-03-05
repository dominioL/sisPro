<?php 
	session_start();
	$pag = 'inicio';
	if (isset($_GET['pag'])) {
		$pag = $_GET['pag'];
	}
?>

<!DOCTYPE html>

<html lang="pt-br">

<head>
	<meta charset="UTF-8" />
	<meta name="author" content="Lucas Pereira da Silva" />
	
	<link rel="stylesheet" type="text/css" href="css/estiloSisPro.css" />
	
	<title>SisPro - Sistema Protege Redes &amp; Telas de Gerenciamento Administrativo</title>
</head>

<body>
	<hr />
	
	<div id="topo">
	<header id="cabecalho">
		<h1 class="logo"><span>Logo Protege Redes &amp; Telas</span></h1>
		<h1><strong>SisPro</strong>: Sistema Protege Redes &amp; Telas de Gerenciamento Administrativo</h1>
	</header>
	</div>
	
	<hr />
	
	<div id="meio">
	<nav id="navegacao">
		<ul id="menuPrincipal">
			<li>Clientes
				<ul>
					<li><a href="?pag=cadastroDePessoaFisica">Cadastrar Pessoa Física</a></li>
					<li><a href="?pag=cadastroDePessoaJuridica">Cadastrar Pessoa Jurídica</a></li>
					<li><a href="#">Buscar Cliente</a></li>
					<li><a href="#">Listar Clientes</a></li>
				</ul>
			</li>
			<li>Orçamentos
				<ul>
					<li><a href="#">Listar Orçamentos Em Aberto</a></li>
					<li><a href="#">Listar Orçamentos Fechados</a></li>
				</ul>
			</li>
			<li>Endereço
				<ul>
					<li><a href="#">Buscar Endereço</a></li>
					<li><a href="#">Buscar Edíficios</a></li>
					<li><a href="#">Listar Edíficios</a></li>
				</ul>
			</li>
			<li>Controle de Caixa
				<ul>
					<li><a href="#">Controle de Caixa</a></li>
				</ul>
			</li>
			<li>Controle de Estoque
				<ul>
					<li><a href="#">Controle de Estoque</a></li>
				</ul>
			</li>
			<li>Fornecedores
				<ul>
					<li><a href="#">Cadastrar Fornecedor</a></li>
					<li><a href="#">Buscar Fornecedor</a></li>
					<li><a href="#">Listar Fornecedores</a></li>
				</ul>
			</li>
			<li>Funcionários
				<ul>
					<li><a href="#">Cadastrar Funcionário</a></li>
					<li><a href="#">Buscar Funcionário</a></li>
					<li><a href="#">Listar Funcionários</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	
	<hr />

	<section id="conteudo">
		<?php
			if ((isset($_SESSION['TipoMantenedor']) && $_SESSION['TipoMantenedor']->possuiAcesso($pag)) || $pag == 'inicio') {
				$paginas = scandir('paginas', 0);
				unset($paginas[0]);
				unset($paginas[1]);
				$pag = 'cadastroDePessoaFisica';
				if (in_array("$pag.php", $paginas)) {
					require("paginas/$pag.php"); 
				} else {
					print("<p class='erro'>A página <strong>$pag.php</strong> não foi encontrada. Fale com o admnistrador do sistema.</p>");
				}
			} else {
				print("<p class='erro'>É necessário estar logado no sistema para ter acesso a está página.</p>");	
			}
		?>
	</section>
	</div>
	
	<hr />
</body>
</html>
