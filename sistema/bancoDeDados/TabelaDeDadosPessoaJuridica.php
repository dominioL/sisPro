<?php
include_once 'BancoDeDadosMySQL.php';

final class TabelaDeDadosPessoaJuridica {
	
	private $bancoDeDados;
	private $tabela;
	private $campos;

	public function TabelaDeDadosPessoaJuridica() {
		$this->bancoDeDados = new BancoDeDados();
		$this->tabela = 'clienteJuridica';
		$this->campos = 'codigo, nome, responsavel, telefone1, telefone2, email1, email2, cnpj, '; 
		$this->campos .= 'inscricaoEstadual, tratamento, informcoesExtras, dataDeCadastro';
	}
	
	public function cadastrar($nome, $responsável, $telefone1, $telefone2, $email1, $email, $cnpj, 
		$inscriçãoEstadual, $tratamento, $informaçõesExtras) {
		$valores = "NULL, $nome, $telefone1, $telefone2, $email1, $email, $cpf, ";
		$valores .= "$tratamento, $informaçõesExtras, NOW()";
			
		return $this->bancoDeDados->salvarRegistro($this->tabela, $this->campos, $valores);
	}
}
?>