<?php
include_once 'Erro.php';
include_once 'ValidadorDeDados.php';
include_once 'sistema/bancoDeDados/TabelaDeDadosPessoaFisica.php';

final class PessoaFisica {
	
	private $nome;
	private $telefone1;
	private $telefone2;
	private $email1;
	private $email2;
	private $cpf;
	private $tratamento;
	private $informaçõesExtras;
	private $tabelaDeDados;
	
	public function PessoaFisica() {
		$tabelaDeDados = new TabelaDeDadosPessoaFisica();
	}
	
	public function fornecerNome() {
		return $this->nome;
	}
	
	public function fornecerTelefone1() {
		return $this->telefone1;
	}
	
	public function fornecerTelefone2() {
		return $this->telefone2;
	}
	
	public function fornecerEmail1() {
		return $this->email1;
	}
	
	public function fornecerEmail2() {
		return $this->email2;
	}
	
	public function fornecerCPF() {
		return $this->cpf;
	}
	
	public function fornecerTratamento() {
		return $this->tratamento;
	}
	
	public function fornecerInfomaçõesExtras () {
		return $this->informaçõesExtras;
	}
	
	public function fixarNome($nome) {
		$this->nome = $nome;
	}
	
	public function fixarTelefone1($telefone1) {
		$this->telefone1 = $telefone1;
	}
	
	public function fixarTelefone2($telefone2) {
		$this->telefone2 = $telefone2;
	}
	
	public function fixarEmail1($email1) {
		$this->email1 = $email1;
	}
	
	public function fixarEmail2($email2) {
		$this->email2 = $email2;
	}
	
	public function fixarCPF($cpf) {
		$this->cpf = $cpf;
	}
	
	public function fixarTratamento($tratamento) {
		$this->tratamento = $tratamento;
	}
	
	public function fixarInfomaçõesExtras($informaçõesExtras) {
		$this->informaçõesExtras = $informaçõesExtras;
	}
	
	public function cadastrar() {
		$validadorDeDados = new ValidadorDeDados();
		$this->nome = $validadorDeDados->validarNome($this->nome);
		$this->telefone1 = $validadorDeDados->validarTelefone($this->telefone1);
		$this->telefone2 = $validadorDeDados->validarTelefone($this->telefone2, false);
		$this->email1 = $validadorDeDados->validarEmail($this->email1, false);
		$this->email2 = $validadorDeDados->validarEmail($this->email2, false);
		$this->cpf = $validadorDeDados->validarCPF($this->cpf, false);
		$this->tratamento = $validadorDeDados->validarTratamento($this->tratamento);
		$erro;
		if ($validadorDeDados->possuiErro()) {
			$erro = $validadorDeDados->fornecerErro();
		} else {
			$erro = $tabelaDeDados->cadastrar(
				$this->nome, 
				$this->telefone1, 
				$this->telefone2, 
				$this->email1, 
				$this->email2, 
				$this->cpf, 
				$this->tratamento, 
				$this->informacoesExtras
			);
		}
		
		return $erro;
	}
}
?>