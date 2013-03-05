<?php
include_once 'BancoDeDadosSQL.php';
include_once 'sistema/modelo/Erro.php';
include_once 'sistema/visao/InterfaceGrafica.php';

final class BancoDeDadosMySQL implements BancoDeDadosSQL {
	
	private $conexão;
	
	const SERVIDOR = 'localhost';
	const USUARIO = 'p79654_p79654';
	const SENHA = '17$hdsqEd';
	const BANCO = 'p79654_SisPro';

	public function BancoDeDadosMySQL() {
		
	}
	
	private function conectar() {
		$this->conexão = mysql_connect(self::SERVIDOR, self::USUARIO, self::SENHA);
		mysql_selectdb(self::BANCO, $this->conexão);
		if (mysql_errno($this->conexão)) {
			$erro = new Erro();
			InterfaceGrafica::mostrarMensagemDeErroDeConexãoNoBancoDedados($erro->inserirErro(mysql_error($this->conexão)));
			die;
		}
	}

	private function fecharConexao() {
		mysql_close($this->conexão);
	}

	public function salvarRegistro($tabela, $campos, $valores) {
		$this->conectar();
		$erro = new Erro();
		$cadastro = mysql_query("INSERT INTO $tabela ($campos) VALUES($valores)", $this->conexão);
		if (!$cadastro) {
			$erro->inserirErro(mysql_error($this->conexão));
		} 
		$this->fecharConexão();
		
		return $erro;
	}
	
	public function deletarRegistro($tabela, $condição) {
		$this->conectar();
		mysql_query("DELET FROM $tabela WHERE $condição", $this->conexão);
		$this->fecharConexao();
	}
	
	public function buscarRegistroSimples($tabela, $campos, $condição, $ordenadoPor, $ascendentemente, $limite) {
		$this->conectar();
		$ascendentemente = ($ascendentemente) ? 'ASC' : 'DESC';
		$consulta = mysql_query("SELECT $campos FROM $tabela WHERE $condição ORDER BY $ordenadoPor $ascendentemente LIMIT $limite", $this->conexão);
		$this->fecharConexao();

		return $consulta;
	}
	
	public function buscarRegistroAvançado($tabela, $campos, $condição, $unicoCom, $onde, $ordenadoPor, $ascendentemente, $limite){
		
	}
	
	public function atualizarRegistro($tabela, $alterações, $condição) {
		
	}
}
?>