<?php 
include_once 'sistema/modelo/Administrador.php';
include_once 'sistema/visao/InterfaceGrafica.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	$login = $_POST['login'];
	$senha = $_POST['senha'];
	$adiministrador = new Administrador();
	$logado = $adiministrador->logar($login, $senha);
	$_SESSION['TipoMantenedor'] = $adiministrador;
	if (!$logado) {
		InterfaceGrafica::mostrarMensagemDeErroDeLogin();
	} else {
		InterfaceGrafica::mostrarMensagemDeSucessoDeLogin();
	}
}
?>

<h1>Acesso Ao SisPro</h1>
<form action="" method="post">
	<div class="divisao">
		<label for="login">Login:
			<input type="text" name="login" maxlength="16" placeholder="Insira Seu Login" tabindex="1" <?php if (isset($email1)) echo('value="'.$login.'"'); ?> />
		</label>
		<label for="senha">Senha:
			<input type="password" name="senha" maxlength="16" placeholder="Insira Sua Senha" tabindex="2" <?php if (isset($senha)) echo('value="'.$senha.'"'); ?> />
		</label>
	</div>
	<div class="divisao">
		<input type="submit" value="Entrar" tabindex="3" />
	</div> 
</form>