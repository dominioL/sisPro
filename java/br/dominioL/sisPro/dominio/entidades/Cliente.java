package br.dominioL.sisPro.dominio.entidades;

import br.dominioL.sisPro.dados.couch.repositorios.RepositorioCouch;
import br.dominioL.sisPro.dados.couch.repositorios.RepositorioDeClientes;
import br.dominioL.sisPro.dominio.Validador;

public final class Cliente extends Entidade<Cliente> {
	@Override
	public RepositorioCouch<Cliente> fornecerRepositorio() {
		return RepositorioDeClientes.fornecerInstancia();
	}

	@Override
	public Boolean validar() {
		return Validador.criar(this)
			.comCampoObrigatorio("nome", Validador.NOME)
			.comListaDeCamposObrigatorio("telefones", Validador.TELEFONE)
			.comListaDeCamposObrigatorio("enderecosEletronicos", Validador.EMAIL)
			.comCampo("cpf", Validador.CPF)
			.comCampo("cnpj", Validador.CNPJ)
			.comCampo("inscricaoEstadual", Validador.IE)
			.naoPermitirOutrosCampos()
			.validar();
	}
}
