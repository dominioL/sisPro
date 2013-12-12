package br.dominioL.sisPro.modelo;

import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.dados.couch.RepositorioDeClientes;

public final class Cliente extends Entidade<Cliente> {
	@Override
	public RepositorioCouch<Cliente> fornecerRepositorio() {
		return RepositorioDeClientes.fornecerInstancia();
	}

	@Override
	public Boolean validar() {
		return new Validador(this)
			.validarCampoObrigatorio("nome", Validador.NOME)
			.validarListaDeCamposObrigatorio("telefones", Validador.TELEFONE)
			.validarListaDeCamposObrigatorio("enderecosEletronicos", Validador.EMAIL)
			.validarCampo("cpf", Validador.CPF)
			.validarCampo("cnpj", Validador.CNPJ)
			.validarCampo("inscricaoEstadual", Validador.IE)
			.naoPermitirOutrosCampos()
			.validar();
	}
}
