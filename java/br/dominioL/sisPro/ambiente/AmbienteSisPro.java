package br.dominioL.sisPro.ambiente;

import javax.ws.rs.core.Application;

import br.dominioL.sisPro.infraestrutura.dados.couchDb.CouchDb;
import br.dominioL.sisPro.infraestrutura.http.ConstrutorDeUri;

public final class AmbienteSisPro implements Ambiente<CouchDb> {

	@Override
	public ConstrutorDeUri construtorDeUri() {
		return ConstrutorDeUri.criar()
				.protocolo(ConfiguracoesSisPro.PROTOCOLO)
				.endereco(ConfiguracoesSisPro.ENDERECO)
				.porta(ConfiguracoesSisPro.PORTA)
				.caminho(ConfiguracoesSisPro.CAMINHO_BASE);
	}

	@Override
	public ConstrutorDeUri construtorDeUriParaBancoDeDados() {
		return ConstrutorDeUri.criar()
				.protocolo(ConfiguracoesSisPro.BANCO_PROTOCOLO)
				.endereco(ConfiguracoesSisPro.BANCO_ENDERECO)
				.porta(ConfiguracoesSisPro.BANCO_PORTA)
				.caminho(ConfiguracoesSisPro.BANCO_CAMINHO_BASE);
	}

	@Override
	public Application aplicacao() {
		return new AplicacaoSisPro();
	}

	@Override
	public CouchDb bancoDeDados() {
		return new CouchDb();
	}

}
