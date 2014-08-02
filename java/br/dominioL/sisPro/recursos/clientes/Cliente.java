package br.dominioL.sisPro.recursos.clientes;

import br.dominioL.sisPro.dados.couch.RepositorioCouch;
import br.dominioL.sisPro.dominio.Entidade;
import br.dominioL.sisPro.validadores.Validador;

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
