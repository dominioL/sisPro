<?php
interface TipoMantenedor {
	
	public function logar($login, $senha);

	public function estáLogado();
	
	public function possuiAcesso($página);
}
?>