<?php
include_once 'sistema/modelo/Erro.php';

final class InterfaceGrafica {
	
	public static function mostrarMensagemDeErroDeConexãoNoBancoDedados($erro) {
		$mensagemDeErro = $erro->fornecerErro();
		print("<p class='erro'>Ocorreu um erro no banco de dados MySQL: $mensagemDeErro</p>");
	}
	
	public static function mostrarMensagemDeErroDeSalvamentoNoBancoDeDados($erro) {
		$mensagemDeErro = $erro->fornecerErro();
		print("<p class='erro'>Ocorreu um erro de salvamento no banco de dados: $mensagemDeErro</p>");
	}
	
	public static function mostrarMensagemDeSucessoDeCadastro() {
		print("<p class='sucesso'>O cadastro foi realizado com sucesso.</p>");
	}

	public static function mostrarMensagemDeErroDeValidacao($erro) {
		$erros = '';
		while ($erro->possuiErro()) {
			$mensagemDeErros = $erro->fornecerErro();
			$erros .= "\n<li>$mensagemDeErro</li>";
		}
		print("<p class='erro'>Ocorreu um ou mais erros na validação dos dados:</p> \n <ul class='erro'>$erros</ul>");
	}
	
	public static function mostrarMensagemDeErroDeLogin() {
		print("<p class='erro'>Não foi possível realizar o login. Acesso negado.</p>");
	}
	
	public static function mostrarMensagemDeSucessoDeLogin() {
		print("<p class='sucesso'>O login foi realizado com sucesso.</p>");
	}
}
?> 