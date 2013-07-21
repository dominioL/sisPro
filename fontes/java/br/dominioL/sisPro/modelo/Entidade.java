package br.dominioL.sisPro.modelo;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

public abstract class Entidade implements RepresentavelComoJson, Validavel {
	protected ObjetoJson dados;

	public Entidade() {
		this(Json.criarObjeto());
	}

	public Entidade(ObjetoJson dados) {
		this.dados = dados;
	}

	@Override
	public final ObjetoJson comoJson() {
		return dados;
	}

	@Override
	public final void fixarDados(ObjetoJson dados) {
		this.dados = dados;
	}
}
