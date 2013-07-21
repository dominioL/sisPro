package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.ObjetoJson;

public final class Cliente extends Entidade {
	@Override
	public Boolean validar() {
		Validador validador = new Validador();
		validador.validarCampoObrigatorio(dados, "nome", Validador.NOME);
		validador.validarListaDeCamposObrigatorio(dados, "telefones", Validador.TELEFONE);
		validador.validarListaDeCamposObrigatorio(dados, "enderecosEletronicos", Validador.EMAIL);
		validador.validarCampo(dados, "cpf", Validador.CPF);
		validador.validarCampo(dados, "cnpj", Validador.CNPJ);
		validador.validarCampo(dados, "inscricaoEstadual", Validador.IE);
		return validador.validar();
	}
}
