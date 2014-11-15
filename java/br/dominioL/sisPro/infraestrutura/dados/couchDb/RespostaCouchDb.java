package br.dominioL.sisPro.infraestrutura.dados.couchDb;

import br.dominioL.estruturados.elemento.primitivos.Booleano;
import br.dominioL.estruturados.elemento.primitivos.Numero;
import br.dominioL.estruturados.elemento.primitivos.Texto;
import br.dominioL.estruturados.json.Json;
import br.dominioL.estruturados.json.ObjetoJson;
import br.dominioL.sisPro.infraestrutura.http.CodigoDeEstado;

public final class RespostaCouchDb {

	private static final Texto IDENTIFICADOR = Texto.criar("identificador");

	private CodigoDeEstado codigoDeEstado;
	private ObjetoJson entidade;
	private Texto localizacao;

	private RespostaCouchDb() {}

	public static RespostaCouchDb criar() {
		return new RespostaCouchDb();
	}

	public RespostaCouchDb comCodigoDeEstado(Numero codigoDeEstadoNumerico) {
		codigoDeEstado = CodigoDeEstado.fornecerCodigoDeEstado(codigoDeEstadoNumerico);
		return this;
	}

	public RespostaCouchDb comEntidade(Texto entidadeTextual) {
		entidade = Json.criarObjeto(entidadeTextual);
		return this;
	}

	public RespostaCouchDb comLocalizacao(Texto localizacao) {
		this.localizacao = localizacao;
		return this;
	}

	public CodigoDeEstado fornecerCodigoDeEstado() {
		return codigoDeEstado;
	}

	public ObjetoJson fornecerEntidade() {
		return entidade;
	}

	public Texto fornecerLocalizacao() {
		return localizacao;
	}

	public Texto fornecerIdentificador() {
		return entidade.fornecer(IDENTIFICADOR).comoTexto();
	}

	public Booleano possuiEntidade() {
		return Booleano.nulo(entidade).negar();
	}

}
