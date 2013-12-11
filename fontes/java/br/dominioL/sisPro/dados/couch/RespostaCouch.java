package br.dominioL.sisPro.dados.couch;

import br.dominioL.conexaoH.CodigoDeEstado;

import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.IdentificadorJson;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.estruturados.json.TextoJson;

public final class RespostaCouch {
	private static final IdentificadorJson IDENTIFICADOR = Json.criarIdentificador("identificador");
	private CodigoDeEstado codigoDeEstado;
	private ObjetoJson entidade;
	private String localizacao;

	private RespostaCouch() {}

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

	public RespostaCouch comLocalizacao(String localizacao) {
		this.localizacao = localizacao;
		return this;
	}

	public CodigoDeEstado fornecerCodigoDeEstado() {
		return codigoDeEstado;
	}

	public ObjetoJson fornecerEntidade() {
		return entidade;
	}

	public String fornecerLocalizacao() {
		return localizacao;
	}

	public String fornecerIdentificador() {
		return entidade.fornecer(IDENTIFICADOR).comoTexto();
	}

	public Boolean possuiEntidade() {
		return (entidade != null);
	}
}
