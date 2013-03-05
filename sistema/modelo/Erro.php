<?php
final class Erro {
	
	private $quantidadeDeErros;
	private $mensagensDeErro;
	
	const ZERO = 0;
	
	public function Erro() {
		$quantidadeDeErros = self::ZERO;
		$mensagensDeErro = array();
	}
	
	public function inserirErro($mensagemDeErro) {
		$this->mensagensDeErro[$this->quantidadeDeErros++] = $mensagemDeErro;
	}
	
	public function fornecerErro() {
		if ($quantidadeDeErros != self::ZERO) {
			$mensagemDeErro = $this->mensagensDeErro[--$this->quantidadeDeErros];
			unset($this->mensagensDeErro[$this->quantidadeDeErros]);
			return $mensagemDeErro;
		}
		
		return '';
	}
	
	public function limparErros() {
		unset($this->mensagensDeErro);
		$this->mensagensDeErro = array();
		$this->quantidadeDeErros = self::ZERO;
	}
	
	public function possuiErro() {
		return ($this->quantidadeDeErros > self::ZERO);
	}
}
?>