<?php
include_once 'Erro.php';
include_once 'ValidadorDeDados.php';
include_once 'sistema/bancoDeDados/TabelaDeDadosPessoaJuridica.php';

final class PessoaJuridica {
	
	private $nome;
	private $responsável;
	private $telefone1;
	private $telefone2;
	private $email1;
	private $email2;
	private $cnpj;
	private $inscriçãoEstadual;
	private $informaçõesExtras;
	private $tabelaDeDados;
	
	public function PessoaJuridica() {
		$tabelaDeDados = new TabelaDeDadosPessoaJuridica();	
	}
	
	public function fornecerNome() {
		return $this->nome;
	}
	
	public function fornecerResponsável() {
		return $this->responsável;
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

	public function fornecerCNPJ() {
		return $this->cnpj;
	}
	
	public function fornecerInscriçãoEstadual() {
		return $this->inscriçãoEstadual;
	}
	
	public function fornecerInfomaçõesExtras () {
		return $this->informaçõesExtras;
	}
	
	public function fixarNome($nome) {
		$this->nome = $nome;
	}
	
	public function fixarResponsável($responsável) {
		$this->responsável = $responsável;
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
	
	public function fixarCPF($cnpj) {
		$this->cpf = $cnpj;
	}
	
	public function fixarTratamento($inscriçãoEstadual) {
		$this->inscriçãoEstadual = $inscriçãoEstadual;
	}
	
	public function fixarInfomaçõesExtras($informaçõesExtras) {
		$this->informaçõesExtras = $informaçõesExtras;
	}
	
	public function cadastrar() {
		$validadorDeDados = new ValidadorDeDados();
		$this->nome = $validadorDeDados->validarNome($this->nome, true);
		$this->responsável = $validadorDeDados->validarResponsável($this->responsável, false);
		$this->telefone1 = $validadorDeDados->validarTelefone($this->telefone1, true);
		$this->telefone2 = $validadorDeDados->validarTelefone($this->telefone2, false);
		$this->email1 = $validadorDeDados->validarEmail($this->email1, false);
		$this->email2 = $validadorDeDados->validarEmail($this->email2, false);
		$this->cnpj = $validadorDeDados->validarCNPJ($this->cnpj, false);
		$this->inscriçãoEstadual = $validadorDeDados->validarInscriçãoEstadual($this->inscriçãoEstadual, false);
		$erro;
		if ($validadorDeDados->possuiErro()) {
			$erro = $validadorDeDados->fornecerErro();
		} else {
			$erro = $tabelaDeDados->cadastrar(
				$this->nome,
				$this->responsável,
				$this->telefone1, 
				$this->telefone2, 
				$this->email1, 
				$this->email2,
				$this->cnpj, 
				$this->inscriçãoEstadual, 
				$this->informacoesExtras
			);
		}
		
		return $erro;
	}
}
?>