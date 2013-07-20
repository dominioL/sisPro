package br.dominioL.sisPro.dados.couch;

import br.dominioL.conexaoH.CodigoDeEstado;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;

public final class RespostaCouch {
	private CodigoDeEstado codigoDeEstado;
	private ObjetoJson entidade;

	private RespostaCouch() {
		//TODO
	}

	public static RespostaCouch criar() {
		return new RespostaCouch();
	}

	public RespostaCouch comCodigoDeEstado(Integer codigoDeEstadoNumerico) {
		codigoDeEstado = CodigoDeEstado.fornecerCodigoDeEstado(codigoDeEstadoNumerico);
		return this;
	}

	public RespostaCouch comEntidade(String entidadeTextual) {
		entidade = Json.criarObjeto(entidadeTextual);
		return this;
	}

	public CodigoDeEstado fornecerCodigoDeEstado() {
		return codigoDeEstado;
	}

	public ObjetoJson fornecerEntidade() {
		return entidade;
	}
}
