<?php
interface BancoDeDadosSQL {
	
	public function salvarRegistro($tabela, $campos, $valores);
	
	public function deletarRegistro($tabela, $condição);
	
	public function buscarRegistroSimples($tabela, $campos, $condição, $ordenadoPor, $ascendentemente, $limite);
	
	public function buscarRegistroAvançado($tabela, $campos, $condição, $unicoCom, $onde, $ordenadoPor, $ascendentemente, $limite);
	
	public function atualizarRegistro($tabela, $alterações, $condição);
}