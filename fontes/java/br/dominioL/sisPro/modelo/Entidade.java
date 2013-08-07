package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

import br.dominioL.sisPro.dados.couch.RepositorioCouch;

public abstract class Entidade<T extends Entidade<T>> implements RepresentavelComoJson, Validavel {
	protected ObjetoJson dados;

	public Entidade() {
		this(Json.criarObjeto());
	}

	public Entidade(ObjetoJson dados) {
		this.dados = dados;
	}

	public abstract RepositorioCouch<T> fornecerRepositorio();

	@Override
	public final ObjetoJson comoJson() {
		return dados;
	}

	@Override
	public final void fixarDados(ObjetoJson dados) {
		this.dados = dados;
	}
}
