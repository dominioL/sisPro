<?php
include_once 'Erro.php';

final class ValidadorDeDados {
	
	private $erro;

	public function ValidadorDeDados() {
		$this->erro = new Erro();
	}

	public function fornecerErro() {
		return $this->erro;
	}
	
	public function possuiErro() {
		return $this->erro->possuiErro();
	}
	
	public function validarCampoDeTexto($valor, $nomeDoCampo, $tamanhoMínimo, $tamanhoMáximo, $obrigatório = true) {
		$valor = trim($valor);
		$this->verificarTamanho($valor, $nomeDoCampo, $tamanhoMínimo, $tamanhoMáximo, $obrigatório);
		
		return $valor;
	}

	public function validarNome($nome, $obrigatório = true) {
		$this->validarCampoDeTexto($nome, 'Nome', 2, 50, $obrigatório);

		return $nome;
	}
	
	public function validarResponsável($responsável, $obrigatório = true) {
		$this->validarCampoDeTexto($responsável, 'Responsável', 2, 50, $obrigatório);

		return $responsavel;
	}

	public function validarEmail($email, $obrigatório = true) {
		$email = trim($email);
		$email = strtolower($email);
		$email = str_replace(' ', '', $email);
		$email = str_replace('.@', '@', $email);
		$email = str_replace('@.', '@', $email);
		$email = str_replace('\'', '', $email);
		$email = str_replace('/', '',  $email);
		$email = str_replace('\\', '', $email);
		$email = str_replace(',', '.', $email);
		$email = str_replace(';', '.', $email);
		$this->verificarTamanho($email, 'E-mail', 6, 64, $obrigatório);
		if ((!strpos($email, '@') || !strpos($email, '.')) && !empty($email)) {
			$this->erro->inserirErro('E-mail inválido.');
		}
			
		return $email;
	}

	public function validarTelefone($telefone, $obrigatório = true) {
		$telefone = $this->validarCampoNumérico($telefone, 'Telefone', 10, $obrigatório);
			
		return $telefone;
	}

	public function validarCPF($cpf, $obrigatório = true)	{
		$cpf = $this->validarCampoNumérico($cpf, 'CPF', 11, $obrigatório);
			
		return $cpf;
	}

	public function validarCNPJ($cnpj, $obrigatório = true) {
		$cnpj = $this->validarCampoNumérico($cnpj, 'CNPJ', 14, $obrigatório);
			
		return $cnpj;
	}

	public function validarInscriçãoEstadual($inscricaoEstadual, $obrigatório = true)	{
		$inscriçãoEstadual = $this->validarCampoNumérico($inscriçãoEstadual, 'Inscrição Estadual', 9, $obrigatório);
			
		return $inscricaoEstadual;
	}

	public function validarTratamento($tratamento, $obrigatório = true) {
		$tratamento = strtoupper($tratamento);
		if ($tratamento != 'M' && $tratamento != 'F') {
			$this->erro->inserirErro('Tratamento inválido.');
		}
			
		return $tratamento;
	}

	private function validarCampoNumérico($numero, $nomeDoCampo, $tamanho, $obrigatório = true) {
		$numero = trim($numero);
		$numero = str_replace('-', '', $numero);
		$numero = str_replace('.', '', $numero);
		$this->verificarTamanho($numero, $nomeDoCampo, $tamanho, $tamanho, $obrigatório);
		if (!is_numeric($numero) && !empty($numero)) {
			$this->erro->inserirErro("$nomeDoCampo inválido.");
		}
			
		return $numero;
	}

	private function verificarTamanho($campo, $nomeDoCampo, $tamanhoMinimo, $tamanhoMaximo, $obrigatório)	{
		$campo = trim($campo);
		if (empty($campo) && $obrigatório) {
			$this->erro->inserirErro("$nomeDoCampo deve ser preenchido.");
		}
		$tamanhoCampo = strlen($campo);
		if (($tamanhoCampo < $tamanhoMínimo || $tamanhoCampo > $tamanhoMáximo) && $tamanhoCampo != 0) {
			$this->erro->inserirErro("$nomeDoCampo está com tamanho fora dos limites permitidos. O campo deve ter entre $tamanhoMínimo e $tamanhoMáximo caracteres.");
		}
	}
}
?>