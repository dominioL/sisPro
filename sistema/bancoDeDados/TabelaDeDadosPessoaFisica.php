<?php
include_once 'BancoDeDadosMySQL.php';

final class TabelaDeDadosPessoaFisica {
	
	private $bancoDeDados;
	private $tabela;
	private $campos;

	public function TabelaDeDadosPessoaFisica() {
		$this->bancoDeDados = new BancoDeDadosMySQL();
		$this->tabela = 'clienteFisica';
		$this->campos = 'codigo, nome, telefone1, telefone2, email1, email2, cpf, tratamento, ';
		$this->campos .= 'informcoesExtras, dataDeCadastro';
	}
	
	public function cadastrar($nome, $telefone1, $telefone2, $email1, $email, $cpf, $tratamento, 
		$informaçõesExtras) {
		$valores = "NULL, $nome, $telefone1, $telefone2, $email1, $email, $cpf, ";
		$valores .= "$tratamento, $informaçõesExtras, NOW()";
			
		return $this->bancoDeDados->salvarRegistro($this->tabela, $this->campos, $valores);
	}
}
?>