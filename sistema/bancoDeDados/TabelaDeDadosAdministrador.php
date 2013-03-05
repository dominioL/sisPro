<?php
include_once 'BancoDeDadosMySQL.php';

final class TabelaDeDadosAdministrador{
	
	private $bancoDeDados;
	private $tabela;
	private $campos;

	public function TabelaDeDadosAdministrador() {
		$this->bancoDeDados = new BancoDeDadosMySQL();
		$this->tabela = 'mantenedorAdministrador';
		$this->campos = 'codigo, login, senha';
	}
	
	public function cadastrar($login, $senha) {
		$valores = "NULL, $nome, $senha";
			
		return $this->bancoDeDados->salvarRegistro($this->tabela, $this->campos, $valores);
	}
	
	public function logar($login, $senha) {
		$consulta = $this->bancoDeDados->buscarRegistroSimples(
			$this->tabela, 
			$this->campos, 
			"login = $login AND senha = $senha", 
			'login', 
			true, 
			1
		);
		echo($consulta);
		if (mysql_num_rows($consulta) > 0) {
			return true;
		}	
		
		return false;
	}
}
?>