<?php
include_once 'Erro.php';
include_once 'TipoMantenedor.php';
include_once 'sistema/bancoDeDados/TabelaDeDadosAdministrador.php';

final class Administrador implements TipoMantenedor {
	
	private $logado;
	private $tabelaDeDados;
	
	public function Administrador() {
		$this->logado = false;
		$this->tabelaDeDados = new TabelaDeDadosAdministrador();
	}
	
	public function cadastrarMantenedor($login, $senha, $páginasDeAcesso) {
		$erro;
		if ($this->logado) {
			
		} else {
			$erro = $this->criarErroDePermissãoNegada();	
		}
	 }
	 
	 public function cadastrarAdministrador($login, $senha) {
	 	$erro;
	 	if ($this->logado) {
	 		$senha = $this->encripitarSenha($senha);
	 		$erro = $this->tabelaDedados->cadastrar($login, $senha);
	 	} else {
	 		$erro = $this->criarErroDePermissãoNegada();
	 	}
	 	
	 	return $erro;
	 }
	
	public function logar($login, $senha) {
		if (!$this->logado) {
			$senha = $this->encriptarSenha($senha);
			$this->logado = $this->tabelaDeDados->logar($login, $senha);
		}
		
		return $this->logado;
	}

	public function estáLogado() {
		return $this->logado;
	}
	
	public function possuiAcesso($página) {
		return $this->logado;
	}
	
	private function encriptarSenha($senha) {
		return hash('whirlpool', $senha);
	}
	
	private function criarErroDePermissãoNegada() {
		$erro = new Erro();
	 	$erro->inserirErro('É necessário estar logado para realizar esta ação.');
	 	
	 	return $erro;
	}
}
?>